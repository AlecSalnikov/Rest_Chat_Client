package com.alec.spring.rest;

import com.alec.spring.rest.configuration.myConfig;

import com.alec.spring.rest.entity.Chat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(myConfig.class);

        Communication communication = context.getBean("communication", Communication.class);


        Chat chat = new Chat("JGd","Ivan");

        communication.saveChat(chat);
     List<Chat> allMessages = new ArrayList<>();
     allMessages = communication.showAllMessages();


System.out.println(allMessages);





    }
}
