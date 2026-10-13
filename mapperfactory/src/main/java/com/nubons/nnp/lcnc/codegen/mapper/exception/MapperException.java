package com.nubons.nnp.lcnc.codegen.mapper.exception;

/**
 * 
 * Custom Exception for Mapper utility.
 * 
 * @author Gourab Guha
 *
 */
public class MapperException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3549089978171215960L;

	public MapperException() {
		super();
	}

	public MapperException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public MapperException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MapperException(String arg0) {
		super(arg0);
	}

	public MapperException(Throwable arg0) {
		super(arg0);
	}

}
