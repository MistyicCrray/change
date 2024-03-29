package com.barter.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barter.mapper.NewsMapper;
import com.barter.model.News;
import com.barter.tools.UUIDUtils;


@Service
public class NewsService {

	@Autowired
	private NewsMapper newsMapper;

	public int add(News news) {
		news.setId(UUIDUtils.get16UUID());
		news.setCreatedate(new Date());
		news.setUpdatedate(new Date());
		return newsMapper.insert(news);
	}

	public int delete(String id) {
		return newsMapper.deleteByPrimaryKey(id);
	}

	public Integer update(Map<String, Object> map) {
		return newsMapper.update(map);
	}
	
	public Integer update(News news) {
		news.setUpdatedate(new Date());
		return newsMapper.updateByPrimaryKey(news);
	}

	public List<News> findList(Map<String, Object> map) {
		return newsMapper.findList(map);
	}

	public News findById(String id) {
		return newsMapper.selectByPrimaryKey(id);
	}

}
