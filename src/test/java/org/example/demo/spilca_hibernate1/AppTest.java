package org.example.demo.spilca_hibernate1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.HashMap;
import java.util.List;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.jupiter.api.Test;

import entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import persistence.CustomPersistenceUnitInfo;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    @Test
    public void storeMessage()
    {
    	EntityManagerFactory emf = new HibernatePersistenceProvider()
    			.createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());
        
    	try {
    		    	
	    	EntityManager em = emf.createEntityManager(); // represent the CONTEXT
	    	
	    	em.getTransaction().begin();
	    	
	    	Product product = new Product();
	    	product.setName("pasta");
	    	
	    	em.persist(product); // add to context (and maybe if there are no changes, it will be inserted)
	    	
	    	em.getTransaction().commit(); // send the query
	    	
	    	em.getTransaction().begin();
	    	
	    	List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();
	    	
	    	products.get(products.size()-1).setName("USING TEST CLASS");
	    	
	    	em.getTransaction().commit();
	    	
	    	assertAll(() -> assertEquals(4, products.size()),
	    	        () -> assertEquals("USING TEST CLASS",
	    	        		products.get(products.size()-1).getName()));	    
	    	
	    	em.close();
	    	
    	} finally {
    		emf.close();
    	}
    }
}
