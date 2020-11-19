package com.fireman.yang.auth.server.test;

import com.fireman.yang.auth.server.controller.LoginController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tongdong
 * @Date: 2020/6/4
 * @Description:
 */
@SpringBootApplication(scanBasePackages = {"com.fireman.yang.auth.server.controller"})
public class AuthServerWebStarter {


    public static void main(String[] args) {
        SpringApplication.run(AuthServerWebStarter.class, args);
    }

}
