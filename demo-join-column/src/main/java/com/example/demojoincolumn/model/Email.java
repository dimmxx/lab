package com.example.demojoincolumn.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Email {

    @Id
    Long id;

    Employee employee;

}
