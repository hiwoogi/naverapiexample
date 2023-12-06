package com.sku.project.trendanalysis.repository;

import com.sku.project.trendanalysis.entity.Favorite;
import com.sku.project.trendanalysis.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavRepository extends JpaRepository<Favorite,String> {
    List<Favorite> findByMember(Member member);

}
