package com.nubons.nnp.lcnc.codegen.entitygen.exception;

/**
 * 
 * @author Gourab Guha
 *
 */
public class EntityGenerationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6474125323739868139L;

	private String code;

	public EntityGenerationException() {
		super();
	}

	public EntityGenerationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public EntityGenerationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EntityGenerationException(String arg0) {
		super(arg0);
	}

	public EntityGenerationException(Throwable arg0) {
		super(arg0);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
