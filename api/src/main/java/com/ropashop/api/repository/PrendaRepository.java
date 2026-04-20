package com.ropashop.api.repository;

import com.ropashop.api.model.Prenda;
import com.ropashop.api.model.EstatusPrenda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrendaRepository extends JpaRepository<Prenda, Long> {
    boolean existsBySku(String sku);
    List<Prenda> findByEstatus(EstatusPrenda estatus);
}
