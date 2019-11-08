package com.test.test.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
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

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", name='" + name + '\'' +
                        ", secondname='" + secondname + '\'' +
                        ", email='" + email + '\'' +
                        ", uriImage='" + uriImage + '\'' +
                        ", status='" + status + '\'' +
                        ", timeChange=" + timeChange;
    }

}