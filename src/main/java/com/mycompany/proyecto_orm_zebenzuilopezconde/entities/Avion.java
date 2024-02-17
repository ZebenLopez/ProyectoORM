package com.mycompany.proyecto_orm_zebenzuilopezconde.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
/**
 * @autor Zebenzui López Conde
 * @version 1.0
 *
 * Curso: 2ºA DAM
 */

/**
 * Clase Avion.
 * Representa un avión en el sistema.
 */
@Entity
@Table(name = "avion")
public class Avion {
    /**
     * El identificador único del avión.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_avion;

    /**
     * El código del avión.
     */
    @Getter
    private String codigo;

    /**
     * El tipo de avión.
     */
    private String tipo;

    /**
     * El vuelo asociado a este avión.
     */
//    @Getter
//    @Setter
    @OneToMany(mappedBy = "avion", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Vuelo> vuelos;

    /**
     * Constructor vacío para la creación de un objeto Avion.
     */
    public Avion() {
    }

    /**
     * Constructor para la creación de un objeto Avion con código y tipo.
     *
     * @param codigo El código del avión.
     * @param tipo El tipo de avión.
     */
    public Avion(String codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    /**
     * Devuelve una representación en cadena de caracteres del objeto Avion.
     *
     * @return Una cadena de caracteres que representa el objeto Avion.
     */
    @Override
    public String toString() {
        return "Avion{id_avion=" + id_avion + ", codigo='" + codigo + "', tipo='" + tipo + "'}";
    }
}