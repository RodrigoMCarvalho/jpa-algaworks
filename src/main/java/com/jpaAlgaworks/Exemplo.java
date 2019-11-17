package com.jpaAlgaworks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpaAlgaworks.model.Cliente;

public class Exemplo {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clientes-PU");
		EntityManager em = emf.createEntityManager();
		
		Cliente cliente = em.find(Cliente.class, 1);
		System.out.println(cliente.getNome());
		
		
		Cliente cli2 = new Cliente();
		cli2.setNome("Gustavo");
		
		em.getTransaction().begin();
		em.persist(cli2);
		em.getTransaction().commit();

		emf.close();
		em.close();
	}
}
