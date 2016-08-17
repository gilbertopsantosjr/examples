package dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import modelo.Autor;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
//@Interceptors({ LogInterceptador.class }) a configuração do interceptador foi substituida de anotação para xml
public class AutorDao {
 	
	@PersistenceContext(unitName="livraria")
	private EntityManager entityManager;
	 	
	@PostConstruct
	void postConstruct(){
		System.out.println("--AutorDao criado");
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void salva(Autor autor) {
		this.entityManager.persist(autor);
	}
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Autor> todosAutores() {
		return this.entityManager.createQuery("select a from Autor a", Autor.class).getResultList();
 	}
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.entityManager.find(Autor.class, autorId);
		return autor;
	}
	
}