package com.gusi.demo.intercept;

import java.security.Key;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.TokenIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gusi.demo.utils.KeyUtil;
import com.gusi.demo.utils.TokenUtil;

public class TokenInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(TokenIterator.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 获取头信息中的token
		String authorizationHeader = request.getHeader("auth_token");
		// 如果token为空抛出
		if (authorizationHeader == null) {

			throw new IllegalArgumentException("token is illegal!");// 抛出未认证的错误
		}
		// 把Bearer Token换成Token
		Key key = KeyUtil.getKey(request.getServletContext());
		String strToken = TokenUtil.extractJwtTokenFromAuthorizationHeader(authorizationHeader);
		if (TokenUtil.isValid(strToken, key)) {
			String name = TokenUtil.getName(strToken, key);// 反解出Name
			String[] roles = TokenUtil.getRoles(strToken, key);// 反解出角色
			int version = TokenUtil.getVersion(strToken, key);// 得到版本
			if (name != null && roles.length != 0 && version != -1) {
				// User user=userservice.findUserByTel(name);
				if (name != null) {
					// containerRequestContext.setSecurityContext(new
					// SecurityContextAuthorizer(uriInfo,new
					// AuthorPricinple(name), new String[]{"user"}));
					return true;
				}
			} else {
				logger.info("User not found " + name);
			}

			throw new IllegalArgumentException("");
		} else {
			logger.info("token is invalid!");
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
