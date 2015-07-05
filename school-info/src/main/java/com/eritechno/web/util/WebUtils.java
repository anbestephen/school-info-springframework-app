package com.eritechno.web.util;

import java.text.MessageFormat;

import org.springframework.util.Assert;

public class WebUtils {

	/**
	 * 
	 * creates formated message
	 * 
	 * @param pattern
	 * @param argument
	 * @return
	 */
	public static String getFormatedMessage(String pattern, int argument) {
		Assert.hasLength(pattern, "pattern is required");
		return MessageFormat.format(pattern, argument);
	}

	/**
	 * 
	 * creates formated message
	 * 
	 * @param pattern
	 * @param argument
	 * @return
	 */

	public static String getFormatedMessage(String pattern, long argument) {
		Assert.hasLength(pattern, "pattern is required");
		return MessageFormat.format(pattern, argument);
	}

}
