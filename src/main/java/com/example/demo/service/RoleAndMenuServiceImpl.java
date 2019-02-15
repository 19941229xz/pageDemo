package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.RoleAndMenuMapper;
import com.example.demo.model.RoleAndMenu;


@Service
public class RoleAndMenuServiceImpl implements RoleAndMenuService {
	
	@Autowired
    RoleAndMenuMapper roleAndMenuMapper;

	@Override
	public Object searchWithPage(BaseReqParam<RoleAndMenu> param) {
		List<RoleAndMenu> roleAndMenuList = roleAndMenuMapper.search((RoleAndMenu)param.getSearchParam());
		return roleAndMenuList;
	}
    
    @Override
	public List<RoleAndMenu> search(RoleAndMenu searchParam) {
		List<RoleAndMenu> roleAndMenuList = roleAndMenuMapper.search(searchParam);
		return roleAndMenuList;
	}

	@Override
	public Object addOne(RoleAndMenu addParam) {     
    	RoleAndMenu roleAndMenuCon=new RoleAndMenu();	
		return roleAndMenuMapper.addOne(addParam);
	}

	@Override
	public RoleAndMenu getOne(RoleAndMenu searchParam) {
		return roleAndMenuMapper.getOne(searchParam);
	}


	@Override
	public Object delete(RoleAndMenu deleteParam) {
		return roleAndMenuMapper.delete(deleteParam);
	}

	@Override
	public Object update(RoleAndMenu updateParam) {
		return roleAndMenuMapper.update(updateParam);
	}



}