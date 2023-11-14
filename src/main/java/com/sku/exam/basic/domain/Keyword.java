package com.sku.exam.basic.domain;

import lombok.Data;

import java.util.List;

@Data
public class Keyword {
    private String name;
    private List<String> param;


}
