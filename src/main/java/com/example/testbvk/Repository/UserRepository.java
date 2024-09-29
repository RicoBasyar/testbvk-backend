package com.example.testbvk.Repository;


import com.example.testbvk.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "Select u from UserEntity u where u.email = :email")
    UserEntity findByEmail(@Param("email") String email);

    @Query(value = "select count(u.email) > 0 from UserEntity u where u.email = :email")
    Boolean isEmailExist(@Param("email") String email);

}
