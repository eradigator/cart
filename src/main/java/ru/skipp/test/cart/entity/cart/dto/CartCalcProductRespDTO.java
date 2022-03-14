package ru.skipp.test.cart.entity.cart.dto;

import lombok.Data;

@Data
public class CartCalcProductRespDTO {
    private int id;
    private int count;
    private float sum;
}
