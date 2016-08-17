package service;

import dao.UsuarioDao;
import modelo.Usuario;

import javax.ejb.*;
import javax.inject.Inject;

@Stateless
public class UsuarioService {

	@Inject
	private UsuarioDao dao;

	public Usuario buscaPeloLogin(String login) {
		return this.dao.buscaPeloLogin(login);
	}
}
