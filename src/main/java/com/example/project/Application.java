package com.example.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args){

        ApplicationContext ctx = SpringApplication.run(Application.class,args);
        System.out.println("Inspecting bean names provided by spring: ");
        String[] beanNames=ctx.getBeanDefinitionNames(); //for getting bean names
        Arrays.sort(beanNames);
        for(String beanName:beanNames){
            System.out.println(beanName);
        }
    }
}
