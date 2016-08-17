package service;

import dao.AutorDao;
import modelo.Autor;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AutorService {

	@Inject
	private AutorDao dao;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
 	public void salva(Autor autor){
		this.dao.salva(autor);
//		throw new LivrariaException();
 	}
 	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Autor> todosAutores() {
		return this.dao.todosAutores();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Autor buscaPelaId(final Integer autorId) {
		return this.dao.buscaPelaId(autorId);
	}
}