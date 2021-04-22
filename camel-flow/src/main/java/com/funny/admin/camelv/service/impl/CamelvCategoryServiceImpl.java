package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvCategoryDao;
import com.funny.admin.camelv.service.ICamelvCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CamelvCategoryServiceImpl implements ICamelvCategoryService {
	@Autowired
	private CamelvCategoryDao camelvCategoryDao;
	
}