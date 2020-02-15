package com.bob.frame.bobframeprojecta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.bob.common", "com.bob.frame"})
@MapperScan("com.bob.frame.bobframeprojecta.dao")
public class BobFrameProjectAApplication {

    public static void main(String[] args) {
        SpringApplication.run(BobFrameProjectAApplication.class, args);
        System.out.println("----------------------BobFrameProjectAApplication start success-------------");
    }

}
