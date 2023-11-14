package com.sku.exam.basic.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/test2")
@CrossOrigin(origins = "http://localhost:3000")
public class ScrapingController {

    @GetMapping("/scrape/{cid}")
    public ResponseEntity<String> scrapeWebsite(@PathVariable String cid) {
        String url = "https://datalab.naver.com/shoppingInsight/getKeywordRank.naver?timeUnit=date&cid=" + cid;

        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Your User Agent")  // Replace with your user agent
                    .referrer("https://datalab.naver.com/")
                    .get();

            String scrapedData = document.text();
            return ResponseEntity.ok(scrapedData);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to scrape the website.");
        }
    }
}