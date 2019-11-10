package com.test.test.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "secondname")
    private String secondname;

    @Column(name = "email")
    private String email;

    @Column(name = "uriImage")
    private String uriImage;

    @Column(name = "status")
    private String status;

    @Column(name = "timeChange")
    private Date timeChange;

    public Users() { }

    public Users(String name, String secondname, String email, String uriImage, String status, Date timeChange) {
        this.name = name;
        this.secondname = secondname;
        this.email = email;
        this.uriImage = uriImage;
        this.status = status;
        this.timeChange = timeChange;
    }
}