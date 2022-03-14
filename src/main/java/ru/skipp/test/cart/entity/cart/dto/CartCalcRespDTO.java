package ru.skipp.test.cart.entity.cart.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartCalcRespDTO {
    private List<CartCalcProductRespDTO> products;
    private float total;
}
