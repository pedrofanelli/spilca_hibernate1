package org.example.demo.spilca_hibernate1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
    	
    	
    	
    	
    	
    	em.getTransaction().commit();
    	em.close();
    	
    }
}
