package com.example.demo.common.util;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {
	
	
	/**
	 * get 请求
	 * @param url
	 * @param params
	 * @return
	 * @throws JSONException 
	 */
	public static JSONObject get(String url, Map<String, String> params) throws JSONException{
        RestTemplate client = new RestTemplate();
        int i=0;
        for (Map.Entry<String, String> entry : params.entrySet()) { 
        	  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
        	  String str=(entry.getKey()+"="+entry.getValue());
        	  if(i==0) {
        		  url+=("?"+str);
        	  }else {
        		  url+=("&"+str);
        	  }
        	  i++;
        }
        //将请求头部和参数合成一个请求
        //执行HTTP请求，将返回的结构使用ResultVO类格式化   (先用string接受  然后再转成jsonobject)
        System.out.println(url);
        ResponseEntity<String> response = client.getForEntity(url,String.class);
        return new JSONObject(response.getBody());
    }
	
	
	public static void main(String[] args) throws JSONException {
		String appid="wx7d177a566047c728";
		String secret="81fffabade65a397ab9fc2c00beb4637";
		String js_code="02375kB80KgcqG1u5yA80gArB8075kBJ";
		String grant_type="authorization_code";
		
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("appid", appid);
		map.put("secret", secret);
		map.put("js_code", js_code);
		map.put("grant_type", grant_type);
		
		JSONObject json=get("https://api.weixin.qq.com/sns/jscode2session", map);
		System.out.println(json.get("openid"));
	}
     
}