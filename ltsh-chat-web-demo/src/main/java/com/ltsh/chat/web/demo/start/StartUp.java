package com.ltsh.chat.web.demo.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by fengjb-it on 2016/7/15 0015.
 */
@ComponentScan
@EnableAutoConfiguration
@ImportResource("classpath:/spring/spring-context.xml")
public class StartUp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartUp.class, args);
    }


}
