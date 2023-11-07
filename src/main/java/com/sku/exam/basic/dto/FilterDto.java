package com.sku.exam.basic.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterDto {
    private String startDate;

    private String endDate;

    private String timeUnit;

    private String category;

    private String keyword;

    private String device;

    private List<String> ages;

    private String gender;
}