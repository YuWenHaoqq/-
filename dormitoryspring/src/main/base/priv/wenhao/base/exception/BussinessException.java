package priv.wenhao.base.exception;

import lombok.Getter;

@SuppressWarnings("warn")
@Getter
public class BussinessException extends Exception {
	private int code;
	private String message;

	public BussinessException() {
	}

	public BussinessException(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
