package ru.skipp.test.cart.entity.cart.dto;

import lombok.Data;
import ru.skipp.test.cart.entity.customer.dto.CustomerAddressDTO;
import ru.skipp.test.cart.entity.payment.PaymentType;

import java.util.List;

@Data
public class CartCalcReqDTO {
    private List<CartCalcProductReqDTO> products;
    private PaymentType paymentType;
    private CustomerAddressDTO customerAddress;
}
