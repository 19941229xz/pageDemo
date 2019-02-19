package com.example.demo.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.HttpResponse;
import com.example.demo.common.auth.JwtService;
import com.example.demo.common.req.LoginParam;
import com.example.demo.common.req.WexinLoginParam;
import com.example.demo.common.util.HttpUtil;
import com.example.demo.model.User;
import com.example.demo.model.WxAccount;
import com.example.demo.service.UserService;
import com.example.demo.service.WxAccountService;

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
    private WxAccountService wxAccountService;
	
	@Autowired
    private JwtService jwtService;

	@ApiOperation(value = "/user",notes = "普通用户登陆（需要用户名和密码）")
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

	@ApiOperation(value = "/weixin",notes = "微信小程序登陆（需要code appid secret）")
    @PostMapping("/weixin")
    public Object weixin(@RequestBody @Valid WexinLoginParam wexinLoginParam){

		//先获取openid
		//参数准备
		Map<String,String> map=new HashMap<String,String>();
		map.put("appid", wexinLoginParam.getAppid());
		map.put("secret", wexinLoginParam.getSecret());
		map.put("js_code", wexinLoginParam.getCode());
		map.put("grant_type", "authorization_code");
		//resttamplate 请求微信服务器 获取openid
		String openid="";
		try {
			JSONObject json= HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", map);
			openid =json.getString("openid");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HttpException(HttpCode.AUTH_FAIL);
		}
		// 查询 wxAccount 是否存在
		// 准备 wxAccount search param
		WxAccount wxAccount=new WxAccount();
		wxAccount.setAppid(wexinLoginParam.getAppid());
		wxAccount.setOpenid(openid);
		List<WxAccount> wxAccountList=wxAccountService.search(wxAccount);
		//不存在的情况
		if(wxAccountList.size()==0) {
			//1,add the new item to DB (the transcation must be completed)
			int num=0;
			//这个方法不受切面拦截  需要手动加上参数
			Date now=new Date();
			wxAccount.setCreateTime(now);
			wxAccount.setIsDeleted(0);
			wxAccount.setUpdateLastTime(now);
			while(num==0) {
				num=(int)wxAccountService.addOne(wxAccount);
			}
			//2,notify front user to register a account  and  related to the wexin account
			return HttpResponse.fail(new HttpException(HttpCode.NO_RELATED_TO_MAIN_ACCOUNT), openid);
		}else if(wxAccountList.size()>1) {
			throw new HttpException(HttpCode.DIRTY_DATA_IN_DB);
		}else if(wxAccountList.size()==1&&StringUtils.isEmpty(wxAccountList.get(0).getUserId())) {
			//item exist but  userId is null
			return HttpResponse.fail(new HttpException(HttpCode.NO_RELATED_TO_MAIN_ACCOUNT), openid);
		}else {
			User user=new User();
			user.setId(wxAccountList.get(0).getUserId());
			User userRes=userService.getOne(user);
			return HttpResponse.success(jwtService.sign( userRes.getUsername(), userRes.getPassword()));
		}
		
		
	}
	
	
	
	
}

