package com.mycompany.proyecto_orm_zebenzuilopezconde.services;


import com.mycompany.proyecto_orm_zebenzuilopezconde.entities.Avion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

public class AvionDAO {

    private final SessionFactory sessionFactory;

    // Constructor que recibe la SessionFactory (debe ser inyectado por Spring o similar)
    public AvionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Método para obtener todos los aviones utilizando SQL nativo
    public List<Avion> obtenerTodos() {
        try (Session session = sessionFactory.openSession()) {
            // Se crea una consulta HQL para seleccionar todos los aviones
            String hql = "FROM Avion";
            Query<Avion> query = session.createQuery(hql, Avion.class);
            // Se ejecuta la consulta y se devuelve la lista de aviones
            return query.list();
        }
    }
    public Avion obtenerAvionPorId(Long idAvion) {
        try (Session session = sessionFactory.openSession()) {
            // Utilizar HQL para obtener el Avión por su ID
            String hql = "FROM Avion WHERE id_avion = :id";
            return session.createQuery(hql, Avion.class)
                    .setParameter("id", idAvion)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("No se encontró un avión con ID: " + idAvion);
            return null;
        }
    }


    public void eliminarAvionPorId(Long idAvion) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Carga el avión que se va a eliminar
                Avion avion = session.get(Avion.class, idAvion);

                if (avion != null) {
                    session.remove(avion);
                    mostrarMensaje("Avión eliminado correctamente con ID: " + idAvion);
                } else {
                    mostrarMensaje("No se encontró un avión con ID: " + idAvion);
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

    public void insertarAvion(Avion avion) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Guardar el avión en la base de datos
                session.persist(avion);

                transaction.commit();
                mostrarMensaje("Avión insertado correctamente con ID: " + avion.getId_avion());
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    mostrarMensaje("Error al insertar el avión");
                }
                e.printStackTrace();
            }
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
