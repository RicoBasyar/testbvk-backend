package com.example.testbvk.Service.impl;

import com.example.testbvk.DTO.reqbody.MemberReqBody;
import com.example.testbvk.DTO.resbody.MemberResBody;
import com.example.testbvk.Entity.MemberEntity;
import com.example.testbvk.Repository.MemberRepository;
import com.example.testbvk.Service.MemberInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MemberService implements MemberInterface {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public MemberEntity detailMember(String email){
        return memberRepository.findByEmail(email);
    }

    @Override
    public List<MemberResBody> searchMember(String name, Pageable pageable){

        Page<MemberEntity> membersPage = memberRepository.findMember(name, pageable);

        List<MemberResBody> resBodyList = new ArrayList<>();

        for (MemberEntity member : membersPage.getContent()) {
            MemberResBody resBody = new MemberResBody();
            resBody.setName(member.getName());
            resBody.setEmail(member.getEmail());
            resBody.setPosition(member.getPosition());
            resBodyList.add(resBody);
        }

        return resBodyList;
    }

    @Override
    public Object save(MemberReqBody r, String path){
        try{
            MemberEntity m = new MemberEntity();
            Boolean isEmailExist = memberRepository.isEmailExist(r.getEmail());
            if(isEmailExist){
                return "Email sudah digunakan";
            }
            m.setEmail(r.getEmail());
            m.setName(r.getName());
            m.setPosition(r.getPosition());
            m.setPicture(path);
            m.setJoinDate(r.getJoinDate());
            m = memberRepository.save(m);
            return m;
        }catch (Exception e){
            e.printStackTrace();
            return "error : " +  e.getMessage();
        }
    }
}
