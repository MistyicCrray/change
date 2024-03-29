package com.barter.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.barter.mapper.BannerMapper;
import com.barter.model.Banner;
import com.barter.tools.FileUtil;
import com.barter.tools.ServiceException;
import com.barter.tools.UUIDUtils;


@Service
public class BannerService {

	@Autowired
	private BannerMapper bannerMapper;

	public String add(Banner banner, MultipartFile image) {
		banner.setId(UUIDUtils.get16UUID());
		banner.setCreateDate(new Date());
		if (image != null) {
			banner.setImage((String) FileUtil.uploadImage(image).get("filePath"));
		}
		int i = bannerMapper.insert(banner);
		if (i > 0) {
			return "添加成功";
		} else {
			throw new ServiceException("添加失败");
		}
	}

	public String delete(String id) {
		int i = bannerMapper.deleteByPrimaryKey(id);
		if (i > 0) {
			return "删除成功";
		} else {
			throw new ServiceException("删除失败");
		}
	}

	public String update(Banner banner, MultipartFile image) {
		if (image != null) {
			banner.setImage((String) FileUtil.uploadImage(image).get("filePath"));
		}
		int i = bannerMapper.updateByPrimaryKey(banner);
		if (i > 0) {
			return "修改成功";
		} else {
			throw new ServiceException("修改失败");
		}
	}

	public Banner find(String id) {
		return bannerMapper.selectByPrimaryKey(id);
	}

	public List<Banner> findList(Map<String, Object> map) {
		return bannerMapper.findList(map);
	}

	public String update(Map<String, Object> map, MultipartFile image) {
		map.put("updateDate", new Date());
		if (image != null) {
			map.put("image", (String) FileUtil.uploadImage(image).get("filePath"));
		} else {
			map.put("image", null);
		}
		int i = bannerMapper.update(map);
		if (i > 0) {
			return "修改成功";
		} else {
			throw new ServiceException("修改失败");
		}
	}

	// 定时任务
	/*
	 * @Scheduled(cron = "* * * * * MON-SAT") public void hello() {
	 * System.out.println("打印"); }
	 */
}
