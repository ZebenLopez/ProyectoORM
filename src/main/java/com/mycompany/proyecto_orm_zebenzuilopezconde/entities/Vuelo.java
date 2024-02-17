package com.mycompany.proyecto_orm_zebenzuilopezconde.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Entity
public class Vuelo {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vuelo;

    @Getter
    @Setter
    private String origen;

    @Getter
    @Setter
    private String destino;

    @Getter
    @Setter
    private String numeroDeVuelo;

    @Setter
    @Column(columnDefinition = "DATE")
    private LocalDate fechaVuelo;

    @Getter
    @Setter
    @Column(columnDefinition = "TIME")
    private LocalTime horaSalida;

    @Setter
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_piloto")
    private Piloto piloto;

    @Setter
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_miembro")
    private Miembro miembro;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_avion")
    private Avion avion;

    public Vuelo() {
    }

    public Vuelo(String origen, String destino, String numeroDeVuelo, LocalDate fecha, LocalTime horaSalida, Avion avion) {
        this.origen = origen;
        this.destino = destino;
        this.numeroDeVuelo = numeroDeVuelo;
        this.fechaVuelo = fecha;
        this.horaSalida = horaSalida;
        this.avion = avion;
    }

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

    public Long getId() {
        return id_vuelo;
    }

    public void setId(Long id) {
        this.id_vuelo = id;
    }

    public LocalDate getFechaVuelo() {
        return fechaVuelo;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public Miembro getMiembro() {
        return miembro;
    }
}