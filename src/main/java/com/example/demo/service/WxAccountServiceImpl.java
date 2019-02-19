package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.WxAccountMapper;
import com.example.demo.model.WxAccount;


@Service
public class WxAccountServiceImpl implements WxAccountService {
	
	@Autowired
    WxAccountMapper wxAccountMapper;

	@Override
	public Object searchWithPage(BaseReqParam<WxAccount> param) {
		List<WxAccount> wxAccountList = wxAccountMapper.search((WxAccount)param.getSearchParam());
		return wxAccountList;
	}
    
    @Override
	public List<WxAccount> search(WxAccount searchParam) {
		List<WxAccount> wxAccountList = wxAccountMapper.search(searchParam);
		return wxAccountList;
	}

	@Override
	public Object addOne(WxAccount addParam) {     
    	WxAccount wxAccountCon=new WxAccount();	
		return wxAccountMapper.addOne(addParam);
	}

	@Override
	public WxAccount getOne(WxAccount searchParam) {
		return wxAccountMapper.getOne(searchParam);
	}


	@Override
	public Object delete(WxAccount deleteParam) {
		return wxAccountMapper.delete(deleteParam);
	}

	@Override
	public Object update(WxAccount updateParam) {
		return wxAccountMapper.update(updateParam);
	}



}