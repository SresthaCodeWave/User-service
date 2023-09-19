package com.example.Userservice.controller;


import com.example.Userservice.dto.UserDTO;
import com.example.Userservice.entity.User;
import com.example.Userservice.feign.FeignInterface;
import com.example.Userservice.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FeignInterface feignInterface;

    @GetMapping("/userList")
    public ResponseEntity<ArrayList<User>> getUsersList()
    {
        ArrayList<User> userArrayList = new ArrayList<User>();
        Iterable<User> userIterable = userService.findUserList();
        for (User user:userIterable)
        {
            userArrayList.add(user);
        }
        return new ResponseEntity(userArrayList, HttpStatus.OK);
    }


    @GetMapping("/getUserById/{emailId}")
    public ResponseEntity<User> getUserById(@PathVariable("emailId") String emailId)
    {
        Optional<User> user = userService.findUserById(emailId);
        UserDTO userDTO = new UserDTO();
        if(user.isPresent())
        {
            BeanUtils.copyProperties(user.get(),userDTO);
            return new ResponseEntity(user, HttpStatus.ACCEPTED);
        }
        else
        {
            return new ResponseEntity("No such user data is found", HttpStatus.OK);
        }
    }


    @PostMapping("/addOrUpdateUser")
    public ResponseEntity<String> addOrUpdateUser(@RequestBody UserDTO userDTO)
    {
        Boolean b=false;
        b=feignInterface.createCart(userDTO);
        b=feignInterface.createOrder(userDTO);
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userService.addOrUpdateUser(user);
        return new ResponseEntity<String>(user.getEmailId(), HttpStatus.CREATED);
    }


    @DeleteMapping("deleteUserById/{emailId}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("emailId") String emailId)
    {
        userService.deleteUser(emailId);
        return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.CREATED);
    }
}
