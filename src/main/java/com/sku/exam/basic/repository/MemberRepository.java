package com.sku.exam.basic.repository;

import com.sku.exam.basic.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long > {
    Member findByEmail(String email);

}