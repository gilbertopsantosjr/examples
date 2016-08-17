package webservice;

import modelo.Livro;
import service.LivroService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
@Stateless
public class LivrariaWS {

	@Inject
	private LivroService livroService;
	
 	@WebResult(name="livro")
	public List<Livro> getLivroPorTitulo(@WebParam(name="titulo") String titulo) {

		System.out.println("Procurando pelo título:: " + titulo);

		return this.livroService.livrosPeloTitulo(titulo);
	}
}