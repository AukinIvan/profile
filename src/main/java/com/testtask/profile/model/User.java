package com.testtask.profile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_email_idx")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;


    @Column(name = "token")
    @CollectionTable(name = "user_token", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "token"}, name = "user_token_unique_idx")})
    @ElementCollection(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<String> token = new ArrayList<>();

    public User() {
    }

    public User(@NotBlank @Size(min = 2, max = 100) String name, @NotBlank @Size(min = 5, max = 20) String password) {
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getToken() {
        return token;
    }

    public void setToken(List<String> token) {
        this.token = token;
    }

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }
}
