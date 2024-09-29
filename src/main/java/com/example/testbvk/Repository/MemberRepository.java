package com.example.testbvk.Repository;

import com.example.testbvk.Entity.MemberEntity;
import com.example.testbvk.Entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    @Query(value="select u from MemberEntity u where u.email = :email")
    MemberEntity findByEmail(@Param("email") String email);

    @Query(value = "select count(u.email) > 0 from MemberEntity u where u.email = :email")
    Boolean isEmailExist(@Param("email") String email);

    @Query(value="select u from MemberEntity u where lower(u.name) like lower(concat('%', :name, '%'))")
    Page<MemberEntity> findMember(@Param("name") String name, Pageable pageable);

//    @Query(value = "select u from MemberEntity u where lower(u.name) like lower(concat('%', :name, '%'))")
//    List<MemberEntity> findByName(@Param("name") String name);




}
