package com.example.demo.entity

import jarkata.persistence.*;

@Entity
public class User{
    @ID
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    @column(unique=true)
    private String email;

}
