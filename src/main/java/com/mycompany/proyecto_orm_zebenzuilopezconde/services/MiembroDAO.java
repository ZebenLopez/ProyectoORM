package com.mycompany.proyecto_orm_zebenzuilopezconde.services;


import com.mycompany.proyecto_orm_zebenzuilopezconde.entities.Miembro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

public class MiembroDAO {

    private final SessionFactory sessionFactory;

    public MiembroDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Miembro> obtenerTodos() {
        try (Session session = sessionFactory.openSession()) {
            // Se crea una consulta HQL para seleccionar todos los aviones
            String hql = "FROM Miembro";
            Query<Miembro> query = session.createQuery(hql, Miembro.class);

            // Se ejecuta la consulta y se devuelve la lista de aviones
            return query.list();
        }
    }

    public Miembro obtenerMiembroPorId(Long idMiembro) {
        try (Session session = sessionFactory.openSession()) {
            // Utilizar HQL para obtener el Miembro por su ID
            String hql = "FROM Miembro WHERE id_miembro = :id";
            return session.createQuery(hql, Miembro.class)
                    .setParameter("id", idMiembro)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("No se encontró un miembro con ID: " + idMiembro);
            return null;
        }
    }
    public void eliminarMiembroPorId(Long idMiembro) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Carga el avión que se va a eliminar
                Miembro miembro = session.get(Miembro.class, idMiembro);

                if (miembro != null) {
                    session.remove(miembro);
                    mostrarMensaje("Miembro eliminado correctamente con ID: " + idMiembro);
                } else {
                    mostrarMensaje("No se encontró un miembro con ID: " + idMiembro);
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

    public void insertarMiembro(Miembro miembro) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Guardar el miembro en la base de datos
                session.persist(miembro);

                transaction.commit();
                mostrarMensaje("Miembro insertado correctamente con ID: " + miembro.getId_miembro());
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    mostrarMensaje("Error al insertar el miembro");
                }
                e.printStackTrace();
            }
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

}
