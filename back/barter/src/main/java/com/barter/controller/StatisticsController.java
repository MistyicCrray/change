package com.barter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barter.service.StatisticsService;
import com.barter.tools.Result;
import com.barter.tools.ResultGenerator;


/**
 * 统计
 * 
 * @author Administrator
 *
 */
@RestController
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping(value = "/user_statistics", method = RequestMethod.GET)
	public Result getUserQuantity() {
		return ResultGenerator.genSuccessResult(statisticsService.userQuantity());
	}

	@RequestMapping(value = "/product_statistics", method = RequestMethod.GET)
	public Result getProductQuantity() {
		return ResultGenerator.genSuccessResult(statisticsService.productQuantity());
	}

	@RequestMapping(value = "/order_statistics", method = RequestMethod.GET)
	public Result getOrderQuantity() {
		return ResultGenerator.genSuccessResult(statisticsService.orderQuantity());
	}

	@RequestMapping(value = "/order_payment", method = RequestMethod.GET)
	public Result getOrderPayment() {
		return ResultGenerator.genSuccessResult(statisticsService.orderPayMent());
	}

	@RequestMapping(value = "/collection_statistics", method = RequestMethod.GET)
	public Result getCollectionStatistics(@RequestParam String productid) {
		return ResultGenerator.genSuccessResult(statisticsService.getCollection(productid));
	}
}
