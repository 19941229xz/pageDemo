package com.example.demo.common.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
 
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.SystemConfig;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;

@Service
public class JwtService {
	
	
	@Autowired
	private SystemConfig systemConfig;
	
	@Autowired
	UserService userService;
	
	 /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public boolean verify(String token, String username, String secret) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
 
    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public String getUsername(String token) {
    	if(StringUtils.isEmpty(token)) {
    		throw new AuthenticationException("token为空");
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    
    /**
     * 根据token中的username 获取完整用户信息
     *
     * @return token中包含的用户名
     */
    public User getUserInfo() {
      RequestAttributes ra = RequestContextHolder.getRequestAttributes();
      ServletRequestAttributes sra = (ServletRequestAttributes) ra;
      HttpServletRequest request = sra.getRequest();
//      System.out.println(request.getHeader("Authorization"));
      String token=request.getHeader("Authorization");
    	if(StringUtils.isEmpty(token)) {
    		throw new AuthenticationException("token为空");
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            String username= jwt.getClaim("username").asString();
            User userCon=new User();
            userCon.setUsername(username);
            List<User> userList=userService.search(userCon);
            if(userList!=null&&userList.size()==1) {
            	return userService.search(userCon).get(0);
            }else {
            	throw new AuthenticationException("该账号不存在");
            }
        } catch (JWTDecodeException e) {
            return null;
        }
    }
 
    /**
     * 生成签名,过期时间在systemConfig配置中查看
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + systemConfig.getTokeExpireTime());
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
 
    }

}





 
