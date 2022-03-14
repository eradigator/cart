package ru.skipp.test.cart.service;

import ru.skipp.test.cart.entity.cart.Product;

public interface IProductRestClientService {
    Product getProductById(int id);
}
