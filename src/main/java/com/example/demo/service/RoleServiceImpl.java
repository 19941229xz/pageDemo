package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.RoleMapper;
import com.example.demo.model.Role;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
    RoleMapper roleMapper;

	@Override
	public Object searchWithPage(BaseReqParam<Role> param) {
		List<Role> roleList = roleMapper.search((Role)param.getSearchParam());
		return roleList;
	}
    
    @Override
	public List<Role> search(Role searchParam) {
		List<Role> roleList = roleMapper.search(searchParam);
		return roleList;
	}

	@Override
	public Object addOne(Role addParam) {     
    	Role roleCon=new Role();	
        roleCon.setRoleName(addParam.getRoleName());
        if(search(roleCon).size()>0) {
			throw new HttpException(HttpCode.NAME_REGISTERED).setMsg("角色名称已经被注册");
		}
		return roleMapper.addOne(addParam);
	}

	@Override
	public Role getOne(Role searchParam) {
		return roleMapper.getOne(searchParam);
	}


	@Override
	public Object delete(Role deleteParam) {
		return roleMapper.delete(deleteParam);
	}

	@Override
	public Object update(Role updateParam) {
		return roleMapper.update(updateParam);
	}



}