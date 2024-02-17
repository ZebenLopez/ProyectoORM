package com.mycompany.proyecto_orm_zebenzuilopezconde.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * @autor Zebenzui López Conde
 * @version 1.0
 *
 * Curso: 2ºA DAM
 * Clase utilitaria para la creación de la SessionFactory de Hibernate.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    /**
     * Método para construir la SessionFactory.
     *
     * @return La SessionFactory construida.
     */
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error al inicializar SessionFactory: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    /**
     * Método para obtener la SessionFactory.
     *
     * @return La SessionFactory.
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}