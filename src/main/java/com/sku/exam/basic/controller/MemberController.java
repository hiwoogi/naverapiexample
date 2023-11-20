package com.sku.exam.basic.controller;


import com.sku.exam.basic.dto.MemberFormDto;
import com.sku.exam.basic.entity.Member;
import com.sku.exam.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/test3")
@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody MemberFormDto memberFormDto) {
        try {
            // Convert MemberFormDto to Member entity
            Member member = new Member();
            member.setName(memberFormDto.getName());
            member.setEmail(memberFormDto.getEmail());
            member.setPassword(memberFormDto.getPassword());
            member.setGender(memberFormDto.getGender());

            // Save the member
            Member savedMember = memberService.saveMember(member);

            return ResponseEntity.ok("Signup successful. Member ID: " + savedMember.getId());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Failed to signup the website.");
        }
    }
}
