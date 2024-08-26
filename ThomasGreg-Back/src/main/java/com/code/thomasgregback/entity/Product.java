package com.code.thomasgregback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creado_en")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "marca_id", referencedColumnName = "id")
    private Mark mark;

    @Column(name = "precio_compra")
    private Long purchasePrice;

    @Column(name = "precio_venta")
    private Long salePrice;

    @Column(name = "nombre")
    private String name;

    @Column(name = "extistencia")
    private Long stock;

    @Column(name = "es_activo")
    private boolean active;
}
