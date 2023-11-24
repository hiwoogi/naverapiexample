package com.sku.exam.basic.controller;


import com.sku.exam.basic.dto.MemberFormDto;
import com.sku.exam.basic.dto.ResponseDto;
import com.sku.exam.basic.entity.Member;
import com.sku.exam.basic.security.TokenProvider;
import com.sku.exam.basic.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/test3")
@RestController
public class MemberController {


    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public MemberController(MemberService memberService, TokenProvider tokenProvider) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody MemberFormDto memberFormDto, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, return a bad request response with error details
            return ResponseEntity.badRequest().body("Validation failed: " + result.getAllErrors());
        }

        try {
            // Convert MemberFormDto to Member entity
            Member member = new Member();
            member.setName(memberFormDto.getName());
            member.setEmail(memberFormDto.getEmail());
            member.setPassword(passwordEncoder.encode(memberFormDto.getPassword()));
            member.setGender(memberFormDto.getGender());

            // Save the member
            Member savedMember = memberService.saveMember(member);

            return ResponseEntity.ok("Signup successful. Member ID: " + savedMember.getId());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Failed to signup the website.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody MemberFormDto memberFormDto) {
        Member member = memberService.getByCredentials(
                memberFormDto.getEmail(),
                memberFormDto.getPassword(),
                passwordEncoder);

        if(member != null) {
            final String token = tokenProvider.create(member);
            // 토큰 생성
            final MemberFormDto responseUserDTO = MemberFormDto.builder()
                    .email(member.getEmail())
                    .id(member.getId())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDto responseDto = ResponseDto.builder()
                    .error("Login failed.")
                    .build();

            return ResponseEntity
                    .badRequest()
                    .body(responseDto);
        }
    }


}
