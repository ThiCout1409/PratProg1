package ex_1;

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
}
