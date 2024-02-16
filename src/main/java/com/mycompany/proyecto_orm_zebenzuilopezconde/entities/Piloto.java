package com.mycompany.proyecto_orm_zebenzuilopezconde.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "piloto")
public class Piloto extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_piloto;

    @Column(name = "hora_de_vuelo")
    private int horasDeVuelo;

//    @OneToMany(mappedBy = "piloto",cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private Set<Vuelo> vuelos;

    public Piloto() {
    }

    public Piloto(String codigo, String nombre, int horasDeVuelo) {
        super.codigo = codigo;
        super.nombre = nombre;
        this.horasDeVuelo = horasDeVuelo;
    }

    public int getHorasDeVuelo() {
        return horasDeVuelo;
    }

    public Long getId_piloto() {
        return id_piloto;
    }

//    public Set<Vuelo> getVuelos() {
//        return vuelos;
//    }

    public String getCodigo() {
        return codigo;
    }

    public void setId_piloto(Long id_piloto) {
        this.id_piloto = id_piloto;
    }

//    public void setVuelos(Set<Vuelo> vuelos) {
//        this.vuelos = vuelos;
//    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setHorasDeVuelo(int horasDeVuelo) {
        this.horasDeVuelo = horasDeVuelo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
