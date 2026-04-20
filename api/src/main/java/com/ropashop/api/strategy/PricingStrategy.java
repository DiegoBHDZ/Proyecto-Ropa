package com.ropashop.api.strategy;

import com.ropashop.api.model.Prenda;
import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calcularPrecioVenta(Prenda prenda);
}
