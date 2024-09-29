package com.example.testbvk.Controller;

import com.example.testbvk.DTO.reqbody.MemberReqBody;
import com.example.testbvk.DTO.resbody.MemberResBody;
import com.example.testbvk.Entity.MemberEntity;
import com.example.testbvk.Service.MemberInterface;
import com.example.testbvk.Service.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/testBVK/member/")
public class MemberController {
    @Autowired
    private MemberInterface memberInterface;
    private static final String UPLOAD_DIR = "uploads/";

    @GetMapping("listMember")
    public List<MemberResBody> serchMember(@RequestParam String name, Pageable pageable) {
        return memberInterface.searchMember(name, pageable);
    }

    @GetMapping("detailMember")
    public MemberEntity serchMember(@RequestParam String email) {
        return memberInterface.detailMember(email);
    }

    @PostMapping("create")
    public ResponseEntity<String> save(@ModelAttribute MemberReqBody memberReqBody) {
        String name = memberReqBody.getName();
        String email = memberReqBody.getEmail();
        MultipartFile picture = memberReqBody.getPicture();


        if (name == null || name.isEmpty() || email == null || email.isEmpty() || picture.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
        }

        try {

            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }


            byte[] bytes = picture.getBytes();
            Path path = Paths.get(UPLOAD_DIR + picture.getOriginalFilename());
            Files.write(path, bytes);

            memberInterface.save(memberReqBody, path.toString());

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Member details uploaded successfully. Name: " + name + ", Email: " + email +
                            ", Picture saved at: " + path);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload picture: " + e.getMessage());
        }
    }
}
