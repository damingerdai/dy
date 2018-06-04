package org.aming.dy.exceptions;

/**
 * Description: 动态数据源异常
 * User: daming
 * Date: 2018-06-02
 * Time: 20:51
 */
public class DynamicDataSourceException extends RuntimeException {

	private String message;

	private Throwable cause;

	@Override
	public String getMessage() {
		return message;
	}

	public DynamicDataSourceException setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public Throwable getCause() {
		return cause;
	}

	DynamicDataSourceException setCause(Throwable cause) {
		this.cause = cause;
		return this;
	}

	DynamicDataSourceException(String message, Throwable cause) {
		super();
		this.message = message;
		this.cause = cause;
	}

	DynamicDataSourceException(String message) {
		super();
		this.message = message;
	}

	public DynamicDataSourceException() {
		super();
	}
}
