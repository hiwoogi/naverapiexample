package com.sku.exam.basic.service;

import com.sku.exam.basic.entity.Favorite;
import com.sku.exam.basic.entity.Member;
import com.sku.exam.basic.repository.FavRepository;
import com.sku.exam.basic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FavService {
    @Autowired
    private FavRepository favRepository;

    @Autowired
    private MemberService memberService;


    public Favorite getFavoriteById(String id) {
        return favRepository.findById(id).orElse(null);
    }

    public Favorite createFavorite(Favorite favorite) {
        return favRepository.save(favorite);
    }

    public Favorite updateFavorite(String id, String updatedContents) {
        Favorite existingFavorite = favRepository.findById(id).orElse(null);
        if (existingFavorite != null) {
            // Update fields as needed
            existingFavorite.setContents(updatedContents);
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


    public List<Favorite> getAllFavoritesForMember(String memberId) {
        // Find the member by email
        Member member = memberService.getMemberById(memberId);

        if (member == null) {
            // Handle the case where the member is not found (you can throw an exception or return an empty list)
            return Collections.emptyList();
        }

        // Fetch the favorites for the given member
        return favRepository.findByMember(member);
    }
}
