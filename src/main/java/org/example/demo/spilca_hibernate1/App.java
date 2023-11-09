package org.example.demo.spilca_hibernate1;

import jakarta.persistence.EntityManager;

/**
 * Setting up HIBERNATE!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    	EntityManager em = null;
    	
    	em.getTransaction().begin();
    	
    	
    	
    	
    	
    	em.getTransaction().commit();
    	
    }
}
