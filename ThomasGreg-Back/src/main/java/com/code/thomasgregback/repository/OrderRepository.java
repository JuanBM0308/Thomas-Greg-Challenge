package com.code.thomasgregback.repository;

import com.code.thomasgregback.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT p.nombre, p.precio_venta, op.cantidad, op.total\n" +
            "FROM orden_producto op \n" +
            "INNER JOIN orden o ON op.orden_id = o.id\n" +
            "INNER JOIN producto p ON op.producto_id = p.id\n" +
            "WHERE o.id = :orderId", nativeQuery = true)
    List<Object[]> findOrderDetails(@Param("orderId") Long orderId);
}
