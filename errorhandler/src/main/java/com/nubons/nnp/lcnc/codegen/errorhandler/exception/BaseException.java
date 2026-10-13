package com.nubons.nnp.lcnc.codegen.errorhandler.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * By default any Microservice module throws RuntimeException as
 * RuntimeException is the preferred way of handling exception scenarios. Either
 * this exception class has to be used or any of its subclass.
 * 
 * @author Gourab Guha (egouguh)
 * @since May 25, 2021
 *
 */
@Getter
@Setter
public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3268731131689486379L;

	private String code = null;
	private String reason = null;
	private String status = null;

	/**
	 * 
	 * @param code
	 * @param reason
	 * @param message
	 * @param status
	 * @param th
	 */
	public BaseException(String code, String reason, String message, String status, Throwable th) {
		super(message, th);
		this.code = code;
		this.reason = reason;
		this.status = status;
	}

	/**
	 * @param code
	 * @param reason
	 * @param message
	 * @param status
	 */
	public BaseException(String code, String reason, String message, String status) {
		super(message);
		this.code = code;
		this.reason = reason;
		this.status = status;
	}

	/**
	 * @param code
	 * @param reason
	 * @param message
	 */
	public BaseException(String code, String reason, String message) {
		super(message);
		this.code = code;
		this.reason = reason;
	}

	/**
	 * @param code
	 * @param reason
	 */
	public BaseException(String code, String reason) {
		super();
		this.code = code;
		this.reason = reason;
	}

	/**
	 * @param code
	 */
	public BaseException(String code) {
		super();
		this.code = code;
	}

	/**
	 * 
	 */
	public BaseException() {
		super();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public BaseException(String message, Throwable th, boolean arg2, boolean arg3) {
		super(message, th, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BaseException(String message, Throwable th) {
		super(message, th);
	}

	/**
	 * @param arg0
	 */
	public BaseException(Throwable th) {
		super(th);
	}

	/**
	 * 
	 * @param code
	 * @param message
	 * @param th
	 */
	public BaseException(String code, String message, Throwable th) {
		super(message, th);
		this.code = code;
	}

}
