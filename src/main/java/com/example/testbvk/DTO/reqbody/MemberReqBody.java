package com.example.testbvk.DTO.reqbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberReqBody {
    private String name;
    private String email;
    private String position;
    private MultipartFile picture;
    private String joinDate;
}
