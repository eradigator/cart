package ru.skipp.test.cart.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.skipp.test.cart.entity.cart.Product;
import ru.skipp.test.cart.service.IProductRestClientService;

@Service
@Slf4j
public class ProductRestClientService implements IProductRestClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${product.service.api.url}")
    private String productServiceApiUrl;

    @Cacheable(value = "productCache")
    public Product getProductById(int id) {
        Product product = null;

        try {
            ResponseEntity<Product> res = restTemplate.postForEntity(productServiceApiUrl, id, Product.class);
            product = res.getBody();
        } catch (Exception e) {
            log.error("Product service unavailable, " + e.getMessage());
        }

        return product;
    }

    @Scheduled(fixedDelay = 3_600_000)
    @CacheEvict(value = "productCache", allEntries = true)
    public void clearCache() {
    }
}
