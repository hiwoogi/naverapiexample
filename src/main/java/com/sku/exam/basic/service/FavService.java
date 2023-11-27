package com.sku.exam.basic.service;

import com.sku.exam.basic.entity.Favorite;
import com.sku.exam.basic.repository.FavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavService {
    @Autowired
    private FavRepository favRepository;

    public Favorite getFavoriteById(String id) {
        return favRepository.findById(id).orElse(null);
    }

    public Favorite createFavorite(Favorite favorite) {
        return favRepository.save(favorite);
    }

    public Favorite updateFavorite(String id, Favorite updatedFavorite) {
        Favorite existingFavorite = favRepository.findById(id).orElse(null);
        if (existingFavorite != null) {
            // Update fields as needed
            existingFavorite.setFilterCriteria(updatedFavorite.getFilterCriteria());
            // Update other fields if needed

            return favRepository.save(existingFavorite);
        } else {
            return null; // Handle not found
        }
    }

    public void deleteFavorite(String id) {
        favRepository.deleteById(id);
    }

    public List<Favorite> getAllFavorites() {
        return favRepository.findAll();
    }
}
