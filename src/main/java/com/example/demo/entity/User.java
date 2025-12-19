package com.example.demo.entity

import jarkata.persistence.*;

@Entity
@ID
private Long id;
private String fullname;
@column(unique=true)
private String email;
