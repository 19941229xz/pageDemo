package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.EmailMapper;
import com.example.demo.model.Email;


@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    EmailMapper emailMapper;

	@Override
	public Object searchWithPage(BaseReqParam<Email> param) {
		List<Email> emailList = emailMapper.search((Email)param.getSearchParam());
		return emailList;
	}

	@Override
	public Object addOne(Email addParam) {
		return emailMapper.addOne(addParam);
	}

	@Override
	public Email getOne(Email searchParam) {
		return emailMapper.getOne(searchParam);
	}


	@Override
	public Object delete(Email deleteParam) {
		return emailMapper.delete(deleteParam);
	}

	@Override
	public Object update(Email updateParam) {
		return emailMapper.update(updateParam);
	}



}