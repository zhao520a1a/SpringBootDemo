//package com.example.demo.controller;
//
///**
// * @author 赵金鑫
// * @date 2017年09月06日
// * 若需要部署到外部的tomcat容器中，则添加下面类即可:
// */
//
//import com.example.demo.SpringBootDemo1Application;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//
///**
// * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
// * @author ZSX
// *
// */
//public class SpringBootStartApplication extends SpringBootServletInitializer {
//
//    private static final Logger logger = LoggerFactory.getLogger(SpringBootStartApplication.class);
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SpringBootDemo1Application.class);
//    }
//
//}