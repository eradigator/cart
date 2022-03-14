package ru.skipp.test.cart.service;

import org.springframework.lang.NonNull;
import ru.skipp.test.cart.entity.cart.dto.CartCalcProductReqDTO;
import ru.skipp.test.cart.entity.cart.dto.CartCalcProductRespDTO;

import java.util.List;

public interface IProductService {
    List<CartCalcProductRespDTO> getProductsWithPrice(@NonNull List<CartCalcProductReqDTO> products);
}
