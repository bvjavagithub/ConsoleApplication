/**
 * 
 */
package com.sainsbury.exception;

/**
 * @author Bhakti
 * Customized exception class.
 */
public class SainsburyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SainsburyException() {
	}

	/**
	 * @param arg0
	 */
	public SainsburyException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public SainsburyException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SainsburyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public SainsburyException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
