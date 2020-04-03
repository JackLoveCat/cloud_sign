package cn.edu.fzu.cloudsign.framework.security.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import cn.edu.fzu.cloudsign.common.constant.Constants;
import cn.edu.fzu.cloudsign.common.exception.CustomException;
import cn.edu.fzu.cloudsign.common.exception.user.UserPasswordNotMatchException;
import cn.edu.fzu.cloudsign.common.utils.MessageUtils;
import cn.edu.fzu.cloudsign.framework.manager.AsyncManager;
import cn.edu.fzu.cloudsign.framework.manager.factory.AsyncFactory;
import cn.edu.fzu.cloudsign.framework.redis.RedisCache;
import cn.edu.fzu.cloudsign.framework.security.LoginUser;

/**
 * 登录校验方法
 * 
 */
@Component
public class SysLoginService {
	@Autowired
	private TokenService tokenService;

	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	private RedisCache redisCache;

	/**
	 * 登录验证
	 * 
	 * TODO 验证码校验
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param captcha  验证码
	 * @param uuid     唯一标识
	 * @return 结果
	 */
	public String login(String username, String password, String code, String uuid) {

		/*
		 * 注掉验证码校验 String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid; String captcha
		 * = redisCache.getCacheObject(verifyKey); redisCache.deleteObject(verifyKey);
		 * if (captcha == null) {
		 * AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
		 * Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire"))); throw
		 * new CaptchaExpireException(); } if (!code.equalsIgnoreCase(captcha)) {
		 * AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
		 * Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error"))); throw
		 * new CaptchaException(); }
		 */

		// 用户验证
		Authentication authentication = null;
		try {
			// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			if (e instanceof BadCredentialsException) {
				AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
						MessageUtils.message("user.password.not.match")));
				throw new UserPasswordNotMatchException();
			} else {
				AsyncManager.me()
						.execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
				throw new CustomException(e.getMessage());
			}
		}
		AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS,
				MessageUtils.message("user.login.success")));
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		// 生成token
		return tokenService.createToken(loginUser);
	}
}
