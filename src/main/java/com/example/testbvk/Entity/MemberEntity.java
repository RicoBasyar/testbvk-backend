package com.example.testbvk.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int ID;

    @Column(name="name")
    String name;

    @Column(name="email")
    String email;

    @Column(name="position")
    String position;

    @Column(name="join_date")
    String joinDate;

    @Column(name="picture")
    String picture;
}
