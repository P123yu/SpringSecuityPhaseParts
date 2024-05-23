package com.abc.controller;


import com.abc.Jwt.JwtToken;
import com.abc.model.UserModel;
import com.abc.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody UserModel userModel){
        JwtToken jwtToken=userServiceImpl.register(userModel);
        if(jwtToken!=null){
            return ResponseEntity.ok(jwtToken);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized registration");
        }

    }


}
