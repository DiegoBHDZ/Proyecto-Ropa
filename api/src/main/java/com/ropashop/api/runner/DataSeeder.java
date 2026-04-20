package com.ropashop.api.runner;

import com.ropashop.api.model.*;
import com.ropashop.api.repository.PrendaRepository;
import com.ropashop.api.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

//@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final PrendaRepository prendaRepository;

    public DataSeeder(CategoriaRepository categoriaRepository, PrendaRepository prendaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.prendaRepository = prendaRepository;
    }

    //@Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0) {
            System.out.println("Guardando datos de prueba");

            Categoria camisas = new Categoria("Camisas", "Camisas y blusas de todo tipo");
            Categoria pantalones = new Categoria("Pantalones", "Pantalones y jeans");
            categoriaRepository.saveAll(List.of(camisas, pantalones));

            Prenda camisa = new Prenda(
                "CAM-001",
                "Camisa Oxford Blanca",
                "M",
                "Blanco",
                "Algodón 100%",
                50,
                new BigDecimal("250.00"),
                EstatusPrenda.DISPONIBLE,
                TipoPrecio.PRECIO_NORMAL,
                camisas
            );

            prendaRepository.save(camisa);
            System.out.println("Datos guardados correctamente");
        }
    }
}
