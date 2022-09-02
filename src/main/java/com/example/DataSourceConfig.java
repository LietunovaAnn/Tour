//package com.example;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
////    @Bean
////    public DataSource getDataSource() {
////
////        return DataSourceBuilder;
////    }
////    @Autowired
////    public OracleDAOFactoryImpl(@Value("${spring.datasource.driver-class-name}") String driver,
////                                @Value("${spring.datasource.url}") String url,
////                                @Value("${spring.datasource.username}") String username,
////                                @Value("${spring.datasource.password}") String password) {
////        this.setMaximumPoolSize(20);
////        this.setDriverClassName(driver);
////        this.setJdbcUrl(url);
////        this.setUsername(username);
////        this.setPassword(password);
////    }
//}
