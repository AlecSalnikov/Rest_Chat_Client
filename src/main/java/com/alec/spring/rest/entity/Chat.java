package com.alec.spring.rest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

//imp
//@Dataort javax.persistence.*;



public class Chat {

    private int id;
  ///  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   // @JsonSerialize(using = LocalDateTimeSerializer.

         //   class)@JsonDeserialize(using= LocalDateTimeDeserializer.class)
   //@CreationTimestamp
    //@Column(name="data",nullable = false,updatable = false)

    private Date data;

    private String message;

    private String userName ;





    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Chat() {

    }
    public Chat(  String message,String userName, Date data ) {

        this.userName=userName;
        this.data=data;
        this.message = message;

    }
    public Chat(  String message, String userName ) {

        this.userName=userName;
        this.data=data;
        this.message = message;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "\n" + userName +

                "(" +     data + ")" +
                ": " + message + '\n';
    }
}
