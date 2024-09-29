package com.example.testbvk.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="useradmin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid")
    int userID;

    @Column(name="uname")
    String uname;

    @Column(name="email")
    String email;

    @Column(name="password")
    String password;
}
