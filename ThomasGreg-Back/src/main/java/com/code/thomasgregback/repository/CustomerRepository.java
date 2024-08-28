package com.code.thomasgregback.repository;

import com.code.thomasgregback.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c.id, c.nombre, c.apellido, COUNT(*) total\n" +
            "FROM orden o\n" +
            "INNER JOIN cliente c ON o.cliente_id = c.id\n" +
            "WHERE o.es_saliente = 1\n" +
            "GROUP BY c.id\n" +
            "ORDER BY total DESC\n" +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTopFiveCustomers();
}
