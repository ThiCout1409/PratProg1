package ex_1;

public class PaisService {

	public void criar(Pais to) {
		ClienteDAO.incluir(to);
	}
	public void atualizar(Pais to) {
		ClienteDAO.atualizar(to);
	}
	public void excluir(Pais to) {
		ClienteDAO.excluir(to);
	}
	public Pais carregar(int id) {
		Pais to = ClienteDAO.carregar(id);
		return to;
	}
}
