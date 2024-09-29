package com.example.testbvk.Service.impl;

import com.example.testbvk.Util.JwtUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.example.testbvk.DTO.reqbody.LoginUserReqBody;
import com.example.testbvk.DTO.reqbody.RegisterUserReqBody;
import com.example.testbvk.DTO.resbody.LoginUserResBody;
import com.example.testbvk.Entity.UserEntity;
import com.example.testbvk.Repository.UserRepository;
import com.example.testbvk.Service.UserInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetailsService userDetailsService() {
        return uname -> (UserDetails) userRepository.findByEmail(uname);
    }

    @Override
    public Object googleCallback(String email, String name) {

            UserEntity user = userRepository.findByEmail(email);
            if (user == null) {
                user = new UserEntity();
                user.setEmail(email);
                user.setUname(name);
                userRepository.save(user);

                return user;
            }
            LoginUserResBody res = objectMapper.convertValue(user, new TypeReference<LoginUserResBody>() {
            });
            res.setToken(jwtUtil.generateToken(user.toString()));
            return res;

    }

    @Override
    public Object userRegister(RegisterUserReqBody r){

        try{
            Boolean isEmailExist = userRepository.isEmailExist(r.getEmail());
            if(isEmailExist){
                return "Email sudah digunakan";
            }
            UserEntity user = new UserEntity();
            user.setUname(r.getUname());
            user.setEmail(r.getEmail());
            user.setPassword(r.getPassword());

            user = userRepository.save(user);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return "error : " +  e.getMessage();
        }
    }

    @Override
    public Object loginMember(LoginUserReqBody r) {
        UserEntity user = userRepository.findByEmail(r.getEmail());

        if (user == null) {
            return "User Tidak Ditemukan";
        }

        if (r.getEmail().equals(user.getEmail()) && !r.getPassword().equals(user.getPassword())) {
            return "Password Tidak Sesuai";
        }

        LoginUserResBody res = objectMapper.convertValue(user, new TypeReference<LoginUserResBody>() {
        });

        res.setToken(jwtUtil.generateToken(user.toString()));
        return res;
    }
}
