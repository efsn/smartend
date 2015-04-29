package org.codeyn.smartend.framework.base;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class ServiceLocator implements BeanFactoryAware {
    
    private static ServiceLocator serviceLocator;
    private static BeanFactory beanFactory;
    
    public static ServiceLocator getInstance(){
        if(null == serviceLocator){
            serviceLocator = (ServiceLocator) beanFactory.getBean("serviceLocator");
        }
        return serviceLocator;
    }
    
    public BeanFactory getBeanFactory(){
        return beanFactory;
    }
    
    @SuppressWarnings("static-access")
    public void setBeanFactory(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }
    
    public static <T> T getBean(Class<T> clazz){
        return ServiceLocator.beanFactory.getBean(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String clazz){
        return (T) ServiceLocator.beanFactory.getBean(clazz);
    }
    
}
