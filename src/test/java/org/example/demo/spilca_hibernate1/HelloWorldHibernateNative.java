package org.example.demo.spilca_hibernate1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;

import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import entities.Product;

/**
 * You can go from native to JPA implementation
 * You can unwrap a SessionFactory from an EntityManagerFactory
 * But you can also do it viceversa, use a Hibernate Configuration to get an EntityManagerFactory
 * 
 * @author peter
 *
 */
public class HelloWorldHibernateNative {

	private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Product.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void storeLoadMessage() {

        try (SessionFactory sessionFactory = createSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Product product = new Product();
            product.setName("Hello World from Native Hibernate!");

            session.persist(product);

            session.getTransaction().commit();
            // INSERT into PRODUCT (ID, TEXT)
            // values (1, 'Hello World from Hibernate!')
            session.beginTransaction();

            //we build a query programmatically
            CriteriaQuery<Product> criteriaQuery = session.getCriteriaBuilder().createQuery(Product.class);
            criteriaQuery.from(Product.class);

            List<Product> products = session.createQuery(criteriaQuery).getResultList();
            // SELECT * from PRODUCT

            session.getTransaction().commit();

            assertAll(
                    () -> assertEquals(9, products.size()),
                    () -> assertEquals("Hello World from Native Hibernate!", products.get(products.size()-1).getName())
            );
        }
    }
}
