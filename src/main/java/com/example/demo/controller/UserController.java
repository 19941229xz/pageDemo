package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("UserController相关api")
@RestController
@RequestMapping("user")
public class UserController implements BaseController<User>{
    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询user的信息",notes = "")
//    @ApiImplicitParam(name ="username",value = "账号",paramType = "path",required = true,dataType = "String")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "用户id",dataType = "String",paramType = "query",example = "1112")
//    })
    @ApiResponses({
            @ApiResponse(code=400,message = "请求参数没有填好"),
            @ApiResponse(code=404,message="请求路径没有找到")
    })        
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<User> param){
        return userService.searchWithPage( param);
    }

    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<User> param) {
		// TODO Auto-generated method stub
		return userService.addOne((User)param.getAddParam());
	}

	@Override
	public Object getOne(BaseReqParam<User> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object delete(BaseReqParam<User> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object update(BaseReqParam<User> param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
    
}
