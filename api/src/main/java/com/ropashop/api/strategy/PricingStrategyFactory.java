package com.ropashop.api.strategy;

import com.ropashop.api.model.TipoPrecio;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class PricingStrategyFactory {

    private final Map<String, PricingStrategy> estrategias;

    public PricingStrategyFactory(Map<String, PricingStrategy> estrategias) {
        this.estrategias = estrategias;
    }

    public PricingStrategy getStrategy(TipoPrecio tipo) {
        String nombreEstrategia = "estrategia" + tipo.name();
        PricingStrategy estrategia = estrategias.get(nombreEstrategia);
        if (estrategia == null) {
            throw new IllegalArgumentException("No existe estrategia para: " + tipo);
        }
        return estrategia;
    }
}
