package com.ropashop.api.strategy;

import com.ropashop.api.model.Prenda;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("estrategiaTEMPORADA")
public class TemporadaStrategy implements PricingStrategy {

    @Override
    public BigDecimal calcularPrecioVenta(Prenda prenda) {
        // Precio Venta = Precio Compra * 1.80 (80% de ganancia - colección nueva)
        return prenda.getPrecioCompra().multiply(new BigDecimal("1.80"));
    }
}
