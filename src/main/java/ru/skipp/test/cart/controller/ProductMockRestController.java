package ru.skipp.test.cart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skipp.test.cart.entity.cart.Product;

import java.util.Random;

@RestController
@RequestMapping("/v1/products")
@Slf4j
public class ProductMockRestController {

    @PostMapping("find")
    public ResponseEntity<Product> find(@RequestBody Integer productId) {
        log.info("Mocking find product method, productId=" + productId);

        Product p = new Product();
        p.setId(productId);
        p.setPrice(new Random().nextFloat());

        return ResponseEntity.ok(p);
    }
}
