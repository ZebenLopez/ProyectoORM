package com.mycompany.proyecto_orm_zebenzuilopezconde.services;

import com.mycompany.proyecto_orm_zebenzuilopezconde.entities.Piloto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

public class PilotoDAO {

    private final SessionFactory sessionFactory;

    public PilotoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Piloto> obtenerTodos() {
        try (Session session = sessionFactory.openSession()) {
            // Se crea una consulta HQL para seleccionar todos los aviones
            String hql = "FROM Piloto";
            Query<Piloto> query = session.createQuery(hql, Piloto.class);

            // Se ejecuta la consulta y se devuelve la lista de aviones
            return query.list();
        }
    }

    public Piloto obtenerPilotoPorId(Long idPiloto) {
        try (Session session = sessionFactory.openSession()) {
            // Utilizar HQL para obtener el Piloto por su ID
            String hql = "FROM Piloto WHERE id_piloto = :id";
            return session.createQuery(hql, Piloto.class)
                    .setParameter("id", idPiloto)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensaje("No se encontró un piloto con ID: " + idPiloto);
            return null;
        }
    }
    public void eliminarPilotoPorId(Long idPiloto) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Carga el avión que se va a eliminar
                Piloto piloto = session.get(Piloto.class, idPiloto);

                if (piloto != null) {
                    session.remove(piloto);
                    mostrarMensaje("Piloto eliminado correctamente con ID: " + idPiloto);
                } else {
                    mostrarMensaje("No se encontró un piloto con ID: " + idPiloto);
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

    public void insertarPiloto(Piloto piloto) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Guardar el piloto en la base de datos
                session.persist(piloto);

                transaction.commit();
                mostrarMensaje("Piloto insertado correctamente con ID: " + piloto.getId_piloto());
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    mostrarMensaje("Error al insertar el piloto");
                }
                e.printStackTrace();
            }
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
