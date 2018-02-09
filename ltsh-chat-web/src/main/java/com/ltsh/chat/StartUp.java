package com.ltsh.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by fengjb-it on 2016/7/15 0015.
 */
@ImportResource({"classpath:/spring/spring-context.xml"})
@SpringBootApplication

public class StartUp {

    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(StartUp.class);

//        properties.load(StartUp.class.getResourceAsStream("/application.properties"));
        springApplication.run(StartUp.class, args);
    }


}
