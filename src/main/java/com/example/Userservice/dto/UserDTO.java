package com.example.Userservice.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {

    private String firstName;
    private String emailId;
    private String password;
}
