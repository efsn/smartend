package org.codeyn.smartend.framework.base;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codeyn.smartend.framework.error.BusinessException;
import org.codeyn.smartend.framework.error.ErrorType;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.alibaba.fastjson.JSON;

import freemarker.template.Template;

public class BaseController{
    
    protected final Logger logger = Logger.getLogger(this.getClass());

    @Resource
    protected FreeMarkerConfigurer freeMarkerConfigurer;
    
    @SuppressWarnings("unchecked")
    protected Map<String, Object> parseJsonTemplate(String tempUrl, Object modelMap) throws Exception{
        FreeMarkerConfigurer freeMarkerConfigurer = ServiceLocator.getBean("freeMarkerConfigurer");
        Template temp = freeMarkerConfigurer.getConfiguration().getTemplate(tempUrl);
        String returnStr = FreeMarkerTemplateUtils.processTemplateIntoString(temp, modelMap);
        try {
            return JSON.parseObject(returnStr.replaceAll("\\s", " "), Map.class);
        } catch (Exception e) {
            logger.error("json格式有误:" + returnStr);
            throw new BusinessException(e);
        }
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    protected String jsonSuccess(){
        return jsonSuccess(null);
    }
    
    protected <T> String jsonSuccess(T data){
        return jsonSuccess(data, "ok");
    }
    
    protected <T> String jsonSuccess(T data, String msg){
        return renderJson(200, msg, data);
    }
    
    protected <T> String jsonFailed(int code, String msg){
        return renderJson(code, msg, null);
    }
    
    protected <T> String jsonFailed(ErrorType error){
        return renderJson(error.getCode(), error.getMsg(), null); 
    }
    
    private <T> String renderJson(int code, String msg, T data){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> stateMap = new HashMap<String, Object>();
        stateMap.put("code", code);
        stateMap.put("msg", StringUtils.isBlank(msg) ? "ok" : msg);
        jsonMap.put("state", stateMap);
        jsonMap.put("data", data);
        return JSON.toJSONString(jsonMap);
    }
    
}
