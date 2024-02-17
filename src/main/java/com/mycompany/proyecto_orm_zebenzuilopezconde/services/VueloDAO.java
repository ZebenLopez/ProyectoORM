package com.mycompany.proyecto_orm_zebenzuilopezconde.services;

import com.mycompany.proyecto_orm_zebenzuilopezconde.entities.Vuelo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;
/**
 * @autor Zebenzui López Conde
 * @version 1.0
 *
 * Clase DAO para la entidad Vuelo.
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
            transaction = session.beginTransaction();
            session.persist(vuelo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            mostrarMensaje("Error al insertar el vuelo");
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
                    mostrarMensaje("Vuelo eliminado correctamente");
                } else {
                    mostrarMensaje("No se encontró un vuelo con ID: " + idVuelo);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
    /**
     * Método para mostrar un mensaje en un JOptionPane.
     *
     * @param mensaje El mensaje a mostrar.
     */
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}