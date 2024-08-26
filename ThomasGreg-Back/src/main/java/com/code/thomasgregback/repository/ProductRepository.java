package com.code.thomasgregback.repository;

import com.code.thomasgregback.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActive(boolean active);

    @Query(value = "SELECT p.id, p.nombre, SUM(op.cantidad) total\n" +
            "FROM orden o\n" +
            "INNER JOIN orden_producto op ON op.orden_id = o.id\n" +
            "INNER JOIN producto p ON op.producto_id = p.id\n" +
            "WHERE o.es_saliente = 1\n" +
            "GROUP BY p.id, p.nombre\n" +
            "ORDER BY total DESC\n" +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTopFiveProducts();
}
