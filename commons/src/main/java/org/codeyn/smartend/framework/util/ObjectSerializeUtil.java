package org.codeyn.smartend.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerializeUtil {

	public static Object stringToObj(String serStr) {
		try {
			String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			Object result = objectInputStream.readObject();
			objectInputStream.close();
			byteArrayInputStream.close();
			return result;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String objToString(Object obj) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(obj);
			String serStr = byteArrayOutputStream.toString("ISO-8859-1");
			serStr = java.net.URLEncoder.encode(serStr, "UTF-8");

			objectOutputStream.close();
			byteArrayOutputStream.close();
			return serStr;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
