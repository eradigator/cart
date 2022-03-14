package ru.skipp.test.cart.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.skipp.test.cart.entity.cart.Product;
import ru.skipp.test.cart.entity.cart.dto.CartCalcProductReqDTO;
import ru.skipp.test.cart.entity.cart.dto.CartCalcReqDTO;
import ru.skipp.test.cart.entity.cart.dto.CartCalcRespDTO;
import ru.skipp.test.cart.entity.customer.dto.CustomerAddressDTO;
import ru.skipp.test.cart.entity.payment.PaymentType;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;
    @MockBean
    private RestTemplate restTemplate;

    @Value("${product.service.api.url}")
    private String PRODUCT_API_URL;

    private static final int PRODUCT1_ID = 1;
    private static final int PRODUCT2_ID = 2;
    private static final float PRODUCT1_PRICE = 10f;
    private static final float PRODUCT2_PRICE = 5f;
    private static final int PRODUCT1_COUNT = 2;
    private static final int PRODUCT2_COUNT = 3;

    @Test
    void calc_givenRightRequest_thenReturnCalculatedCart() {
        Product product1 = new Product();
        product1.setId(PRODUCT1_ID);
        product1.setPrice(PRODUCT1_PRICE);
        when(restTemplate.postForEntity(PRODUCT_API_URL,1, Product.class))
                .thenReturn(ResponseEntity.ok(product1));

        Product product2 = new Product();
        product2.setId(PRODUCT2_ID);
        product2.setPrice(PRODUCT2_PRICE);
        when(restTemplate.postForEntity(PRODUCT_API_URL,2, Product.class))
                .thenReturn(ResponseEntity.ok(product2));

        CartCalcRespDTO res = cartService.calc(this.getCartCalcReqDTO());

        assertEquals(2, res.getProducts().size());
        assertEquals(PRODUCT1_PRICE*PRODUCT1_COUNT, res.getProducts().get(0).getSum());
        assertEquals(PRODUCT2_PRICE*PRODUCT2_COUNT, res.getProducts().get(1).getSum());
        assertEquals(PRODUCT1_PRICE*PRODUCT1_COUNT + PRODUCT2_PRICE*PRODUCT2_COUNT, res.getTotal());
    }

    private CartCalcReqDTO getCartCalcReqDTO() {
        CartCalcProductReqDTO productReqDTO = new CartCalcProductReqDTO();
        productReqDTO.setId(PRODUCT1_ID);
        productReqDTO.setCount(PRODUCT1_COUNT);

        CartCalcProductReqDTO productReqDTO2 = new CartCalcProductReqDTO();
        productReqDTO2.setId(PRODUCT2_ID);
        productReqDTO2.setCount(PRODUCT2_COUNT);

        List<CartCalcProductReqDTO> products = Arrays.asList(productReqDTO, productReqDTO2);

        CustomerAddressDTO addressDTO = new CustomerAddressDTO();
        addressDTO.setId(25);

        CartCalcReqDTO cartDTO = new CartCalcReqDTO();
        cartDTO.setProducts(products);
        cartDTO.setPaymentType(PaymentType.CARD);
        cartDTO.setCustomerAddress(addressDTO);
        return cartDTO;
    }
}