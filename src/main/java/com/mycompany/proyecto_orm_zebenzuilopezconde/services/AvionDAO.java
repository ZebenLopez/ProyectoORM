package com.mycompany.proyecto_orm_zebenzuilopezconde.services;

import com.mycompany.proyecto_orm_zebenzuilopezconde.entities.Avion;
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
 * Curso: 2ºA DAM
 * Clase DAO para la entidad Avion.
 */
public class AvionDAO {
    private final SessionFactory sessionFactory;
    /**
     * Constructor que recibe la SessionFactory (debe ser inyectado por Spring o similar)
     *
     * @param sessionFactory La SessionFactory para interactuar con la base de datos.
     */
    public AvionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**
     * Método para obtener todos los aviones utilizando SQL nativo
     *
     * @return Una lista de todos los aviones.
     */
    public List<Avion> verTodosLosAviones() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Avion";
            Query<Avion> query = session.createQuery(hql, Avion.class);
            return query.list();
        }
    }
    /**
     * Método para obtener un avión por su ID.
     *
     * @param idAvion El ID del avión.
     * @return El avión con el ID proporcionado.
     */
    public Avion verAvionPorId(Long idAvion) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Avion WHERE id_avion = :id";
            return session.createQuery(hql, Avion.class)
                    .setParameter("id", idAvion)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            joptionMessage("No se encontró un avión con ID: " + idAvion);
            return null;
        }
    }
    /**
     * Método para eliminar un avión por su ID.
     *
     * @param idAvion El ID del avión a eliminar.
     */
    public void eliminarAvionPorId(Long idAvion) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();
                Avion avion = session.get(Avion.class, idAvion);
                if (avion != null) {
                    session.remove(avion);
                    joptionMessage("Avión eliminado correctamente con ID: " + idAvion);
                } else {
                    joptionMessage("No se encontró un avión con ID: " + idAvion);
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
     * Método para insertar un avión en la base de datos.
     *
     * @param avion El avión a insertar.
     */
    public void insertarAvion(Avion avion) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.persist(avion);
                transaction.commit();
                joptionMessage("Avión insertado correctamente con ID: " + avion.getId_avion());
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    joptionMessage("Error al insertar el avión");
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
    private void joptionMessage(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}