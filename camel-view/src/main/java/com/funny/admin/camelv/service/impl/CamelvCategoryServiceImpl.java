package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvCategoryDao;
import com.funny.admin.camelv.service.ICamelvCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CamelvCategoryServiceImpl implements ICamelvCategoryService {
	@Resource
	private CamelvCategoryDao camelvCategoryDao;

}
