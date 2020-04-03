package cn.edu.fzu.cloudsign.project.system.domain;

import javax.validation.constraints.NotBlank;

/**
 * 登录认证对象
 * 
 * @author fanxu
 *
 */
public class UserIdentifier {
	private static final long serialVersionUID = 1L;

	private String account;
	private String passward;

	@NotBlank(message = "输入账号不能为空")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@NotBlank(message = "输入密码不能为空")
	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

}
