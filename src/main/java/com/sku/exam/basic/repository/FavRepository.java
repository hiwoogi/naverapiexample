package com.sku.exam.basic.repository;

import com.sku.exam.basic.entity.Favorite;
import com.sku.exam.basic.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavRepository extends JpaRepository<Favorite,String> {
    List<Favorite> findByMember(Member member);

}
