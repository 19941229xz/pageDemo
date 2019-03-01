package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.UserAndBanjiMapper;
import com.example.demo.model.UserAndBanji;


@Service
public class UserAndBanjiServiceImpl implements UserAndBanjiService {
	
	@Autowired
    UserAndBanjiMapper userAndBanjiMapper;

	@Override
	public Object searchWithPage(BaseReqParam<UserAndBanji> param) {
		List<UserAndBanji> userAndBanjiList = userAndBanjiMapper.search((UserAndBanji)param.getSearchParam());
		return userAndBanjiList;
	}
    
    @Override
	public List<UserAndBanji> search(UserAndBanji searchParam) {
		List<UserAndBanji> userAndBanjiList = userAndBanjiMapper.search(searchParam);
		return userAndBanjiList;
	}

	@Override
	public Object addOne(UserAndBanji addParam) {     
    	UserAndBanji userAndBanjiCon=new UserAndBanji();	
		return userAndBanjiMapper.addOne(addParam);
	}

	@Override
	public UserAndBanji getOne(UserAndBanji searchParam) {
		return userAndBanjiMapper.getOne(searchParam);
	}


	@Override
	public Object delete(UserAndBanji deleteParam) {
		return userAndBanjiMapper.delete(deleteParam);
	}

	@Override
	public Object update(UserAndBanji updateParam) {
		return userAndBanjiMapper.update(updateParam);
	}



}