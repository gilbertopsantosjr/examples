package dao;

import modelo.Livro;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class LivroDao {

	@PersistenceContext(unitName="livraria")
	private EntityManager entityManager;

	public void salva(Livro livro) {
		this.entityManager.persist(livro);
	}

	public List<Livro> todosLivros() {
		return this.entityManager.createQuery("select l from Livro l",
				Livro.class).getResultList();
	}

	public List<Livro> livrosPeloTitulo(String titulo) {
		TypedQuery<Livro> query = this.entityManager.createQuery(
				"select l from Livro l where l.titulo like upper(:pTitulo)",
				Livro.class);
		query.setParameter("pTitulo", "%" + titulo.toUpperCase() + "%");
		return query.getResultList();
	}
}
