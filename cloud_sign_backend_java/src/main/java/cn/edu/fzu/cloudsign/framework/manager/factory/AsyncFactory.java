package cn.edu.fzu.cloudsign.framework.manager.factory;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.fzu.cloudsign.common.constant.Constants;
import cn.edu.fzu.cloudsign.common.utils.LogUtils;
import cn.edu.fzu.cloudsign.common.utils.ServletUtils;
import cn.edu.fzu.cloudsign.common.utils.ip.IpUtils;
import cn.edu.fzu.cloudsign.common.utils.spring.SpringUtils;
import cn.edu.fzu.cloudsign.project.monitor.domain.SysLogininfor;
import cn.edu.fzu.cloudsign.project.monitor.service.ISysLogininforService;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 异步工厂（产生任务用）
 * 
 */
public class AsyncFactory {
	private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

	/**
	 * 记录登陆信息
	 * 
	 * @param username 用户名
	 * @param status   状态
	 * @param message  消息
	 * @param args     列表
	 * @return 任务task
	 */
	public static TimerTask recordLogininfor(final String username, final String status, final String message,
			final Object... args) {
		final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
		final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
		return new TimerTask() {
			@Override
			public void run() {
				StringBuilder s = new StringBuilder();
				s.append(LogUtils.getBlock(ip));
				s.append(LogUtils.getBlock(username));
				s.append(LogUtils.getBlock(status));
				s.append(LogUtils.getBlock(message));
				// 打印信息到日志
				sys_user_logger.info(s.toString(), args);
				// 获取客户端操作系统
				String os = userAgent.getOperatingSystem().getName();
				// 获取客户端浏览器
				String browser = userAgent.getBrowser().getName();
				// 封装对象
				SysLogininfor logininfor = new SysLogininfor();
				logininfor.setUserName(username);
				logininfor.setIpaddr(ip);
				logininfor.setBrowser(browser);
				logininfor.setOs(os);
				logininfor.setMsg(message);
				// 日志状态
				if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
					logininfor.setStatus(Constants.SUCCESS);
				} else if (Constants.LOGIN_FAIL.equals(status)) {
					logininfor.setStatus(Constants.FAIL);
				}
				// 插入数据
				SpringUtils.getBean(ISysLogininforService.class).insertLogininfor(logininfor);
			}
		};
	}

}
