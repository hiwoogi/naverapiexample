package com.sku.exam.basic.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MemberFormDto {

    private String name;

    private String email;

    private String password;

    private String confirmPassword;

    private String gender;




}