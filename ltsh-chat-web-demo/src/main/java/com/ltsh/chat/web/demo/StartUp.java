package com.ltsh.chat.web.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by fengjb-it on 2016/7/15 0015.
 */
//@ComponentScan

@SpringBootApplication
//@ImportResource("classpath:/spring/spring-context.xml")
public class StartUp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartUp.class, args);
    }


}
