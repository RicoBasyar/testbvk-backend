package com.example.testbvk.Service;

import com.example.testbvk.DTO.reqbody.LoginUserReqBody;
import com.example.testbvk.DTO.reqbody.MemberReqBody;
import com.example.testbvk.DTO.reqbody.RegisterUserReqBody;
import com.example.testbvk.DTO.resbody.MemberResBody;
import com.example.testbvk.Entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MemberInterface {
    Object save(MemberReqBody r, String path);
    List<MemberResBody> searchMember(String name, Pageable pageable);
    MemberEntity detailMember(String email);
}

