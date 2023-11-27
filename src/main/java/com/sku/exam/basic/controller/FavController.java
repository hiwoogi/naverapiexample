package com.sku.exam.basic.controller;

import com.google.gson.Gson;
import com.sku.exam.basic.dto.FavoriteDto;
import com.sku.exam.basic.entity.Favorite;
import com.sku.exam.basic.entity.Member;
import com.sku.exam.basic.service.FavService;
import com.sku.exam.basic.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/favorites")
public class FavController {

    @Autowired
    private FavService favService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable String id) {
        Favorite favorite = favService.getFavoriteById(id);
        return ResponseEntity.ok(favorite);
    }
    @PostMapping
    public ResponseEntity<Favorite> createFavorite(@RequestBody FavoriteDto favoriteDto) {
        Member member = memberService.getMemberById(favoriteDto.getMember().getId());



        Favorite favorite = Favorite.builder()
                .filterCriteria(convertToJsonString(favoriteDto.getFilterData())) // Convert FilterData to String using Gson
                .clickFilterCriteria(convertToJsonString(favoriteDto.getClickFilterData()))
                .member(member) // Set the member
                .build();

        Favorite createdFavorite = favService.createFavorite(favorite);
        return new ResponseEntity<>(createdFavorite, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable String id) {
        favService.deleteFavorite(id);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/{id}")
    public ResponseEntity<Favorite> updateFavorite(@PathVariable String id, @RequestBody Favorite favorite) {
        Favorite updatedFavorite = favService.updateFavorite(id, favorite);
        return ResponseEntity.ok(updatedFavorite);
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getAllFavorites() {
        List<Favorite> favorites = favService.getAllFavorites();
        return ResponseEntity.ok(favorites);
    }

    private String convertToJsonString(Object object) {
        try {
            Gson gson = new Gson();
            return gson.toJson(object);
        } catch (Exception e) {
            // Handle the exception (e.g., log an error)
            return null;
        }
    }
}
