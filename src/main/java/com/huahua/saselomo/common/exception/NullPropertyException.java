package com.huahua.saselomo.common.exception;
/**
 * 空属性异常
 * @author Lin·Y
 *
 */
public class NullPropertyException extends RuntimeException{

	private static final long serialVersionUID = 1603695770071770293L;

	public NullPropertyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NullPropertyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NullPropertyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NullPropertyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NullPropertyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
