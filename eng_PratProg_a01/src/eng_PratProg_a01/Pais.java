package eng_PratProg_a01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pais {

	private int id;
	private String nome;
	private long populacao;
	private double area;

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

	public Connection obtemConexao() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=xw15dv86");
	}


	public void criar() {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, getId());
			stm.setString(2, getNome());
			stm.setLong(3, getPopulacao());
			stm.setDouble(4, getArea());
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
			stm.setString(2, getNome());
			stm.setLong(3, getPopulacao());
			stm.setDouble(4, getArea());
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
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.idPais =?";
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
					setArea(0.0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}

	public String maiorPais(Connection conn) {

		String sqlSelect = "SELECT nome, populacao FROM pais ORDER BY populacao desc LIMIT 1";
		try (PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
				}
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return "Pais: " + getNome() + "\nPopulação: " + getPopulacao();
	}
	
	public ArrayList<Pais> tresPaises(Connection conn){
		ArrayList<Pais> paises = new ArrayList<Pais>();
		
		String sqlSelect = "SELECT idPais, nome FROM pais";
		try(PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
			rs.next();
			for(int i = 0; i < 3 ; i++, rs.next()) {
				setId(rs.getInt("idPais"));
				setNome(rs.getString("nome"));
				System.out.println("Id: " + getId() + " Pais: "  + getNome());
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return paises;
	}
	
	public void menorArea(Connection conn) {
		
		String sqlSelect = "SELECT nome, area FROM pais ORDER BY area LIMIT 1";
		try (PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setArea(rs.getDouble("area"));
				}
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		System.out.println("Pais: " + getNome() + "\nArea: " + getArea());
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

}
