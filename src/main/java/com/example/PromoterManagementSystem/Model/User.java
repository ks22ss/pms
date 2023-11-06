package com.example.PromoterManagementSystem.Model;

import com.example.PromoterManagementSystem.Type.UserType;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table(schema = "pms", name = "user")
public class User {
    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "pms_user_type_enum")
    private UserType userType;

    private Date joinDate = Date.valueOf(LocalDate.now());
    private Date endDate = null;

    public User() {

    }

    public User(String username, String email, String password, UserType userType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return this.email + " " + this.password + " " +this.username + " " +this.userType;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
