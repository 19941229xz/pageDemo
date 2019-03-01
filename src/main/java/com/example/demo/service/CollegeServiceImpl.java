package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.CollegeMapper;
import com.example.demo.model.College;


@Service
public class CollegeServiceImpl implements CollegeService {
	
	@Autowired
    CollegeMapper collegeMapper;

	@Override
	public Object searchWithPage(BaseReqParam<College> param) {
		List<College> collegeList = collegeMapper.search((College)param.getSearchParam());
		return collegeList;
	}
    
    @Override
	public List<College> search(College searchParam) {
		List<College> collegeList = collegeMapper.search(searchParam);
		return collegeList;
	}

	@Override
	public Object addOne(College addParam) {     
    	College collegeCon=new College();	
        collegeCon.setCollegeName(addParam.getCollegeName());
        if(search(collegeCon).size()>0) {
			throw new HttpException(HttpCode.NAME_REGISTERED).setMsg("学校名称已经被注册");
		}
		return collegeMapper.addOne(addParam);
	}

	@Override
	public College getOne(College searchParam) {
		return collegeMapper.getOne(searchParam);
	}


	@Override
	public Object delete(College deleteParam) {
		return collegeMapper.delete(deleteParam);
	}

	@Override
	public Object update(College updateParam) {
		return collegeMapper.update(updateParam);
	}



}