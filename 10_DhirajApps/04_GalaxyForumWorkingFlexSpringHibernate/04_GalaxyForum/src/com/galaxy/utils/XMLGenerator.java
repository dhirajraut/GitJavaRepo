package com.galaxy.utils;

import java.lang.reflect.Field;

public class XMLGenerator {
	public static String generateXML(Object object) {
		return generateTag (object);
	}
	
	public static String generateTag(Object object) {
		System.out.println("In XMLGenerator.generateTag()");
		StringBuilder sb = new StringBuilder();
		Class cls = object.getClass();
		String classSimpleName = cls.getSimpleName();
		sb.append("<" + classSimpleName + ">");
		String className = cls.getName();
		while (isCustomClass(className)) {
			System.out.println("Classname = " + className);
			String fieldName = "";
			String fieldValue = "";

			Field[] fields = cls.getDeclaredFields();
			for (int ctr = 0; ctr < fields.length; ctr ++) {
				Field field = fields[ctr];
				field.setAccessible(true);
				fieldName = field.getName();
				fieldValue = getFieldValue(object, field);
				String xmlTag = prepareXMLTag(fieldName, fieldValue);
				sb.append(xmlTag);
			}
			cls = cls.getSuperclass();
			className = cls.getName();
		}
		sb.append("</" + classSimpleName + ">");
		return sb.toString();

	}

	private static String prepareXMLTag(String fieldName, String fieldValue) {
		StringBuilder sb = new StringBuilder();
		sb.append("<" + fieldName + ">")
			.append(fieldValue)
			.append("</" + fieldName + ">\n");
		return sb.toString();
	}
	
	public static String getFieldValue(Object object, Field field) {
		String value = "";
		try {
			Object vo = field.get(object);
			if (null != vo) {
				value = (String)field.get(object).toString();
			}
			else {
				value = "null";
			}
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	private static boolean isCustomClass(String className) {

		if (className.startsWith("com.galaxy")) {
			return true;
		}
		return false;
	}

}
