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
@Table(name = "orden")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creado_en")
    private Date createdAt;

    @Column(name = "numero", unique = true)
    private String number;

    @Column(name = "es_electronico")
    private boolean ecommerce;

    @Column(name = "es_saliente")
    private boolean outbound;

    @Column(name = "es_aleatorio")
    private boolean random;

    @Column(name = "total")
    private Long total;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private User user;
}
