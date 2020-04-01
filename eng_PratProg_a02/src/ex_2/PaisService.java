package ex_2;

public class PaisService {

	public void criar(Pais to) {
		PaisDAO.criar(to);
	}
	public void atualizar(Pais to) {
		PaisDAO.atualizar(to);
	}
	public void excluir(Pais to) {
		PaisDAO.excluir(to);
	}
	public Pais carregar(int id) {
		Pais to = PaisDAO.carregar(id);
		return to;
	}
	public Pais maiorPais(Pais to) {
		return PaisDAO.maiorPais(to);
	}
	public Pais[] tresPaises(Pais[] toes) {
		PaisDAO.reset1();
		return PaisDAO.tresPaises(toes);
	}
	public Pais menorArea(Pais to) {
		return PaisDAO.menorArea(to);		
	}

}
