package com.example.demo;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ServletComponentScan // 扫描使用注解方式的servlet
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpringBootDemo1Application {

    //extends WebMvcConfigurerAdapter
    /* 使用FastJson方法一：
		 启动类继承 WebMvcConfigureerAdapter类
     	 覆盖configureMessageConverters（）
	过程：
	*  1.定义一个converter消息转换对象
    *  2.在converter添加配置信息
	*  3.将converter加入converters中
	* */
    //@Override
    //public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //	super.configureMessageConverters(converters);
    //	FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
    //
    //	FastJsonConfig fastJsonConfig = new FastJsonConfig();
    //	fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
    //	fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
    //
    //	converters.add(fastJsonHttpMessageConverter);
    //}

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemo1Application.class, args);
    }


    /*使用FastJson方法二：
    *  注入一个Bean
    * */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }


}
