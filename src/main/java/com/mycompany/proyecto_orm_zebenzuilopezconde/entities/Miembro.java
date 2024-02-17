package com.mycompany.proyecto_orm_zebenzuilopezconde.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;
/**
 * @autor Zebenzui López Conde
 * @version 1.0
 *
 * Curso: 2ºA DAM
 * Representa un miembro en el sistema.
 */
@Getter
@Entity
public class Miembro extends Persona {

    /**
     * El identificador único del miembro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_miembro;

    /**
     * Los vuelos asociados a este miembro.
     */
//    @Setter
    @OneToMany(mappedBy = "miembro", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Vuelo> vuelos;

    /**
     * Constructor vacío para la creación de un objeto Miembro.
     */
    public Miembro() {
    }

    /**
     * Constructor para la creación de un objeto Miembro con código y nombre.
     *
     * @param codigo El código del miembro.
     * @param nombre El nombre del miembro.
     */
    public Miembro(String codigo, String nombre) {
        super.codigo = codigo;
        super.nombre = nombre;
    }

    /**
     * Devuelve el nombre del miembro.
     *
     * @return El nombre del miembro.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el código del miembro.
     *
     * @return El código del miembro.
     */
    public String getCodigo() {
        return codigo;
    }

}