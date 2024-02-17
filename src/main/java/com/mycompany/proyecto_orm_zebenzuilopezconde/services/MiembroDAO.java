package com.mycompany.proyecto_orm_zebenzuilopezconde.services;

import com.mycompany.proyecto_orm_zebenzuilopezconde.entities.Miembro;
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
 * Clase DAO para la entidad Miembro.
 */
public class MiembroDAO {
    private final SessionFactory sessionFactory;
    /**
     * Constructor que recibe la SessionFactory (debe ser inyectado por Spring o similar)
     *
     * @param sessionFactory La SessionFactory para interactuar con la base de datos.
     */
    public MiembroDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**
     * Método para obtener todos los miembros utilizando SQL nativo
     *
     * @return Una lista de todos los miembros.
     */
    public List<Miembro> verTodosLosMiembros() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Miembro";
            Query<Miembro> query = session.createQuery(hql, Miembro.class);
            return query.list();
        }
    }
    /**
     * Método para obtener un miembro por su ID.
     *
     * @param idMiembro El ID del miembro.
     * @return El miembro con el ID proporcionado.
     */
    public Miembro verMiembroPorId(Long idMiembro) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Miembro WHERE id_miembro = :id";
            return session.createQuery(hql, Miembro.class)
                    .setParameter("id", idMiembro)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            joptionMessage("No se encontró un miembro con ID: " + idMiembro);
            return null;
        }
    }
    /**
     * Método para eliminar un miembro por su ID.
     *
     * @param idMiembro El ID del miembro a eliminar.
     */
    public void eliminarMiembroPorId(Long idMiembro) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Miembro miembro = session.get(Miembro.class, idMiembro);
                if (miembro != null) {
                    session.remove(miembro);
                    joptionMessage("Miembro eliminado correctamente con ID: " + idMiembro);
                } else {
                    joptionMessage("No se encontró un miembro con ID: " + idMiembro);
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
     * Método para insertar un miembro en la base de datos.
     *
     * @param miembro El miembro a insertar.
     */
    public void insertarMiembro(Miembro miembro) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.persist(miembro);
                transaction.commit();
                joptionMessage("Miembro insertado correctamente con ID: " + miembro.getId_miembro());
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    joptionMessage("Error al insertar el miembro");
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