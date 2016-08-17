package dao;

import modelo.Usuario;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Usuario buscaPeloLogin(String login) {
		Usuario usuario = null;
		try {
			TypedQuery<Usuario> query = entityManager.createQuery(
					"select u from Usuario u where u.login=:pLogin", Usuario.class);
			query.setParameter("pLogin", login);
			usuario = (Usuario) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
