package ex_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {
	Pais cliente, copia;
	static int id = 2;
	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
	 * linha com o id igual ao do escolhido para o to instanciado abaixo. Se
	 * houver, delete.
	 * Certifique-se também que sobrecarregou o equals na classe
	 * Cliente.
	 * Certifique-se que a fixture cliente1 foi criada no banco.
	 * Além disso, a ordem de execução dos testes é importante; por
	 * isso a anotação FixMethodOrder logo acima do nome desta classe
	 */
	
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		cliente = new Pais(id, "Batista Cepelos", "(11) 91234-4321", "btcp@usjt.br");
		copia = new Pais(id, "Batista Cepelos", "(11) 91234-4321","btcp@usjt.br" );
		System.out.println(cliente);
		System.out.println(copia);
		System.out.println(id);
	}
	@Test
	public void test1Carregar() {
		System.out.println("carregar");
		//para funcionar o cliente 1 deve ter sido carregado no banco por fora
		Pais fixture = new Pais(1, "Carlos Drummond de Andrade", "(11) 91234-4321", "cda@usjt.br" );
		Pais novo = new Pais(1, null, null, null);
		PaisService service = new PaisService();
		novo = service.carregar(novo.getId());
		assertEquals(novo, fixture);
	}
	
	@Test
	public void test2Criar() {
		System.out.println("criar");
		PaisService service = new PaisService();
		service.criar(cliente);
		id = cliente.getId();
		System.out.println(id);
		copia.setId(id);
		assertEquals(copia, cliente);
	}
	@Test
	public void test3Atualizar() {
		System.out.println("atualizar");
		cliente.setFone("999999");
		copia.setFone("999999");
		PaisService service = new PaisService();
		service.atualizar(cliente);
		cliente = service.carregar(cliente.getId());
		assertEquals(cliente, copia);
	}
	@Test
	public void test4Excluir() {
		System.out.println("excluir");
		Pais vazio = new Pais();
		PaisService service = new PaisService();
		service.excluir(cliente);
		cliente = service.carregar(cliente.getId());
		assertEquals(cliente, vazio);
	}
}