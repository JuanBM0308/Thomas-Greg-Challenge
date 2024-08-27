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
@Table(name = "orden_producto")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creado_en")
    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "orden_id", referencedColumnName = "id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "cantidad")
    private Long quantity;

    @Column(name = "total")
    private Long total;
}
