package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.GoodOrderMapper;
import com.example.demo.model.GoodOrder;


@Service
public class GoodOrderServiceImpl implements GoodOrderService {
	
	@Autowired
    GoodOrderMapper goodOrderMapper;

	@Override
	public Object searchWithPage(BaseReqParam<GoodOrder> param) {
		List<GoodOrder> goodOrderList = goodOrderMapper.search((GoodOrder)param.getSearchParam());
		return goodOrderList;
	}
    
    @Override
	public List<GoodOrder> search(GoodOrder searchParam) {
		List<GoodOrder> goodOrderList = goodOrderMapper.search(searchParam);
		return goodOrderList;
	}

	@Override
	public Object addOne(GoodOrder addParam) {     
    	GoodOrder goodOrderCon=new GoodOrder();	
		return goodOrderMapper.addOne(addParam);
	}

	@Override
	public GoodOrder getOne(GoodOrder searchParam) {
		return goodOrderMapper.getOne(searchParam);
	}


	@Override
	public Object delete(GoodOrder deleteParam) {
		return goodOrderMapper.delete(deleteParam);
	}

	@Override
	public Object update(GoodOrder updateParam) {
		return goodOrderMapper.update(updateParam);
	}



}