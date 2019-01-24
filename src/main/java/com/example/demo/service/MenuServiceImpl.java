package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
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
	public List<Menu> search(Menu searchParam) {
		List<Menu> menuList = menuMapper.search(searchParam);
		return menuList;
	}

	@Override
	public Object addOne(Menu addParam) {     
    	Menu menuCon=new Menu();	
        menuCon.setMenuName(addParam.getMenuName());
        if(search(menuCon).size()>0) {
			throw new HttpException(HttpCode.NAME_REGISTERED).setMsg("菜单名称已经被注册");
		}
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