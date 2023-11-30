package com.sku.exam.basic.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteDto {

    private FilterDto filterData;
    private ClickFilterDto clickFilterData;
    private MemberFormDto member;

    private LocalDateTime registrationTime;

    private String title;

    private String contents;


}