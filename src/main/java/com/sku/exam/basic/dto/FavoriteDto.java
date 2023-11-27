package com.sku.exam.basic.dto;

import lombok.Data;

@Data
public class FavoriteDto {

    private FilterDto  filterData;
    private ClickFilterDto clickFilterData;
    private MemberFormDto member;

}
