package com.example.demo.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.HttpResponse;
import com.example.demo.common.auth.JwtService;
import com.example.demo.common.req.LoginParam;
import com.example.demo.common.req.StudentRegParam;
import com.example.demo.common.util.HttpUtil;
import com.example.demo.common.util.UUIDUtil;
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

@Api(value = "RegisterController", description = "注册接口详细描述")
@RestController
@RequestMapping("register")
public class RegisterController {

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

	@ApiOperation(value = "/student", notes = "学生注册")
	@PostMapping("/student")
	public Object user(@RequestBody @Valid StudentRegParam param) {
		// validate wxaccount is related??
		WxAccount wxAccountValid = new WxAccount();
		wxAccountValid.setAppid(param.getAppid());
		wxAccountValid.setOpenid(param.getOpenid());
		wxAccountValid.setIsDeleted(0);
		List<WxAccount> wxAccountList = wxAccountService.search(wxAccountValid);
		if (wxAccountList.size() == 0) {
			throw new HttpException(HttpCode.BAD_PARAM).setMsg("微信账号错误或者未登陆");
		} else if (wxAccountList.size() == 1) {
			if (!StringUtils.isEmpty(wxAccountList.get(0).getUserId())) { // 已经绑定的情况
//						User con=new User();
//						con.setId(wxAccountList.get(0).getUserId());
//						return HttpResponse.success(jwtService.sign(userService.getOne(con).getUsername(), 
//								userService.getOne(con).getPassword()));
				throw new HttpException(HttpCode.BAD_PARAM).setMsg("微信账号已经绑定系统账号");
			}
		} else if (wxAccountList.size() == 2) {
			if (!StringUtils.isEmpty(wxAccountList.get(0).getUserId())
					|| !StringUtils.isEmpty(wxAccountList.get(1).getUserId())) {
				throw new HttpException(HttpCode.BAD_PARAM).setMsg("微信账号已经绑定系统账号");
			}
		}

		// add user to DB
		User user = new User();
		user.setUsername(param.getUsername());

		// validate username is exist??
		List<User> userList = userService.search(user);
		if (userList.size() >= 1) {
			throw new HttpException(HttpCode.NAME_REGISTERED);
		} else {
//			user.setPassword(param.getPassword());
			// aop not intercepet need generate some param which are not be null
			Date now = new Date();
			user.setCreateTime(now);
			user.setIsDeleted(0);
			user.setUpdateLastTime(now);
			user.setId(UUIDUtil.uuid());
			int num = (int) userService.addOne(user);
			// if add success related to wxAccount
			if (num == 1) {
				User userSearchCon = new User();// user search param
				userSearchCon.setUsername(param.getUsername());
				userSearchCon.setIsDeleted(0);
				List<User> userList2 = userService.search(userSearchCon);

				User userRes = userList2.get(0);
				String userId = userRes.getId(); // get user id

				// related to wxAccount
				WxAccount wxAccount = new WxAccount();
				wxAccount.setOpenid(param.getOpenid());
				wxAccount.setAppid(param.getAppid());
//							wxAccount.setUserId(userId);

				wxAccountService.delete(wxAccount);
				wxAccount.setUserId(userId);
				wxAccount.setCreateTime(now);
				wxAccount.setUpdateLastTime(now);
				wxAccount.setIsDeleted(0);
				int num2 = (int) wxAccountService.addOne(wxAccount);
				if (num2 == 1) {
//					return HttpResponse.success(
//							jwtService.sign(param.getUsername(), param.getPassword()));
				} else {
					return HttpResponse.fail(new HttpException(HttpCode.DIRTY_DATA_IN_DB), "绑定失败");
				}
			} else {
				return HttpResponse.fail(new HttpException(HttpCode.DIRTY_DATA_IN_DB), "绑定失败");
			}
		}
		return null;
	}

}
