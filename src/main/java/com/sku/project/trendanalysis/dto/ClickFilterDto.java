package com.sku.project.trendanalysis.dto;

import com.sku.project.trendanalysis.domain.Keyword;
import lombok.Data;

import java.util.List;

@Data
public class ClickFilterDto {
    private String startDate;

    private String endDate;

    private String timeUnit;

    private String category;

    private List<Keyword> keyword;

    private String device;

    private List<String> ages;

    private String gender;
}
