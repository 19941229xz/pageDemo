package com.example.demo.common;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.HttpResponse;
import com.example.demo.common.auth.JwtService;
import com.example.demo.common.req.LoginParam;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value="LoginControllerApi",description="登陆接口详细描述")
@RestController
@RequestMapping("login")
public class LoginController{

	@Autowired
    private UserService userService;
	
	@Autowired
    private JwtService jwtService;

	@ApiOperation(value = "/user",notes = "普通登陆")
    @PostMapping("/user")
    public Object user(@RequestBody @Valid LoginParam loginParam){
		//TODO need fix
		try {
			User user=new User();
			String loginUsername=loginParam.getUsername();
			String loginPassword=loginParam.getPassword();
			user.setPassword(loginPassword);
			user.setUsername(loginUsername);
			
					
			
			List<User> userList= userService.search(user);
			if(userList.size()==1) {
				User userRes=userList.get(0);
				if((userRes.getPassword().equals(loginPassword))&&
						(userRes.getUsername().equals(loginUsername))) {
					return HttpResponse.success(jwtService.sign( loginUsername, loginPassword));
				}else {
					return HttpResponse.error(new HttpException(HttpCode.LOGIN_FAIL).setMsg("用户名或密码错误"));
				}
			}else {
				return HttpResponse.error(new HttpException(HttpCode.LOGIN_FAIL).setMsg("用户名或密码错误"));
			}
		} catch (Exception e) {
			throw new HttpException(HttpCode.LOGIN_FAIL);
		}
    }

	
}

