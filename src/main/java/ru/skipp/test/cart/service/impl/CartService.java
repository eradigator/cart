package ru.skipp.test.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.skipp.test.cart.entity.cart.dto.CartCalcProductRespDTO;
import ru.skipp.test.cart.entity.cart.dto.CartCalcReqDTO;
import ru.skipp.test.cart.entity.cart.dto.CartCalcRespDTO;
import ru.skipp.test.cart.service.ICartService;
import ru.skipp.test.cart.service.IProductService;

import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private IProductService productService;

    @Override
    public CartCalcRespDTO calc(@NonNull CartCalcReqDTO cartDTO) {
        List<CartCalcProductRespDTO> pricedProducts = productService.getProductsWithPrice(cartDTO.getProducts());

        CartCalcRespDTO res = new CartCalcRespDTO();
        res.setProducts(pricedProducts);
        res.setTotal(pricedProducts.stream()
                .map(CartCalcProductRespDTO::getSum)
                .reduce(0f, Float::sum));

        return res;
    }
}
