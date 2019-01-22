package com.example.demo.common.auth;

import org.apache.shiro.authc.AuthenticationToken;
 
/**
 * @author xiongzh
 * @create 2018-07-12 15:19
 **/
public class JwtToken implements AuthenticationToken {
 
	private static final long serialVersionUID = 407844054259208373L;
	
	private String token;
 
    public JwtToken(String token) {
        this.token = token;
    }
 
    @Override
    public Object getPrincipal() {
        return token;
    }
 
    @Override
    public Object getCredentials() {
        return token;
    }
}

