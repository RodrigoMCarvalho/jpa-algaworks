package com.jpaAlgaworks;

import static org.hibernate.criterion.Restrictions.gt;
import static org.hibernate.criterion.Restrictions.like;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jpaAlgaworks.model.Produto;

public class ConsultasComCriteria2 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clientes-PU");
		EntityManager em = emf.createEntityManager();

		//consultaProdutos(em);
		//consultaRestricaoEQ(em);
		//consultaRestricaoNE(em);
		//consultaRestricaoLIKE(em);
		//consultaRestricaoGT(em);
		//consultaRestricaoLT(em);
		//consultaRestricaoConjunction(em);
		//consultaRestricaoDisjunction(em);
		//paginandoResultados(em);
		//consultaProdutosOrdenados(em);
		//projecoes(em);
		projecoesAgregadas(em);
		
		emf.close();
		em.close();
	}

	public static void consultaProdutos(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		List<?> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	public static void consultaRestricaoEQ(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.eq("nome","Monitor"));    //retornar objeto que possue um valor de propriedade que é igual à nossa restrição
		List<Produto> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	public static void consultaRestricaoNE(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.ne("nome","Monitor"));    //retornar objeto que não possue um valor de propriedade que é igual à nossa restrição
		List<Produto> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	public static void consultaRestricaoLIKE(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.like("nome", "Tec%"));    //retornar objeto que começem com "Tec" e termine com qualquer letra
		List<Produto> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	public static void consultaRestricaoGT(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.gt("preco", new Double(110.0)));      //verifica se é maior que
		List<Produto> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	public static void consultaRestricaoLT(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.lt("preco", new Double(110.0)));      //verifica se é menor que
		List<Produto> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	public static void consultaRestricaoConjunction(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.conjunction()                         //Equivale a expressão AND
					.add(like("nome", "Tec%"))							//import static org.hibernate.criterion.Restrictions.*;
					.add(gt("preco", new Double(200.00))));

		List<Produto> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	public static void consultaRestricaoDisjunction(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.disjunction()                         //Equivale a expressão OR
					.add(like("nome", "Tec%"))							//import static org.hibernate.criterion.Restrictions.*;
					.add(gt("preco", new Double(200.00))));

		List<Produto> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	public static void paginandoResultados(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.setFirstResult(1);
		criteria.setMaxResults(2);
		List<Produto> lista = criteria.list();
		lista.forEach(System.out::println);
	}
	
	
	public static void consultaProdutosOrdenados(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.addOrder(Order.asc("preco"));
		List<?> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	public static void projecoes(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.setProjection(Projections.rowCount());

		List<?> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	public static void projecoesAgregadas(EntityManager em) {
		Session session = (Session) em.getDelegate();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Produto.class);
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("nome"));
		projList.add(Projections.property("preco"));
		criteria.setProjection(projList);

		List<?> results = criteria.list();
		results.forEach(System.out::println);
	}
	
	
	
	
	
	
}