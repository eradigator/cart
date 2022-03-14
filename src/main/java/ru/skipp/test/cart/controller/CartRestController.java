package ru.skipp.test.cart.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skipp.test.cart.entity.cart.dto.CartCalcReqDTO;
import ru.skipp.test.cart.entity.cart.dto.CartCalcRespDTO;
import ru.skipp.test.cart.service.ICartService;

@AllArgsConstructor
@RestController
@RequestMapping("v1/cart")
public class CartRestController {

    private ICartService cartService;

    /**
     * Calculates a shopping cart items price
     * @param cart {@link CartCalcReqDTO} - a list of items to calculate a price
     * @return {@link CartCalcRespDTO} with a price for each item in shopping cart and a total amount
     */
    @PostMapping("calc")
    public ResponseEntity<CartCalcRespDTO> calc(@RequestBody CartCalcReqDTO cart) {
        return ResponseEntity.ok(cartService.calc(cart));
    }

}
