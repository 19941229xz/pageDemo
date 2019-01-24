package com.example.demo.common.auth;


import com.example.demo.service.UserService;
import com.example.demo.model.User;
import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.auth.JwtService;
import com.example.demo.common.base.BaseReqParam;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
 
/**
 * @author xiongzh
 * @create 2018-07-12 15:23
 **/
@Component
public class Realm extends AuthorizingRealm {
 
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtService JwtUtil;
 
 
    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
 
    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JwtUtil.getUsername(principals.toString());
        User userCon=new User();
        userCon.setUsername(username);
        BaseReqParam<User> baseParam=new BaseReqParam<User>();
        baseParam.setSearchParam(userCon);
        User userRes = (User)userService.searchWithPage(baseParam);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }
 
    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token无效");
        }
 
        User userCon=new User();
        userCon.setUsername(username);
        User userRes = userService.search(userCon).get(0);
        if (userRes == null) {
            throw new AuthenticationException("用户不存在!");
        }
 
        if (!JwtUtil.verify(token, username, userRes.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }
 
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}

