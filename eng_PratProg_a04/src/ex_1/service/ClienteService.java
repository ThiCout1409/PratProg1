package ex_1.service;

import java.util.ArrayList;

import ex_1.dao.ClienteDAO;
import ex_1.model.Cliente;

public class ClienteService {

	public void criar(Cliente to) {
		ClienteDAO.incluir(to);
	}
	public void atualizar(Cliente to) {
		ClienteDAO.atualizar(to);
	}
	public void excluir(Cliente to) {
		ClienteDAO.excluir(to);
	}
	public Cliente carregar(int id) {
		Cliente to = ClienteDAO.carregar(id);
		return to;
	}
	public ArrayList<Cliente> listarTodos() {
		return ClienteDAO.listarTodos();
		
	}
}
