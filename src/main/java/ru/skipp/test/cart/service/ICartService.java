package ru.skipp.test.cart.service;

import org.springframework.lang.NonNull;
import ru.skipp.test.cart.entity.cart.dto.CartCalcReqDTO;
import ru.skipp.test.cart.entity.cart.dto.CartCalcRespDTO;

public interface ICartService {
    CartCalcRespDTO calc(@NonNull CartCalcReqDTO cartDTO);
}
