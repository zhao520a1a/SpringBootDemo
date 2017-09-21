//package com.example.demo.datasourceConfig;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author 赵金鑫
// * @date 2017年08月25日
// */
//
//@Configuration
////此处是你dao文件所在的包名
//@EnableJpaRepositories("com.example.demo.dao")
//@EnableTransactionManagement
//public class DataSourceConifg {
//
//
//    //连接池配置：按前缀自动装配注入
//    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.db")
//    //@Primary
//    public DataSource testDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//
//    @Bean(name = "entityManagerFactory")
//    public EntityManagerFactory entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        //此处com.example.*.model是你的java bean所在的包名
//        factory.setPackagesToScan("com.example.dao.model");
//        factory.setDataSource(dataSource);
//
//        Map<String, Object> jpaProperties = new HashMap<String, Object>();
//        jpaProperties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
//        jpaProperties.put("hibernate.jdbc.batch_size",50);
//
//        factory.setJpaPropertyMap(jpaProperties);
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    @Bean(name="platformTransactionManager")
//    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//
//
//
//
//}
