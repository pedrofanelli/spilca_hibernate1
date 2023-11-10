package org.example.demo.spilca_hibernate1;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.Test;

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
    public void shouldAnswerWithTrue()
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
	    	
	    	products.get(products.size()-1).setName("Hello Hibernate!!!");
	    	
	    	em.getTransaction().commit();
	    		    	
	    	em.close();
	    	
    	} finally {
    		emf.close();
    	}
    }
}
