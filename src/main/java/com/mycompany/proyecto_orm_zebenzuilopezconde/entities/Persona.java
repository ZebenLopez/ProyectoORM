package com.mycompany.proyecto_orm_zebenzuilopezconde.entities;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Persona {
    public String codigo;
    public String nombre;

}
