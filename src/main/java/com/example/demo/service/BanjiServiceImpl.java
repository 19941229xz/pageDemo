package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.BanjiMapper;
import com.example.demo.model.Banji;


@Service
public class BanjiServiceImpl implements BanjiService {
	
	@Autowired
    BanjiMapper banjiMapper;

	@Override
	public Object searchWithPage(BaseReqParam<Banji> param) {
		List<Banji> banjiList = banjiMapper.search((Banji)param.getSearchParam());
		return banjiList;
	}
    
    @Override
	public List<Banji> search(Banji searchParam) {
		List<Banji> banjiList = banjiMapper.search(searchParam);
		return banjiList;
	}

	@Override
	public Object addOne(Banji addParam) {     
    	Banji banjiCon=new Banji();	
        banjiCon.setBanjiName(addParam.getBanjiName());
        if(search(banjiCon).size()>0) {
			throw new HttpException(HttpCode.NAME_REGISTERED).setMsg("班级名称已经被注册");
		}
		return banjiMapper.addOne(addParam);
	}

	@Override
	public Banji getOne(Banji searchParam) {
		return banjiMapper.getOne(searchParam);
	}


	@Override
	public Object delete(Banji deleteParam) {
		return banjiMapper.delete(deleteParam);
	}

	@Override
	public Object update(Banji updateParam) {
		return banjiMapper.update(updateParam);
	}



}