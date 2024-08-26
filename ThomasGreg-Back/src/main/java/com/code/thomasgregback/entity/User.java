package com.code.thomasgregback.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastname;

    @Column(name = "correo", unique = true)
    private String mail;

    @Column(name = "contrasenia")
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private Role role;
}
