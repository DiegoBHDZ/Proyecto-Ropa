package com.ropashop.api.controller;

import com.ropashop.api.model.Prenda;
import com.ropashop.api.service.PrendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/prendas")
public class PrendaController {

    private final PrendaService prendaService;

    public PrendaController(PrendaService prendaService) {
        this.prendaService = prendaService;
    }

    @GetMapping
    public List<Prenda> obtenerTodas() {
        return prendaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prenda> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prendaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrenda(@PathVariable Long id) {
        prendaService.eliminarPrenda(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public Prenda guardarPrenda(@Valid @RequestBody Prenda prenda) {
        return prendaService.guardarPrenda(prenda);
    }

    @GetMapping("/{id}/precio")
    public ResponseEntity<BigDecimal> obtenerPrecioVenta(@PathVariable Long id) {
        return ResponseEntity.ok(prendaService.calcularPrecioVenta(id));
    }
}
