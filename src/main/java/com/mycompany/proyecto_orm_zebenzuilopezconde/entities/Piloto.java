package com.mycompany.proyecto_orm_zebenzuilopezconde.entities;

import jakarta.persistence.*;
/**
 * @autor Zebenzui López Conde
 * @version 1.0
 *
 * Curso: 2ºA DAM
 * Representa un piloto en el sistema.
 */
@Entity
@Table(name = "piloto")
public class Piloto extends Persona {

    /**
     * El identificador único del piloto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_piloto;

    /**
     * Las horas de vuelo del piloto.
     */
    @Column(name = "hora_de_vuelo")
    private int horasDeVuelo;

    /**
     * Constructor vacío para la creación de un objeto Piloto.
     */
    public Piloto() {
    }

    /**
     * Constructor para la creación de un objeto Piloto con código, nombre y horas de vuelo.
     *
     * @param codigo El código del piloto.
     * @param nombre El nombre del piloto.
     * @param horasDeVuelo Las horas de vuelo del piloto.
     */
    public Piloto(String codigo, String nombre, int horasDeVuelo) {
        super.codigo = codigo;
        super.nombre = nombre;
        this.horasDeVuelo = horasDeVuelo;
    }

    /**
     * Devuelve las horas de vuelo del piloto.
     *
     * @return Las horas de vuelo del piloto.
     */
    public int getHorasDeVuelo() {
        return horasDeVuelo;
    }

    /**
     * Devuelve el identificador único del piloto.
     *
     * @return El identificador único del piloto.
     */
    public Long getId_piloto() {
        return id_piloto;
    }

    /**
     * Devuelve el nombre del piloto.
     *
     * @return El nombre del piloto.
     */
    public String getNombre() {
        return nombre;
    }

}