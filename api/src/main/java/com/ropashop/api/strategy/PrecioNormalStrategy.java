package com.ropashop.api.strategy;

import com.ropashop.api.model.Prenda;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("estrategiaPRECIO_NORMAL")
public class PrecioNormalStrategy implements PricingStrategy {

    @Override
    public BigDecimal calcularPrecioVenta(Prenda prenda) {
        // Precio Venta = Precio Compra * 1.50 (50% de ganancia)
        return prenda.getPrecioCompra().multiply(new BigDecimal("1.50"));
    }
}
