package org.codeyn.smartend.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codeyn.smartend.framework.error.ErrorType;
import org.codeyn.smartend.framework.util.PropertyConfigurer;
import org.codeyn.smartend.util.FrontUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CheckSignatureInterceptor implements HandlerInterceptor {

	protected final Logger logger = Logger.getLogger(this.getClass());

    private static String APP_KEY = PropertyConfigurer.get("api.verify.key");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 判断接口验签开关
        if ("false".equals(PropertyConfigurer.get("api.verify.switch"))) {
			return true;
		}

		// 验证服务端签名是否一致
		if (this.calcServerSign(request).equals(request.getHeader("x-sign"))) {
			return true;
		}

		Integer errorCode = ErrorType.SYSTEM_FORBIDDEN.getCode();
		String errorMsg = ErrorType.SYSTEM_FORBIDDEN.getMsg();
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(FrontUtil.renderJson(errorCode, errorMsg, null));
			writer.flush();
		} catch (IOException e) {
			logger.error("处理异常请求出错", e);
		}
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@SuppressWarnings("unchecked")
	private String calcServerSign(HttpServletRequest request) {
		List<String> sortedDataItemList = new ArrayList<String>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues != null) {
				String valStr = paramValues.length == 1 ? paramValues[0] : Arrays.toString(paramValues);
				sortedDataItemList.add(paramName + "=" + valStr);
			}
		}

		// 升序
		Collections.sort(sortedDataItemList);
		String strDataItem = StringUtils.join(sortedDataItemList, "");
		return DigestUtils.md5Hex(strDataItem + APP_KEY);
	}
}
