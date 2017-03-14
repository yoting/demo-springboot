package com.gusi.demo.controller;

import java.util.Date;

import javax.servlet.ServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gusi.demo.utils.KeyUtil;
import com.gusi.demo.utils.TokenUtil;

@RestController
public class CommonController {
	@RequestMapping("/")
	public String index() {
		return "hello spring boot!";
	}

	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public @ResponseBody Object accessToken(@RequestParam String username, @RequestParam String password, ServletRequest request) {

		// 使用Token工具类得到token
		Date expiry = new Date(System.currentTimeMillis() + 360000L);
		String jwtString = TokenUtil.getJWTString(username, expiry, KeyUtil.getKey(request.getServletContext()));

		// 这是token的实体化类，用来返回给用户
		Token token = new Token();

		token.setAuthToken(jwtString);
		token.setExpires("" + expiry.getTime());

		return token;
	}

}

class Token {
	private String authToken;
	private String expires;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

}