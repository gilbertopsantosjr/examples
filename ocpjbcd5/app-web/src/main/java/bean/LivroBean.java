package bean;

import modelo.Autor;
import modelo.Livro;
import service.AutorService;
import service.LivroService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;

	@Inject
	private LivroService livroService;

	@Inject
	private AutorService autorService;

	public void cadastra() {
		Autor autor = this.autorService.buscaPelaId(this.autorId);
		this.livro.setAutor(autor);
		this.livroService.salva(livro);
		this.livro = new Livro();
	}

	public List<Autor> getAutores() {
		return autorService.todosAutores();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public List<Livro> getLivros() {
		return this.livroService.todosLivros();
	}
}