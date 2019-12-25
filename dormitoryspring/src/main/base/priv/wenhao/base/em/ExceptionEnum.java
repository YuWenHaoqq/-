package priv.wenhao.base.em;

import lombok.Data;

/**
 * Description: 异常枚举类
 * Author: yuWenHao
 * Date: 2019/12/17
 */

public enum  ExceptionEnum {

	SUCCESS(0,"请求成功"),
	QUERYFAILED(1,"查询失败"),
	INSERTFAILED(2,"新增失败"),
	LOGINFAILED(3,"登录错误"),
	CHECKLOGIN(4,"凭证过期"),
	AESKEYFAILED(5,"密钥错误"),
	UNKNOW(-1,"未知异常,请连续管理员");
	private int code;
	private String message;

	ExceptionEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
