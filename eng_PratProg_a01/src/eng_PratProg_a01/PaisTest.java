package eng_PratProg_a01;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(Alphanumeric.class)
public class PaisTest {
	Pais pais, copia;
	static int id = 0;
	double delta = 0.01;

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Setup");
		pais = new Pais(2, "Bielorussia", 9491800, 207600.0);
		copia = new Pais(2, "Bielorussia", 9491800, 207600.0);
		System.out.println(pais);
		System.out.println(copia);
	}
	
	@Test
	void test00Carregar() {
		System.out.println("Carregar");
		Pais fixture = new Pais(1, "Brasil", 210147125, 8515767.0);
		Pais novo = new Pais(1, null, 0, 0);
		novo.carregar();
		assertEquals(novo.getId(), fixture.getId());
		assertEquals(novo.getNome(), fixture.getNome());
		assertEquals(novo.getPopulacao(), fixture.getPopulacao());
		assertEquals(novo.getArea(), fixture.getArea(), delta);
	}
	
	@Test
	void test01Criar() {
		System.out.println("Criar");
		pais.criar();
		id = pais.getId();
		System.out.println(id);
		copia.setId(id);
		assertEquals(pais.getId(), copia.getId());
		assertEquals(pais.getNome(), copia.getNome());
		assertEquals(pais.getPopulacao(), copia.getPopulacao());
		assertEquals(pais.getArea(), copia.getArea(), delta);
	}

	@Test
	void test02Atualizar() {
		System.out.println("atualizar");
		pais.setArea(555);
		copia.setArea(555);
		pais.atualizar();
		pais.carregar();
		assertEquals(pais.getId(), copia.getId());
		assertEquals(pais.getNome(), copia.getNome());
		assertEquals(pais.getPopulacao(), copia.getPopulacao());
		assertEquals(pais.getArea(), copia.getArea(), delta);
	}

	@Test
	void test03Excluir() {
		System.out.println("Excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setArea(0);
		copia.setPopulacao(0);
		pais.excluir();
		pais.carregar();
		assertEquals(pais.getId(), copia.getId());
		assertEquals(pais.getNome(), copia.getNome());
		assertEquals(pais.getPopulacao(), copia.getPopulacao());
		assertEquals(pais.getArea(), copia.getArea(), delta);
	}

	@Test
	void test05MaiorPais() {
		pais.criar();
		Pais novo = new Pais(3, "Canada", 37242571, 9984670);
		novo.criar();
		Pais maior = new Pais();
		
		maior.maiorPais();
		assertEquals(maior.getNome(), "Brasil");
		assertEquals(maior.getPopulacao(), 210147125);
	}

	@Test
	void test06TresPaises() {
		pais.reset1();
		pais.reset2();
		pais.reset3();
		pais.reset4();
		pais.reset5();
		
		Pais[] paises = new Pais[3];
		for(int i = 0; i < 3; i++) {
			paises[i] = new Pais();
		}
		paises = pais.tresPaises();
		assertEquals(paises[0].getId(), 1);
		assertEquals(paises[0].getNome(), "Brasil");
		assertEquals(paises[1].getId(), 2);
		assertEquals(paises[1].getNome(), "Bielorussia");
		assertEquals(paises[2].getId(), 3);
		assertEquals(paises[2].getNome(), "Canada");
		
	}

	@Test
	void test07MenorArea() {
		Pais menor = new Pais();
		menor.menorArea();
		assertEquals(menor.getNome(), "Bielorussia");
		assertEquals(menor.getArea(), 207600, delta);
	}

}
