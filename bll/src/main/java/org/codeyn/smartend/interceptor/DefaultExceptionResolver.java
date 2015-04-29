package org.codeyn.smartend.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codeyn.smartend.framework.error.BusinessException;
import org.codeyn.smartend.framework.error.ErrorType;
import org.codeyn.smartend.util.FrontUtil;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统默认异常处理类
 */
public class DefaultExceptionResolver implements HandlerExceptionResolver {

	/** Logger available to subclasses */
	protected final Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.error("接口异常", ex);

		// 分析异常类型
		Integer errorCode = ErrorType.SYSTEM_BUSY.getCode();
		String errorMsg = ErrorType.SYSTEM_BUSY.getMsg();
		if (ex instanceof BusinessException) {
			BusinessException businessException = (BusinessException) ex;
			errorCode = businessException.getErrorCode();
			errorMsg = businessException.getMessage();
		}

		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(FrontUtil.renderJson(errorCode, errorMsg, null));
			writer.flush();
		} catch (IOException e) {
			logger.error("处理异常请求出错", e);
		}

		return new ModelAndView();
	}
}
