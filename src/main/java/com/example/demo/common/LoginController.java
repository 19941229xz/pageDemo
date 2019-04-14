package com.example.demo.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.HttpResponse;
import com.example.demo.common.auth.JwtService;
import com.example.demo.common.req.LoginParam;
import com.example.demo.common.req.WeixinRegisterParam;
import com.example.demo.common.req.WexinLoginParam;
import com.example.demo.common.util.HttpUtil;
import com.example.demo.common.util.UUIDUtil;
import com.example.demo.model.Daka;
import com.example.demo.model.Menu;
import com.example.demo.model.RoleAndMenu;
import com.example.demo.model.User;
import com.example.demo.model.WxAccount;
import com.example.demo.service.MenuService;
import com.example.demo.service.RoleAndMenuService;
import com.example.demo.service.UserService;
import com.example.demo.service.WxAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value="LoginControllerApi",description="登陆接口详细描述")
@RestController
@RequestMapping("login")
@CrossOrigin
public class LoginController{
	
	private List<Daka> dakaList = new ArrayList<Daka>();

	@Autowired
    private UserService userService;
	
	@Autowired
    private WxAccountService wxAccountService;
	
	@Autowired
    private RoleAndMenuService roleAndMenuService;
	
	@Autowired
    private MenuService menuService;
	
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
		}else if(wxAccountList.size()>2) {
			throw new HttpException(HttpCode.DIRTY_DATA_IN_DB);
		}else if(wxAccountList.size()==1&&StringUtils.isEmpty(wxAccountList.get(0).getUserId())) {
			//item exist but  userId is null
			return HttpResponse.fail(new HttpException(HttpCode.NO_RELATED_TO_MAIN_ACCOUNT), openid);
		}else if(wxAccountList.size()==1&&!StringUtils.isEmpty(wxAccountList.get(0).getUserId())){
			User user=new User();
			user.setId(wxAccountList.get(0).getUserId());
			User userRes=userService.getOne(user);
			return HttpResponse.success(jwtService.sign( userRes.getUsername(), userRes.getPassword()));
		}else {
			User user=new User();
			user.setId(!StringUtils.isEmpty(wxAccountList.get(0).getUserId())?wxAccountList.get(0).getUserId():
				wxAccountList.get(1).getUserId());
			User userRes=userService.getOne(user);
			return HttpResponse.success(jwtService.sign( userRes.getUsername(), userRes.getPassword()));
		}
		
		
	}
	
	@ApiOperation(value = "/weixin/register",notes = "微信小程序登陆（需要code appid secret）")
    @PostMapping("/weixin/register")
    public Object weixinRegister(@RequestBody @Valid WeixinRegisterParam weixinRegisterParam){
		//validate wxaccount is related??
		WxAccount wxAccountValid=new WxAccount();
		wxAccountValid.setAppid(weixinRegisterParam.getAppid());
		wxAccountValid.setOpenid(weixinRegisterParam.getOpenid());
		wxAccountValid.setIsDeleted(0);
		List<WxAccount> wxAccountList=wxAccountService.search(wxAccountValid);
		if(wxAccountList.size()==0) {
			throw new HttpException(HttpCode.BAD_PARAM).setMsg("微信账号错误或者未登陆");
		}else if(wxAccountList.size()==1) {
			if(!StringUtils.isEmpty(wxAccountList.get(0).getUserId())) { // 已经绑定的情况
//				User con=new User();
//				con.setId(wxAccountList.get(0).getUserId());
//				return HttpResponse.success(jwtService.sign(userService.getOne(con).getUsername(), 
//						userService.getOne(con).getPassword()));
				throw new HttpException(HttpCode.BAD_PARAM).setMsg("微信账号已经绑定系统账号");
			}
		}else if(wxAccountList.size()==2){
			if(!StringUtils.isEmpty(wxAccountList.get(0).getUserId())||
					!StringUtils.isEmpty(wxAccountList.get(1).getUserId())) {
				throw new HttpException(HttpCode.BAD_PARAM).setMsg("微信账号已经绑定系统账号");
			}
		}
	
		//add  user to DB
		User user = new User();
		user.setUsername(weixinRegisterParam.getUsername());
		
		//validate username is  exist??
		List<User> userList=userService.search(user);
		if(userList.size()>=1) {
			throw new HttpException(HttpCode.NAME_REGISTERED);
		}else {
			user.setPassword(weixinRegisterParam.getPassword());
			//aop not intercepet   need generate some param which are not be null
			Date now=new Date();
			user.setCreateTime(now);
			user.setIsDeleted(0);
			user.setUpdateLastTime(now);
			user.setId(UUIDUtil.uuid());
			int num=(int)userService.addOne(user);
				// if add success related to wxAccount
				if(num==1) {
					User userSearchCon=new User();// user search param
					userSearchCon.setUsername(weixinRegisterParam.getUsername());
					userSearchCon.setIsDeleted(0);
					List<User> userList2=userService.search(userSearchCon);
					
					User userRes=userList2.get(0);
					String userId=userRes.getId(); // get user id
					
					//related to wxAccount
					WxAccount wxAccount=new WxAccount();
					wxAccount.setOpenid(weixinRegisterParam.getOpenid());
					wxAccount.setAppid(weixinRegisterParam.getAppid());
//					wxAccount.setUserId(userId);
					
					wxAccountService.delete(wxAccount);
					wxAccount.setUserId(userId);
					wxAccount.setCreateTime(now);
					wxAccount.setUpdateLastTime(now);
					wxAccount.setIsDeleted(0);
					int num2=(int)wxAccountService.addOne(wxAccount);
						if(num2==1) {
							return HttpResponse.success(jwtService.sign(weixinRegisterParam.getUsername(), 
									weixinRegisterParam.getPassword()));
						}else {
							return HttpResponse.fail(new HttpException(HttpCode.DIRTY_DATA_IN_DB), "绑定失败");
						}
				}else {
					return HttpResponse.fail(new HttpException(HttpCode.DIRTY_DATA_IN_DB), "绑定失败");
				}
		}
	}
	
	
	
	@ApiOperation(value = "/getUserInfo",notes = "获取用户完整信息（请求头中带token）")
    @PostMapping("/getUserInfo")
    public Object getUserInfo(){
		
		return HttpResponse.success(jwtService.getUserInfo());
	}
	
	@ApiOperation(value = "/getMenus",notes = "获取用户可以访问的菜单（请求头中带token）")
    @PostMapping("/getMenus")
    public Object getMenus(){
		
		String roleId=jwtService.getUserInfo().getRoleId();
		
		RoleAndMenu ram=new RoleAndMenu();
		ram.setRoleId(roleId);
		ram.setIsDeleted(0);
		List<RoleAndMenu> ramList=roleAndMenuService.search(ram);
		
		List<Menu> menuList=new ArrayList<Menu>();
		Menu tempMenu=null;
		Menu conMenu=new Menu();
		conMenu.setIsDeleted(0);
		for (int i = 0; i < ramList.size(); i++) {
			conMenu.setId(ramList.get(i).getMenuId());
			tempMenu=menuService.getOne(conMenu);
			menuList.add(tempMenu);
		}
		
		return HttpResponse.success(menuList);
	}
	
	
	@ApiOperation(value = "/daka",notes = "学生打卡接口")
    @PostMapping("/daka")
    public Object daka(@RequestBody Daka daka){
		
	String[] des = new String[] {"优雅地","帅气地","调皮地","潇洒地","漂亮地"};	
		
	for (Daka temp : dakaList) {
		if(temp.getName().equals(daka.getName())) {
			return HttpResponse.success("今天已经打过卡了");
		}
	}
		
		dakaList.add(daka);
		
		return HttpResponse.success("您"+des[new Random().nextInt(des.length-1)]+"打了个卡");
	}
	
	@ApiOperation(value = "/paihang",notes = "学生获取打卡排行接口")
    @PostMapping("/paihang")
    public Object paihang(){
		
		return HttpResponse.success(dakaList);
	}
	
	
}

