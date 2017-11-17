package com.example.demo.common;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring容器上下文工具类
 * 创建者： 刘江平
 * 创建时间：2014年9月15日上午10:17:06
 */
@Component
public class SpringContext implements ApplicationContextAware {

	private static ApplicationContext context;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	/**
	 * 获取spring容器上下文
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
	    return context;
	}
	/**
	 * 获取容器中的Bean对象
	 * @param beanid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getBean(String beanid){
		return (T)context.getBean(beanid);
	}
}
