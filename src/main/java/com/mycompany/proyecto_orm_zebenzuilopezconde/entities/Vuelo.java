package com.mycompany.proyecto_orm_zebenzuilopezconde.entities;

import jakarta.persistence.*;
import lombok.Setter;

/**
 * @autor Zebenzui López Conde
 * @version 1.0
 *
 * Curso: 2ºA DAM
 * Representa un vuelo en el sistema.
 */
@Entity
public class Vuelo {

    /**
     * El identificador único del vuelo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vuelo;

    /**
     * El origen del vuelo.
     */
    @Setter
    private String origen;

    /**
     * El destino del vuelo.
     */
    @Setter
    private String destino;

    /**
     * El número de vuelo.
     */
    @Setter
    private String numeroDeVuelo;

    /**
     * La fecha del vuelo.
     */
    private String fechaVuelo;

    /**
     * La hora de salida del vuelo.
     */
    @Setter
    private String horaSalida;

    /**
     * El piloto del vuelo.
     */
    @Setter
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_piloto")
    private Piloto piloto;

    /**
     * El miembro asociado al vuelo.
     */
    @Setter
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_miembro")
    private Miembro miembro;

    /**
     * El avión asociado al vuelo.
     */
    @Setter
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_avion")
    private Avion avion;

    /**
     * Constructor vacío para la creación de un objeto Vuelo.
     */
    public Vuelo() {
    }

    /**
     * Constructor para la creación de un objeto Vuelo con origen, destino, número de vuelo, fecha, hora de salida y avión.
     *
     * @param origen El origen del vuelo.
     * @param destino El destino del vuelo.
     * @param numeroDeVuelo El número de vuelo.
     * @param fecha La fecha del vuelo.
     * @param horaSalida La hora de salida del vuelo.
     * @param avion El avión asociado al vuelo.
     */
    public Vuelo(String origen, String destino, String numeroDeVuelo, String fecha, String horaSalida, Avion avion) {
        this.origen = origen;
        this.destino = destino;
        this.numeroDeVuelo = numeroDeVuelo;
        this.fechaVuelo = fecha;
        this.horaSalida = horaSalida;
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

    public String getHoraSalida() {
        return horaSalida;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setId(Long id) {
        this.id_vuelo = id;
    }

    public void setFecha(String fecha) {
        this.fechaVuelo = fecha;
    }

    public Long getId_vuelo() {
        return id_vuelo;
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

}
