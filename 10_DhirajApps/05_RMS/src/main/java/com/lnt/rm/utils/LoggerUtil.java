/**
 * 
 */
package com.lnt.rm.utils;

/**
 * @author 284773
 *
 */
public class LoggerUtil {
	// @Todo Temporary Implementation.
	public static void log(Object object, String message) {
		System.out.println(object.getClass().getName() + ": " + message);
	}
}
