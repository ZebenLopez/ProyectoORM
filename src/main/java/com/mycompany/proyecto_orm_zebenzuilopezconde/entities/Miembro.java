package com.mycompany.proyecto_orm_zebenzuilopezconde.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Miembro extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_miembro;

    @OneToMany(mappedBy = "miembro", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Vuelo> vuelos;

    public Miembro() {
    }

    public Miembro(String codigo, String nombre) {
        super.codigo = codigo;
        super.nombre = nombre;
    }

    public Long getId_miembro() {
        return id_miembro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setVuelos(Set<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<Vuelo> getVuelos() {
        return vuelos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
