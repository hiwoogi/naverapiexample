package com.sku.exam.basic.repository;

import com.sku.exam.basic.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavRepository extends JpaRepository<Favorite,String> {


}
