package org.codeyn.smartend.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

public class FrontUtil {

	private static String[] excludeUrls = { "/login.json", "/version.json" };

	public static boolean isExcludeUrl(String curUrl) {
		for (String url : excludeUrls) {
			if (curUrl.indexOf(url) > -1) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAjax(HttpServletRequest request) {
		String xRequestedWith = request.getHeader("X-Requested-With");
		boolean isAjax = request.getHeader("accept").indexOf("application/json") > -1;
		return isAjax || (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") > -1);
	}

	public static <T> String renderJson(int code, String message, T data) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> stateMap = new HashMap<String, Object>();
		stateMap.put("code", code);
		stateMap.put("msg", StringUtils.isBlank(message) ? "Ok" : message);
		jsonMap.put("state", stateMap);
		jsonMap.put("data", data);
		return JSON.toJSONString(jsonMap);
	}
}
