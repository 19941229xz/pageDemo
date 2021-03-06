package com.example.demo.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	static int points1705=0;
	
	static int points1706=0;
	
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
	
	
	@ApiOperation(value = "/addPoint", notes = "玩家点击一次加一分")
	@PostMapping("/addPoint")
	public Object addPoint(GameUser user) {
		
		if(user.getBanji().equals("1706")) {
			GameController.points1706+=1;
		}else if(user.getBanji().equals("1705")) {
			GameController.points1705+=1;
		}else {
			return HttpResponse.success("班级不存在");
		}
		
		
		return HttpResponse.success("加分成功");
		
	}
	
	
	@ApiOperation(value = "/watchPoint", notes = "c查看分数情况")
	@PostMapping("/watchPoint")
	public Object watchPoint() {
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		
		map.put("points1705", points1705);
		map.put("points1706", points1706);
		
		return HttpResponse.success(map);
		
	}
		
	
	

}
