package com.example.demo.entity

import jarkata.persistence.*;

@Entity
@Table(name="users",uniqueConstraints={
       @UniqueConstraints{columnNames="email")}
public class User{
    @ID
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    @column(unique=true)
    private String email;

}
