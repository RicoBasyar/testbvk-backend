package com.example.testbvk.Controller;


import com.example.testbvk.DTO.reqbody.LoginUserReqBody;
import com.example.testbvk.DTO.reqbody.RegisterUserReqBody;
import com.example.testbvk.Service.UserInterface;
import com.nimbusds.oauth2.sdk.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/testBVK/")
public class UserController {
    @Autowired
    private UserInterface userInterface;

    @GetMapping("user/oauth2/redirect")
    public ResponseEntity<?> googleCallBack(OAuth2AuthenticationToken token) {
        String email = token.getPrincipal().getAttribute("email");
        String name = token.getPrincipal().getAttribute("name");

        Object res = userInterface.googleCallback(email, name);

        return ResponseEntity.ok(res);
    }

    @PostMapping("user/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginUserReqBody r){
        try{
            Object member = userInterface.loginMember(r);
            return ResponseEntity.ok().body(member);
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }


    @PostMapping("user/registerUser")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterUserReqBody r) {
        try {
            Object member = userInterface.userRegister(r);
            System.out.println("Register request received: " + r);
            return ResponseEntity.ok().body(member);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
