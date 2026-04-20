package com.ropashop.api.strategy;

import com.ropashop.api.model.Prenda;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("estrategiaLIQUIDACION")
public class LiquidacionStrategy implements PricingStrategy {

    @Override
    public BigDecimal calcularPrecioVenta(Prenda prenda) {
        // Precio Venta = Precio Compra * 1.10 (10% de ganancia - liquidación)
        return prenda.getPrecioCompra().multiply(new BigDecimal("1.10"));
    }
}
