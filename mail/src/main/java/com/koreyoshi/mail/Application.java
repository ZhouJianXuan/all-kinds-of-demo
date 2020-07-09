package com.koreyoshi.mail;

import com.koreyoshi.mail.annotation.SendMail;
import com.koreyoshi.mail.entity.Node;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.koreyoshi.base.exception.BaseCustomException;
import work.koreyoshi.base.exception.HttpException;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/send")
    @SendMail(tos = "#0.tos", title = "#0.title", msg = "#0.msg")
    public String send(@RequestBody Node node){
        return "success";
    }

    @RequestMapping("/send2")
    @SendMail(tos = "#0", title = "#1", msg = "#2")
    public String send(String[] tos, String title, String msg){
        return "success";
    }

    @RequestMapping("/send3")
    public String send3(String[] tos, String title, String msg){
        throw HttpException.connectError();
    }
}
