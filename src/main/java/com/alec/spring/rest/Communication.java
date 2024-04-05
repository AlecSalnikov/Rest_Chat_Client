package com.alec.spring.rest;


import com.alec.spring.rest.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/spring_course_rest_war_exploded/api/messages";

    public List<Chat> showAllMessages(){
        ResponseEntity<List<Chat>> responseEntity=
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Chat>>() {});
               List<Chat> allEmployees= responseEntity.getBody();


                return allEmployees;

    }

public Chat getChat(int id){
        Chat chat =restTemplate.getForObject(URL+"/"+id, Chat.class);
        return  chat;
}


    public void showLastMessages() {
        ResponseEntity<LinkedList<Chat>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<LinkedList<Chat>>() {
                        });
        LinkedList<Chat> allEmployees2 = responseEntity.getBody();
        Collections.reverse(allEmployees2);
        List<Chat> lastMessages = new ArrayList<>();

      for(int i=4;i>=0;--i){

                System.out.println("============BACK CHAT================");
                System.out.println(allEmployees2.get(i).getUserName());
                System.out.println("(" + allEmployees2.get(i).getData() +"): ");
                System.out.println(allEmployees2.get(i).getMessage());


        }
    }


    public int saveChat(Chat chat){

        int id = chat.getId();
        if(id==0){
            ResponseEntity<String> responseEntity=
                    restTemplate.postForEntity(URL,chat, String.class);
         // System.out.println(responseEntity.getBody());
        }
else
        restTemplate.put(URL,chat);
        return id;
    }
    }











