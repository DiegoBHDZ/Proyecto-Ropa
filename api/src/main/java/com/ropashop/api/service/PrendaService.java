package com.ropashop.api.service;

import com.ropashop.api.model.Prenda;
import com.ropashop.api.model.Categoria;
import com.ropashop.api.repository.PrendaRepository;
import com.ropashop.api.repository.CategoriaRepository;
import com.ropashop.api.strategy.PricingStrategy;
import com.ropashop.api.strategy.PricingStrategyFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PrendaService {

    private final PrendaRepository prendaRepository;
    private final CategoriaRepository categoriaRepository;
    private final PricingStrategyFactory strategyFactory;

    public PrendaService(PrendaRepository prendaRepository, CategoriaRepository categoriaRepository,
                         PricingStrategyFactory strategyFactory) {
        this.prendaRepository = prendaRepository;
        this.categoriaRepository = categoriaRepository;
        this.strategyFactory = strategyFactory;
    }

    public List<Prenda> obtenerTodas() {
        return prendaRepository.findAll();
    }

    public Prenda guardarPrenda(Prenda prenda) {
        Categoria categoria = categoriaRepository.findById(prenda.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + prenda.getCategoria().getId()));
        prenda.setCategoria(categoria);
        return prendaRepository.save(prenda);
    }

    public Prenda buscarPorId(Long id) {
        return prendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenda no encontrada con ID: " + id));
    }

    public void eliminarPrenda(Long id) {
        Prenda prenda = buscarPorId(id);
        prendaRepository.delete(prenda);
    }

    public BigDecimal calcularPrecioVenta(Long idPrenda) {
        Prenda prenda = prendaRepository.findById(idPrenda)
                .orElseThrow(() -> new RuntimeException("Prenda no encontrada"));
        PricingStrategy estrategia = strategyFactory.getStrategy(prenda.getTipoPrecio());
        return estrategia.calcularPrecioVenta(prenda);
    }
}
