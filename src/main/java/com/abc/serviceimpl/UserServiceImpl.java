package com.abc.serviceimpl;

import com.abc.Jwt.JwtToken;
import com.abc.Jwt.JwtTokenGenerator;
import com.abc.model.UserModel;
import com.abc.repository.UserRepository;
import com.abc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;
    @Override
    public JwtToken register(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        UserModel user=userRepository.save(userModel);
        String jwt_token=jwtTokenGenerator.generateToken(user);
        return JwtToken.builder().accessToken(jwt_token).build();
    }



}
