package com.barter.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.barter.model.Order;
import com.barter.model.OrderItem;
import com.barter.model.Product;
import com.barter.model.User;
import com.barter.service.OrderItemService;
import com.barter.service.OrderService;
import com.barter.service.ProductService;
import com.barter.service.ShoppingCartService;
import com.barter.tools.CurrentUser;
import com.barter.tools.LoginRequired;
import com.barter.tools.Result;
import com.barter.tools.ResultGenerator;
import com.barter.tools.ServiceException;
import com.barter.tools.TableData;
import com.barter.tools.UUIDUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private OrderService orderService;

	/**
	 * 上架
	 * 
	 * @Title: add
	 * @Description: TODO
	 * @param product
	 * @param user
	 * @return Result
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@LoginRequired
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Result add(@RequestParam(required = false) Map<String, Object> map, @CurrentUser User user,
			@RequestParam(value = "file", required = false) MultipartFile file)
			throws IllegalAccessException, InvocationTargetException {
		Product product = new Product();
		product.setCreateBy(user.getUserName());
		product.setUserId(user.getId());
		product.setUpdateBy(user.getId());
		BeanUtils.populate(product, map);
		return ResultGenerator.genSuccessResult(productService.add(product, file));
	}

	/**
	 * 获取商品列表
	 * 
	 * @param pageNum
	 * @param size
	 * @param product
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Result get(Integer pageNum, Integer size, Product product,
			@RequestParam(required = false) Map<String, Object> map) {
		String orderBy = "";
		if (map.get("orderBy") == null || map.get("orderBy").equals("")) {
			orderBy = "create_date";
		} else {
			orderBy = map.get("orderBy").toString();
		}
		Page<Product> page = PageHelper.startPage(pageNum == null ? 1 : pageNum, size == null ? 5 : size,
				orderBy + " desc");
		List<Product> list = productService.findList(map);
		return ResultGenerator.genSuccessResult(new TableData<Product>(page.getTotal(), list));
	}

	/**
	 * 删除商品
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable(value = "id") String id) {
		return ResultGenerator.genSuccessResult(productService.delete(id));
	}

	/**
	 * 获取商品详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result getById(@PathVariable String id) {
		productService.getHits(id); // 增加浏览量
		return ResultGenerator.genSuccessResult(productService.findById(id));
	}

	/**
	 * 修改商品详情
	 * 
	 * @param map
	 * @param user
	 * @param id
	 * @param file
	 * @return
	 */
	@LoginRequired
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Result update(@RequestParam(required = false) Map<String, Object> map, @CurrentUser User user,
			@PathVariable String id, @RequestParam(required = false) MultipartFile file) {
		map.put("updateby", user.getId());
		map.put("proid", id);
		return ResultGenerator.genSuccessResult(productService.update(map, file));
	}

	/**
	 *
	 * @Title: getByUser
	 * @Description: TODO 查询自己的上架的商品
	 * @param pageNum
	 *            当前页
	 * @param size
	 *            每页显示条数
	 * @param map
	 * @param user
	 * @return Result
	 */
	@LoginRequired
	@RequestMapping(value = "/getByUser", method = RequestMethod.GET)
	public Result getByUser(Integer pageNum, Integer size, @RequestParam(required = false) Map<String, Object> map,
			@CurrentUser User user) {
		map.put("userId", user.getId());
		Page<Product> page = PageHelper.startPage(pageNum == null ? 1 : pageNum, size == null ? 5 : size, "create_date");
		List<Product> list = productService.findList(map);
		return ResultGenerator.genSuccessResult(new TableData<Product>(page.getTotal(), list));
	}

	/**
	 * 购买
	 * 
	 * @param user
	 * @param proId
	 * @return
	 */
	@LoginRequired
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public Result buyProduct(@CurrentUser User user, @RequestBody(required = false) List<Map<String, Object>> map) {
		OrderItem orderItem = new OrderItem();
		Double total = new Double(0);
		Order order = new Order();
		order.setOrderId("wg_" + UUIDUtils.getOrderIdByTime()); // 订单号
		for (Map<String, Object> productMap : map) {
			Integer quantity = Integer.parseInt(productMap.get("quantity").toString());
			Integer quality = Integer.parseInt(productMap.get("quality").toString());
			int i = quality - quantity; // 减库存
			// 如果库存小于0则提醒买家
			if (i < 0) {
				throw new ServiceException(
						"商品" + productMap.get("name") + "库存还剩余:" + productMap.get("quality") + "，请减少购买数量!");
			}
			Map<String, Object> map1 = new HashMap<>();
			if (i == 0) {
				map1.put("status", "2"); // 售罄
			}
			map1.put("id", productMap.get("proid"));
			map1.put("quality", i);
			if (productMap.get("isnotauction").toString().equals("1")) {
				map1.put("auctionStatus", "2");
			}
			productService.update(map1, null); // 修改库存量
			Map<String, Object> shoppingMap = new HashMap<>();
			shoppingMap.put("productid", productMap.get("productid"));
			List<Map<String, Object>> list = shoppingCartService.findList(shoppingMap);
			if (list.size() != 0) {
				String id = (String) list.get(0).get("cartid");
				shoppingCartService.delete(id);
			}
			Product product = productService.findById((String) productMap.get("proid"));
			// 购买时生成订单子项
			orderItem.setOrderItemId(UUIDUtils.getOrderIdByTime()); // 订单子项号
			orderItem.setOrderId(order.getOrderId()); // 对应订单号
			orderItem.setSellid(product.getId()); // 卖家Id
			orderItem.setUserid(user.getId()); // 买家Id
			orderItem.setCreateTime(new Date()); // 下单日期
			orderItem.setStatus("0"); // 未发货待付款状态
			orderItem.setQuantity(quantity); // 数量
			orderItem.setPayment(quantity * product.getPrice()); // 小计
			orderItem.setProductid(product.getId()); // 商品id
			orderItemService.add(orderItem); // 添加
			total += orderItem.getPayment(); // 计算总价
		}
		// 同时生成订单
		order.setPayment(total); // 总价
		order.setUserId(user.getId());
		order.setStatus("0");
		orderService.add(order);
		return ResultGenerator.genSuccessResult(order);
	}

	/**
	 * 竞拍
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	// @LoginRequired
	// @RequestMapping(value = "/auction/{productId}", method =
	// RequestMethod.PUT)
	// public Result auction(@RequestBody Map<String, Object> map, @CurrentUser
	// User user,
	// @PathVariable String productId) {
	// Product product = productService.findById(productId);
	// if (product.getId().equals(user.getId())) {
	// return ResultGenerator.genFailResult("您不能竞拍自己的商品");
	// }
	// if (product.getPrice() >= Float.parseFloat(map.get("price").toString()))
	// {
	// return ResultGenerator.genFailResult("出价不能低于当前价格");
	// }
	// if (product.getIncrements() >
	// (Float.parseFloat(map.get("price").toString()) - product.getPrice())) {
	// return ResultGenerator.genFailResult("加价不能低于" + product.getIncrements());
	// }
	// if (StringUtil.isNotEmpty(product.getBidderid())) {
	// if (product.getBidderid().equals(user.getId())) {
	// return ResultGenerator.genFailResult("您已经竞拍过，请勿重复竞拍");
	// }
	// }
	// map.put("proid", productId);
	// map.put("bidderId", user.getId()); // 竞拍者id
	// map.put("currentBidder", user.getUserName()); // 竞拍者姓名
	// map.put("auctionStatus", "1"); // 竞拍状态
	// map.put("price", Float.parseFloat(map.get("price").toString())); //
	// 商品当前价格
	// productService.update(map, null); // 修改商品信息
	// return ResultGenerator.genSuccessResult("竞拍成功");
	// }

	/**
	 * 删除图片信息
	 * 
	 * @param id
	 * @return
	 */
	@LoginRequired
	@RequestMapping(value = "/delImag/{id}", method = RequestMethod.PUT)
	public Result delImg(@PathVariable String id) {
		return ResultGenerator.genSuccessResult(productService.delImg(id));
	}
	
	/**
	 * 物品交换申请
	 * 
	 * @param id
	 * @return
	 */
	@LoginRequired
	@RequestMapping(value = "/changePro", method = RequestMethod.POST)
	public Result changePro(@RequestBody Map<String, Object> map, @CurrentUser User user) {
		map.put("changeUserId", user.getId());
		Product curProduct = productService.findById(map.get("curProId").toString());
		if (!curProduct.getStatus().equals("0")) {
			return ResultGenerator.genFailResult("该物品正在交易中");
		}
		productService.changeProduct(map);
		return ResultGenerator.genSuccessResult("申请成功");
	}
}
