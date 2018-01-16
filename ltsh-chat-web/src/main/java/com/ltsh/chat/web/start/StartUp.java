package com.ltsh.chat.web.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by fengjb-it on 2016/7/15 0015.
 */
@ComponentScan
@EnableAutoConfiguration
@ImportResource({"classpath:/spring/spring-context.xml"})
public class StartUp {

    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(StartUp.class);

//        properties.load(StartUp.class.getResourceAsStream("/application.properties"));
        springApplication.run(StartUp.class, args);
    }


}
