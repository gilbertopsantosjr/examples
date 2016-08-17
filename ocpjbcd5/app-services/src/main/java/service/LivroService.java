package service;

import dao.LivroDao;
import modelo.Livro;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class LivroService {

	@Inject
	private LivroDao livroDao;

	public void salva(Livro livro) {
		this.livroDao.salva(livro);
	}

	public List<Livro> todosLivros() {
		return this.livroDao.todosLivros();
	}

	public List<Livro> livrosPeloTitulo(String titulo) {
		return this.livroDao.livrosPeloTitulo(titulo);
	}
}
