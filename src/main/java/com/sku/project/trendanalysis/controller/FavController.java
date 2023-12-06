package com.sku.project.trendanalysis.controller;

import com.google.gson.Gson;
import com.sku.project.trendanalysis.dto.FavoriteDto;
import com.sku.project.trendanalysis.entity.Favorite;
import com.sku.project.trendanalysis.entity.Member;
import com.sku.project.trendanalysis.service.FavService;
import com.sku.project.trendanalysis.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
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
        LocalDateTime registrationTime = LocalDateTime.now();

        Favorite favorite = Favorite.builder()
                .filterCriteria(convertToJsonString(favoriteDto.getFilterData())) // Convert FilterData to String using Gson
                .clickFilterCriteria(convertToJsonString(favoriteDto.getClickFilterData()))
                .member(member) // Set the member
                .registrationTime(registrationTime) // Set the registration time
                .title(favoriteDto.getTitle()) // Set the title
                .contents(favoriteDto.getContents()) // Set the contents
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
    public ResponseEntity<Favorite> updateFavorite(@PathVariable String id, @RequestBody FavoriteDto favoriteDto) {

        Favorite updatedFavorite = favService.updateFavorite(id, favoriteDto.getContents());
        return ResponseEntity.ok(updatedFavorite);
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getAllFavorites(Authentication authentication) {
        String memberId = authentication.getName();


        // Use the member's email to fetch their favorites
        List<Favorite> favorites = favService.getAllFavoritesForMember(memberId);


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
