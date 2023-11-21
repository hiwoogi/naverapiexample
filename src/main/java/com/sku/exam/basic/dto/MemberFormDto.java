package com.sku.exam.basic.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberFormDto {

    private String token;

    private String name;

    private String email;

    private String password;

    private String confirmPassword;

    private String gender;

    private String id;




}