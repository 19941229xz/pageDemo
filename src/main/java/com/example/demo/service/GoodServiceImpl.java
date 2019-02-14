package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.GoodMapper;
import com.example.demo.model.Good;


@Service
public class GoodServiceImpl implements GoodService {
	
	@Autowired
    GoodMapper goodMapper;

	@Override
	public Object searchWithPage(BaseReqParam<Good> param) {
		List<Good> goodList = goodMapper.search((Good)param.getSearchParam());
		return goodList;
	}
    
    @Override
	public List<Good> search(Good searchParam) {
		List<Good> goodList = goodMapper.search(searchParam);
		return goodList;
	}

	@Override
	public Object addOne(Good addParam) {     
    	Good goodCon=new Good();	
		return goodMapper.addOne(addParam);
	}

	@Override
	public Good getOne(Good searchParam) {
		return goodMapper.getOne(searchParam);
	}


	@Override
	public Object delete(Good deleteParam) {
		return goodMapper.delete(deleteParam);
	}

	@Override
	public Object update(Good updateParam) {
		return goodMapper.update(updateParam);
	}



}