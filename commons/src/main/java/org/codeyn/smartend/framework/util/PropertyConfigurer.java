package org.codeyn.smartend.framework.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyConfigurer extends PropertyPlaceholderConfigurer{
    
    private static Map<String, Object> ctxPropertiesMap;
    
    protected void processProperties(ConfigurableListableBeanFactory beanFoctory, Properties props){
        super.processProperties(beanFoctory, props);
        ctxPropertiesMap = new HashMap<String, Object>();
        for(Object key : props.keySet()){
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
    
    public static String get(String key){
        Object obj = ctxPropertiesMap.get(key);
        return obj == null ? null : obj.toString();
    }

    public static void put(String key, String value){
        ctxPropertiesMap.put(key, value);
    }
    
}
