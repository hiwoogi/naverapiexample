package com.sku.exam.basic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberFormDto {

    private String token;

    @NotBlank(message = "이름은 필수 입력입니다.")
    @Size(min = 2, max = 10, message = "이름은 2~10자리 내에서 입력해주세요")
    private String name;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "@이 포함된 이메일 형태로 작성해주세요")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 4, message = "비밀번호는 최소 4자리 이상이어야합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인이 필요합니다.")
    @Size(min = 4, message = "비밀번호는 최소 4자리 이상이어야합니다.")
    private String confirmPassword;

    @NotBlank(message = "성별은 필수로 입력 되어야합니다")
    private String gender;

    private String id;




}