package com.example.testbvk.DTO.reqbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserReqBody {

    private String uname;
    private String email;
    private String password;
}
