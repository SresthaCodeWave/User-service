package com.example.Userservice.feign;

import com.example.Userservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "localhost:8092/cart",name="cart")
public interface FeignInterface {

    @PostMapping("/createCart")
    public Boolean createCart(UserDTO userDTO);
    @PostMapping("/createOrder")
    public Boolean createOrder(UserDTO userDTO);
}
