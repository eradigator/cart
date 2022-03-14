package ru.skipp.test.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.skipp.test.cart.entity.cart.dto.CartCalcProductReqDTO;
import ru.skipp.test.cart.entity.cart.dto.CartCalcProductRespDTO;
import ru.skipp.test.cart.service.IProductRestClientService;
import ru.skipp.test.cart.service.IProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRestClientService restClientService;

    @Override
    public List<CartCalcProductRespDTO> getProductsWithPrice(@NonNull List<CartCalcProductReqDTO> products) {

        List<CartCalcProductRespDTO> productRespDTOres;

        productRespDTOres = products.stream()
                .map(p -> {
                    CartCalcProductRespDTO productRespDTO = new CartCalcProductRespDTO();
                    productRespDTO.setId(p.getId());
                    productRespDTO.setCount(p.getCount());

                    float price = restClientService.getProductById(p.getId()).getPrice();
                    productRespDTO.setSum(price * p.getCount());

                    return productRespDTO;
                })
                .collect(Collectors.toList());

        return productRespDTOres;
    }
}
