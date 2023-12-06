package com.sku.project.trendanalysis.repository;

import com.sku.project.trendanalysis.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {
    Member findByEmail(String email);
    Member findByEmailAndPassword(String email, String password);


}