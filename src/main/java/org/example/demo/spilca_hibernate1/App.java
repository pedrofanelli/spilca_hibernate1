package org.example.demo.spilca_hibernate1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import entities.Product;

/**
 * Setting up HIBERNATE!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
        
    	EntityManager em = emf.createEntityManager(); // represent the CONTEXT
    	
    	em.getTransaction().begin();
    	
    	Product product = new Product();
    	product.setId(1L);
    	product.setName("beer");
    	
    	em.persist(product); // add to context (and maybe if there are no changes, it will be inserted)
    	
    	
    	
    	
    	em.getTransaction().commit();
    	em.close();
    	
    }
}
