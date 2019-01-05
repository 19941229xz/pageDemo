package com.example.demo.service;

public interface UserService {

	
	Object findUserListWithPage(int page ,int pageSize);

	Object findUserList(int page, int size);
	
}
