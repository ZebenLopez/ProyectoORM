package com.mycompany.proyecto_orm_zebenzuilopezconde.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @autor Zebenzui López Conde
 * @version 1.0
 *
 * Clase que representa un vuelo.
 */
@Setter
@Entity
public class Vuelo {

    /**
     * ID del vuelo.
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vuelo;

    /**
     * Origen del vuelo.
     */
    @Getter
    @Setter
    private String origen;

    /**
     * Destino del vuelo.
     */
    @Getter
    @Setter
    private String destino;

    /**
     * Número de vuelo.
     */
    @Getter
    @Setter
    private String numeroDeVuelo;

    /**
     * Fecha del vuelo.
     */
    @Setter
    @Column(columnDefinition = "DATE")
    private LocalDate fechaVuelo;

    /**
     * Hora de salida del vuelo.
     */
    @Getter
    @Setter
    @Column(columnDefinition = "TIME")
    private LocalTime horaSalida;

    /**
     * Piloto del vuelo.
     */
    @Setter
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_piloto")
    private Piloto piloto;

    /**
     * Miembro del vuelo.
     */
    @Setter
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_miembro")
    private Miembro miembro;

    /**
     * Avión del vuelo.
     */
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_avion")
    private Avion avion;

    /**
     * Constructor vacío.
     */
    public Vuelo() {
    }

    /**
     * Constructor con origen, destino, número de vuelo, fecha, hora de salida y avión.
     */
    public Vuelo(String origen, String destino, String numeroDeVuelo, LocalDate fecha, LocalTime horaSalida, Avion avion) {
        this.origen = origen;
        this.destino = destino;
        this.numeroDeVuelo = numeroDeVuelo;
        this.fechaVuelo = fecha;
        this.horaSalida = horaSalida;
        this.avion = avion;
    }

    /**
     * Constructor con código, origen, destino, fecha, hora, piloto, avión y miembro.
     */
    public Vuelo(String codigo, String origen, String destino, LocalDate fecha, LocalTime hora, Piloto piloto, Avion avion, Miembro miembro) {
        this.origen = origen;
        this.destino = destino;
        this.numeroDeVuelo = codigo;
        this.fechaVuelo = fecha;
        this.horaSalida = hora;
        this.piloto = piloto;
        this.avion = avion;
        this.miembro = miembro;
    }

    /**
     * Obtiene el ID del vuelo.
     */
    public Long getId() {
        return id_vuelo;
    }

    /**
     * Establece el ID del vuelo.
     */
    public void setId(Long id) {
        this.id_vuelo = id;
    }

    /**
     * Obtiene la fecha del vuelo.
     */
    public LocalDate getFechaVuelo() {
        return fechaVuelo;
    }

    /**
     * Obtiene el piloto del vuelo.
     */
    public Piloto getPiloto() {
        return piloto;
    }

    /**
     * Obtiene el miembro del vuelo.
     */
    public Miembro getMiembro() {
        return miembro;
    }
}