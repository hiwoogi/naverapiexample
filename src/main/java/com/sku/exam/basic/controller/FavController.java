package com.sku.exam.basic.controller;

import com.sku.exam.basic.entity.Favorite;
import com.sku.exam.basic.service.FavService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable String id) {
        Favorite favorite = favService.getFavoriteById(id);
        return ResponseEntity.ok(favorite);
    }
    @PostMapping
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite) {
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

}
