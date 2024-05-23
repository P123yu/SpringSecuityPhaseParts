package com.abc.service;

import com.abc.Jwt.JwtToken;
import com.abc.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // register
    JwtToken register(UserModel userModel);


}
