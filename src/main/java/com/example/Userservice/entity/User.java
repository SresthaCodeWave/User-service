package com.example.Userservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "User")
public class User {

    @Id
    private String emailId;
    private String firstName;
    private String password;
}
