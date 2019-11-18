package com.jpaAlgaworks;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.jpaAlgaworks.dto.UsuarioDTO;
import com.jpaAlgaworks.model.Dominio;
import com.jpaAlgaworks.model.Usuario;

public class ConsultasComCriteria {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("clientes-PU");
		EntityManager em = emf.createEntityManager();
		
		//primeiraConsulta(em);
		//escolhendoRetorno(em);
		//fazendoProjecoes(em);
		//passandoParametros(em);
		//ordenandoResultados(em);
		paginandoResultados(em);

		emf.close();
		em.close();

	}
	
	public static void primeiraConsulta(EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(root);
		
		TypedQuery<Usuario> typedQuery = em.createQuery(criteriaQuery);
		List<Usuario> resultList = typedQuery.getResultList();
		resultList.forEach(u -> System.out.println(u.getId() + ", " + u.getNome()));
	}
	
	public static void escolhendoRetorno(EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		//--------- SELECT u.dominio FROM Usuario ;
//		CriteriaQuery<Dominio> criteriaQuery = criteriaBuilder.createQuery(Dominio.class);
//		Root<Usuario> root = criteriaQuery.from(Usuario.class);  //ROOT seria o equivalente ao alias do JPQL
		
//		criteriaQuery.select(root.get("dominio"));  //multiselect  várias colunas
//		
//		TypedQuery<Dominio> typedQuery = em.createQuery(criteriaQuery);
//		List<Dominio> resultList = typedQuery.getResultList();
//		resultList.forEach(d -> System.out.println(d.getId() + ", " + d.getNome()));
		
		//--------- SELECT u.nome FROM Usuario ;
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		
		criteriaQuery.select(root.get("nome"));  //multiselect  várias colunas
		
		TypedQuery<String> typedQuery = em.createQuery(criteriaQuery);
		List<String> resultList = typedQuery.getResultList();
		resultList.forEach(nome -> System.out.println("Nome: " + nome));
	}
	
	public static void fazendoProjecoes(EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		//------- SELECT id, login, nome FROM Usuario;
//		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
//		Root<Usuario> root = criteriaQuery.from(Usuario.class);
//		
//		criteriaQuery.multiselect(root.get("id"), root.get("login"), root.get("nome"));  //multiselect  várias colunas
//		
//		TypedQuery<Object[]> typedQuery = em.createQuery(criteriaQuery);
//		List<Object[]> resultList = typedQuery.getResultList();
//		resultList.forEach(arr -> System.out.println(String.format("%s, %s, %s", arr)));
		
		//------- SELECT NEW com.jpaAlgaworks.dto.UsuarioDTO(id, login, nome) FROM Usuario;
		CriteriaQuery<UsuarioDTO> criteriaQuery = criteriaBuilder.createQuery(UsuarioDTO.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		
		criteriaQuery.select(criteriaBuilder.construct(UsuarioDTO.class, 
				root.get("id"), root.get("login"), root.get("nome")));  
		
		TypedQuery<UsuarioDTO> typedQuery = em.createQuery(criteriaQuery);
		List<UsuarioDTO> resultList = typedQuery.getResultList();
		resultList.forEach(u -> System.out.println("DTO :" + u.getId() + ", " + u.getNome()));
	}
	
	public static void passandoParametros(EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		// ----------- SELECT u FROM Usuario u WHERE u.id =: idUsuario;
//		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
//		Root<Usuario> root = criteriaQuery.from(Usuario.class);
//		
//		criteriaQuery.select(root);
//		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));
//		
//		TypedQuery<Usuario> typedQuery = em.createQuery(criteriaQuery);
//		Usuario usuario =  typedQuery.getSingleResult();
//		System.out.println(usuario.getId() + ", " + usuario.getNome());
		
		// ----------- SELECT u FROM Usuario u WHERE u.login =: loginUsuario;
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("login"), "ria"));
		
		TypedQuery<Usuario> typedQuery = em.createQuery(criteriaQuery);
		Usuario usuario =  typedQuery.getSingleResult();
		System.out.println(usuario.getId() + ", " + usuario.getNome());
	}
	
	private static void ordenandoResultados(EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		//--------- SELECT u FROM Usuario u ORDER BY u.nome ;
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);  
		
		criteriaQuery.select(root);  
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));
		
		TypedQuery<Usuario> typedQuery = em.createQuery(criteriaQuery);
		List<Usuario> resultList = typedQuery.getResultList();
		resultList.forEach(d -> System.out.println(d.getId() + ", " + d.getNome()));
	}
	
	private static void paginandoResultados(EntityManager em) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);  
		
		criteriaQuery.select(root);  
		
		TypedQuery<Usuario> typedQuery = em.createQuery(criteriaQuery)
				.setFirstResult(0)
				.setMaxResults(2);
		List<Usuario> resultList = typedQuery.getResultList();
		resultList.forEach(d -> System.out.println(d.getId() + ", " + d.getNome()));
	}
	
	
	
	
	
	

}
