package eng_PratProg_a01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pais {

	private int id;
	private String nome;
	private long populacao;
	private double area;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Pais() {
		super();
	}

	public Pais(int id, String nome, long populacao, double area) {
		super();
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}	

	public Pais(String nome, long populacao, double area) {
		super();
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}

	public Connection obtemConexao() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=xw15dv86");
	}


	public void criar() {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar() {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.setInt(4, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir() {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void carregar() {
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id =?";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setArea(rs.getDouble("area"));
				} else {
					setId(-1);
					setNome(null);
					setPopulacao(0);
					setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}

	public void maiorPais() {

		String sqlSelect = "SELECT nome, populacao FROM pais ORDER BY populacao desc LIMIT 1";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);){
			try(ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
				}
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Pais: " + getNome() + "\nPopulação: " + getPopulacao());
	}

	public Pais[] tresPaises(){
		Pais[] paises = new Pais[3];
		for(int i = 0; i < 3; i++) {
			paises[i] = new Pais();
		}

		String sqlSelect = "SELECT id, nome FROM pais";

		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try(ResultSet rs = stm.executeQuery();) {
				rs.next();
				for(int i = 0; i < 3 ; i++, rs.next()) {
					paises[i].setId(rs.getInt("id"));
					paises[i].setNome(rs.getString("nome"));
					System.out.println("Id: " + paises[i].getId() + " Pais: "  + paises[i].getNome());
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return paises;
	}

	public void menorArea() {

		String sqlSelect = "SELECT nome, area FROM pais ORDER BY area LIMIT 1";
		try(Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try(ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setArea(rs.getDouble("area"));
				}
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		System.out.println("Pais: " + getNome() + "\nArea: " + getArea());
	}

	public void reset1() {

		String sqlQuery = "DELETE FROM pais WHERE id < 10";
		try(Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery);){
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void reset2() {

		String sqlQuery = "alter table pais AUTO_INCREMENT = 1";
		try(Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery);){
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void reset3() {

		String sqlQuery = "INSERT INTO `Pais` (`nome`,`populacao`,`area`) VALUES ('Brasil',210147125,8515767);";
		try(Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery);){
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void reset4() {

		String sqlQuery = "INSERT INTO `Pais` (`nome`,`populacao`,`area`) VALUES ('Bielorussia',9491800,207600);";
		try(Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery);){
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void reset5() {

		String sqlQuery = "INSERT INTO `Pais` (`nome`,`populacao`,`area`) VALUES ('Canada',37242571,9984670);";
		try(Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery);){
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nome=" + nome + ", populacao=" + populacao + ", area=" + area + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (area == 0.0) {
			if (other.area != 0.0)
				return false;
		} else if (area != other.area)
			return false;
		if (populacao != 0) {
			if (other.populacao != 0)
				return false;
		} else if (populacao != other.populacao)
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
