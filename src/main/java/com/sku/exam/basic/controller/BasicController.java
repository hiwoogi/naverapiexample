package com.sku.exam.basic.controller;

import com.google.gson.Gson;
import com.sku.exam.basic.dto.ClickFilterDto;
import com.sku.exam.basic.dto.FilterDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/test")
@Controller
public class BasicController {

    private static final Logger logger = LoggerFactory.getLogger(BasicController.class);

    @Value("${data.client.id}")
    private String dataclientId;
    @Value("${data.client.secret}")
    private String dataclientSecret;
    @Value("${data.genderApiURL}")
    private String genderApiURL;
    @Value("${data.deviceApiURL}")
    private String deviceApiURL;
    @Value("${data.ageApiURL}")
    private String ageApiURL;
    @Value("${data.clickApiURL}")
    private String clickApiURL;



    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    @PostMapping("/gender")
    public ResponseEntity<String> testRequestBody(@RequestBody FilterDto dto) {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", dataclientId);
        requestHeaders.put("X-Naver-Client-Secret", dataclientSecret);
        requestHeaders.put("Content-Type", "application/json");

        //요청 json 데이터를
        Gson gson = new Gson();
        String requestBody = gson.toJson(dto);

        String responseBody = post(genderApiURL, requestHeaders, requestBody);
        logger.info("response : {}", responseBody);
        logger.info("Received KeywordDto: {}", dto);
        return ResponseEntity.ok(responseBody);


    }

    @PostMapping("/device")
    public ResponseEntity<String> deviceRequestBody(@RequestBody FilterDto dto) {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", dataclientId);
        requestHeaders.put("X-Naver-Client-Secret", dataclientSecret);
        requestHeaders.put("Content-Type", "application/json");

        //요청 json 데이터를
        Gson gson = new Gson();
        String requestBody = gson.toJson(dto);

        String responseBody = post(deviceApiURL, requestHeaders, requestBody);
        logger.info("response : {}", responseBody);
        logger.info("Received KeywordDto: {}", dto);
        return ResponseEntity.ok(responseBody);


    }

    @PostMapping("/age")
    public ResponseEntity<String> ageRequestBody(@RequestBody FilterDto dto) {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", dataclientId);
        requestHeaders.put("X-Naver-Client-Secret", dataclientSecret);
        requestHeaders.put("Content-Type", "application/json");

        //요청 json 데이터를
        Gson gson = new Gson();
        String requestBody = gson.toJson(dto);

        String responseBody = post(ageApiURL, requestHeaders, requestBody);
        logger.info("response : {}", responseBody);
        logger.info("Received KeywordDto: {}", dto);
        return ResponseEntity.ok(responseBody);


    }

    @PostMapping("/click")
    public ResponseEntity<String> clickRequestBody(@RequestBody ClickFilterDto dto) {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", dataclientId);
        requestHeaders.put("X-Naver-Client-Secret", dataclientSecret);
        requestHeaders.put("Content-Type", "application/json");

        //요청 json 데이터를
        Gson gson = new Gson();
        String requestBody = gson.toJson(dto);

        String responseBody = post(clickApiURL, requestHeaders, requestBody);
        logger.info("response : {}", responseBody);
        logger.info("Received KeywordDto: {}", dto);
        return ResponseEntity.ok(responseBody);


    }


}




