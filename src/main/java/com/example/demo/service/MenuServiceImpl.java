package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.MenuMapper;
import com.example.demo.model.Menu;


@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
    MenuMapper menuMapper;

	@Override
	public Object searchWithPage(BaseReqParam<Menu> param) {
		List<Menu> menuList = menuMapper.search((Menu)param.getSearchParam());
		return menuList;
	}

	@Override
	public Object addOne(Menu addParam) {
		return menuMapper.addOne(addParam);
	}

	@Override
	public Menu getOne(Menu searchParam) {
		return menuMapper.getOne(searchParam);
	}


	@Override
	public Object delete(Menu deleteParam) {
		return menuMapper.delete(deleteParam);
	}

	@Override
	public Object update(Menu updateParam) {
		return menuMapper.update(updateParam);
	}



}