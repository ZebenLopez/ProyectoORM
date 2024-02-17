package com.mycompany.proyecto_orm_zebenzuilopezconde.services;

import com.mycompany.proyecto_orm_zebenzuilopezconde.entities.Vuelo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

/**
 * @version 1.0
 * <p>
 * Clase DAO para la entidad Vuelo.
 * @autor Zebenzui López Conde
 */
public class VueloDAO {
    private final SessionFactory sessionFactory;

    /**
     * Constructor que recibe la SessionFactory (debe ser inyectado por Spring o similar)
     *
     * @param sessionFactory La SessionFactory para interactuar con la base de datos.
     */
    public VueloDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Método para obtener todos los vuelos utilizando SQL nativo
     *
     * @return Una lista de todos los vuelos.
     */
    public List<Vuelo> verTodosLosVuelos() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Vuelo";
            Query<Vuelo> query = session.createQuery(hql, Vuelo.class);
            return query.list();
        }
    }

    /**
     * Método para insertar un vuelo en la base de datos.
     *
     * @param vuelo El vuelo a insertar.
     */
public void insertarVuelo(Vuelo vuelo) {
    Transaction transaction = null;
    try (Session session = sessionFactory.openSession()) {
        // Verificar si el vuelo ya existe
        if (vuelo.getId() != null) {
            Vuelo vueloExistente = session.get(Vuelo.class, vuelo.getId());
            if (vueloExistente != null) {
                joptionMessage("Ya existe un vuelo con ID: " + vuelo.getId());
                return;
            }
        }
        // Verificar si el piloto, miembro o avión ya están asignados a un vuelo en la misma fecha y hora
        if (existeVueloConMismosRecursos(vuelo)) {
            joptionMessage("Ya existe un vuelo con los mismos recursos en la misma fecha y hora");
            return;
        }

        transaction = session.beginTransaction();
        session.persist(vuelo);
        transaction.commit();
        joptionMessage("Vuelo insertado correctamente");
    } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        joptionMessage("Error al insertar el vuelo: " + e.getMessage());
        e.printStackTrace();
    }
}

    /**
     * Método para eliminar un vuelo por su ID.
     *
     * @param idVuelo El ID del vuelo a eliminar.
     */
    public void eliminarVueloPorId(Long idVuelo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Vuelo vuelo = session.get(Vuelo.class, idVuelo);
                if (vuelo != null) {
                    session.remove(vuelo);
                    joptionMessage("Vuelo eliminado correctamente");
                } else {
                    joptionMessage("No se encontró un vuelo con ID: " + idVuelo);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                joptionMessage("Error al eliminar el vuelo");
                e.printStackTrace();
            }
        }
    }

    public boolean existeVueloConMismosRecursos(Vuelo vuelo) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Vuelo v WHERE v.fechaVuelo = :fecha AND v.horaSalida = :hora AND (v.piloto.id_piloto = :pilotoId OR v.miembro.id_miembro = :miembroId)");
            query.setParameter("fecha", vuelo.getFechaVuelo());
            query.setParameter("hora", vuelo.getHoraSalida());
            query.setParameter("pilotoId", vuelo.getPiloto().getId_piloto());
            query.setParameter("miembroId", vuelo.getMiembro().getId_miembro());
//            query.setParameter("avionId", vuelo.getAvion().getId_avion());
            List results = query.list();
            return !results.isEmpty();
        }
    }

    /**
     * Método para mostrar un mensaje en un JOptionPane.
     *
     * @param mensaje El mensaje a mostrar.
     */
    private void joptionMessage(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}