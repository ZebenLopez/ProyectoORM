package com.mycompany.proyecto_orm_zebenzuilopezconde.services;


import com.mycompany.proyecto_orm_zebenzuilopezconde.entities.Vuelo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

public class VueloDAO {

    private final SessionFactory sessionFactory;

    public VueloDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Vuelo> obtenerTodos() {
        try (Session session = sessionFactory.openSession()) {
            // Se crea una consulta HQL para seleccionar todos los aviones
            String hql = "FROM Vuelo";
            Query<Vuelo> query = session.createQuery(hql, Vuelo.class);

            // Se ejecuta la consulta y se devuelve la lista de aviones
            return query.list();
        }
    }

     public void insertarVuelo(Vuelo vuelo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Guardar el vuelo en la base de datos
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

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
