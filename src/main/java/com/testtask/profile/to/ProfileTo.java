package com.testtask.profile.to;

import javax.validation.constraints.*;

public class ProfileTo {
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @Email(message = "Неправильный формат")
    @NotBlank
    private String email;

    @NotNull
    @Min(value = 18, message = "регистрация доступна для совершеннолетних")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
