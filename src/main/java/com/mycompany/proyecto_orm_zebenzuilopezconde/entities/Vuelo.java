package com.mycompany.proyecto_orm_zebenzuilopezconde.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vuelo;

    private String origen;

    private String destino;

    private String numeroDeVuelo;

    private String fechaVuelo;

    private String horaSalida;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_piloto")
    private Piloto piloto;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_miembro")
    private Miembro miembro;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_avion")
    private Avion avion;

    public Vuelo() {
    }

    public Vuelo(String origen, String destino, String numeroDeVuelo, String fecha, String horaSalida, Avion avion) {
        this.origen = origen;
        this.destino = destino;
        this.numeroDeVuelo = numeroDeVuelo;
        this.fechaVuelo = fecha;
        this.horaSalida = horaSalida;
        this.avion = avion;
    }

    public Vuelo(Long id_vuelo, String origen, String destino, String numeroDeVuelo, String fechaVuelo, String horaSalida, Piloto piloto, Miembro miembro, Avion avion) {
        this.id_vuelo = id_vuelo;
        this.origen = origen;
        this.destino = destino;
        this.numeroDeVuelo = numeroDeVuelo;
        this.fechaVuelo = fechaVuelo;
        this.horaSalida = horaSalida;
        this.piloto = piloto;
        this.miembro = miembro;
        this.avion = avion;
    }

    public Vuelo(String codigo, String origen, String destino, String fecha, String hora, Piloto piloto, Avion avion, Miembro miembro) {
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

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getNumeroDeVuelo() {
        return numeroDeVuelo;
    }

    public String getFecha() {
        return fechaVuelo;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setId(Long id) {
        this.id_vuelo = id;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setNumeroDeVuelo(String numeroDeVuelo) {
        this.numeroDeVuelo = numeroDeVuelo;
    }

    public void setFecha(String fecha) {
        this.fechaVuelo = fecha;
    }

    public Long getId_vuelo() {
        return id_vuelo;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public String getFechaVuelo() {
        return fechaVuelo;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setFechaVuelo(String fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

}
