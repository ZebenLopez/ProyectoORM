package com.mycompany.proyecto_orm_zebenzuilopezconde.services;

import com.mycompany.proyecto_orm_zebenzuilopezconde.entities.Piloto;
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
 * Clase DAO para la entidad Piloto.
 */
public class PilotoDAO {
    private final SessionFactory sessionFactory;
    /**
     * Constructor que recibe la SessionFactory (debe ser inyectado por Spring o similar)
     *
     * @param sessionFactory La SessionFactory para interactuar con la base de datos.
     */
    public PilotoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**
     * Método para obtener todos los pilotos utilizando SQL nativo
     *
     * @return Una lista de todos los pilotos.
     */
    public List<Piloto> verTodosLosPilotos() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Piloto";
            Query<Piloto> query = session.createQuery(hql, Piloto.class);
            return query.list();
        }
    }
    /**
     * Método para obtener un piloto por su ID.
     *
     * @param idPiloto El ID del piloto.
     * @return El piloto con el ID proporcionado.
     */
    public Piloto verPilotoPorId(Long idPiloto) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Piloto WHERE id_piloto = :id";
            return session.createQuery(hql, Piloto.class)
                    .setParameter("id", idPiloto)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            joptionMessage("No se encontró un piloto con ID: " + idPiloto);
            return null;
        }
    }
    /**
     * Método para eliminar un piloto por su ID.
     *
     * @param idPiloto El ID del piloto a eliminar.
     */
    public void eliminarPilotoPorId(Long idPiloto) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Piloto piloto = session.get(Piloto.class, idPiloto);
                if (piloto != null) {
                    session.remove(piloto);
                    joptionMessage("Piloto eliminado correctamente con ID: " + idPiloto);
                } else {
                    joptionMessage("No se encontró un piloto con ID: " + idPiloto);
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
     * Método para insertar un piloto en la base de datos.
     *
     * @param piloto El piloto a insertar.
     */
    public void insertarPiloto(Piloto piloto) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.persist(piloto);
                transaction.commit();
                joptionMessage("Piloto insertado correctamente con ID: " + piloto.getId_piloto());
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    joptionMessage("Error al insertar el piloto");
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