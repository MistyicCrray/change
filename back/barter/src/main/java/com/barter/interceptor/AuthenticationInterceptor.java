package com.barter.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.barter.service.UserService;
import com.barter.tools.AuthorizationException;
import com.barter.tools.LoginRequired;
import com.barter.tools.TokenUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * 身份验证拦截器 
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
	public final static String ACCESS_TOKEN = "accessToken";
	@Resource
	UserService userService;

	// 在业务处理器处理请求之前被调用
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		// 判断接口是否需要登录
		LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);

		// 有 @LoginRequired 注解，需要认证
		if (methodAnnotation != null) {
			// 判断是否存在令牌信息，如果存在，则允许登录
			String accessToken = request.getHeader(ACCESS_TOKEN);// ("Authorization");
			if (null == accessToken) {
				throw new AuthorizationException("无token，请重新登录");
			} else if (accessToken.isEmpty()) {
				throw new AuthorizationException("token为空");
			} else {
				// 从Redis 中查看 token 是否过期
				Claims claims;
				try {
					claims = TokenUtil.parseJWT(accessToken);
				} catch (ExpiredJwtException e) {
					throw new AuthorizationException("token失效，请重新登录");
				} catch (SignatureException se) {
					throw new AuthorizationException("token令牌错误");
				} catch (MalformedJwtException e2) {
					throw new AuthorizationException("token令牌错误，格式不对");
				}
				String id = claims.getId();
				Object user = userService.findById(id);
				if (user == null) {
//					response.setStatus(401);
					throw new AuthorizationException("token无效，用户不存在，请重新登录");
				}
				// 当前登录用户@CurrentUser
				request.setAttribute(CurrentUserConstants.CURRENT_USER, user);
				return true;
			}

		} else {// 不需要登录可请求
			return true;
		}
	}

	// 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	// 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}
}
