package ex_2;

public class Teste {

	public static void main(String[] args) {



		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		PaisService service = new PaisService();
		Pais to = new Pais();
		Pais[] toes = new Pais[3];
		service.maiorPais(to);

		service.tresPaises(toes);

		service.menorArea(to);

	}

}
