package com.example.demo.common;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.req.GameLoginParam;
import com.example.demo.common.req.StudentRegParam;
import com.example.demo.model.GameUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "GameController", description = "游戏注册接口详细描述")
@RestController
@RequestMapping("game")
public class GameController {
	
	static List<GameUser>  gameUserList=new ArrayList<GameUser>();
	
	
	@ApiOperation(value = "/login", notes = "玩家注册和登陆")
	@PostMapping("/login")
	public Object login(GameLoginParam  param) {
		
		
		GameUser gu=new GameUser();
		
		gu.setName(param.getName());
		gu.setBanji(param.getBanji());
		gu.setStuId(param.getStuId());
		
		GameController.gameUserList.add(gu);
		
		return HttpResponse.success(gu);
		
		
	}
	
	@ApiOperation(value = "/watchGameUser", notes = "查看所有玩家")
	@PostMapping("/watchGameUser")
	public Object watchGameUser() {
		
		 
		
		
		return HttpResponse.success(GameController.gameUserList);
		
		
	}
		
	
	

}
