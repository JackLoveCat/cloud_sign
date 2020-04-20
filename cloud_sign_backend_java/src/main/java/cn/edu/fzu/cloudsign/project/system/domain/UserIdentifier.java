package cn.edu.fzu.cloudsign.project.system.domain;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录认证对象
 * 
 * @author fanxu
 *
 */
@ApiModel("登录认证对象")
public class UserIdentifier {
	private static final long serialVersionUID = 1L;

	private String account;
	private String passward;

	@ApiModelProperty(value = "用户账号、手机或邮箱", required = true)
	@NotBlank(message = "输入账号不能为空")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@ApiModelProperty(value = "密码", required = true)
	@NotBlank(message = "输入密码不能为空")
	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

}
