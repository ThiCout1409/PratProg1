package ex_1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ex_1.model.Cliente;

public class ClienteDAO {

	public static void incluir(Cliente to) {
		String sqlInsert = "INSERT INTO cliente(id, nome, fone, email) VALUES (?, ?, ?,?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, to.getId());
			stm.setString(2, to.getNome());
			stm.setString(3, to.getFone());
			stm.setString(4, to.getEmail());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					to.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void atualizar(Cliente to) {
		String sqlUpdate = "UPDATE cliente SET nome=?, fone=?, email=? WHERE id=?";
		// usando o try with resources do Java 7, quefecha o queabriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, to.getNome());
			stm.setString(2, to.getFone());
			stm.setString(3, to.getEmail());
			stm.setInt(4, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void excluir(Cliente to) {
		String sqlDelete = "DELETE FROM cliente WHERE id = ?";
		// usando o try with resources do Java 7, quefecha o queabriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Cliente carregar(int id) {
		Cliente to = new Cliente();
		String sqlSelect = "SELECT id, nome, fone, email FROM cliente WHERE cliente.id = ?";
		// usando o try with resources do Java 7, quefecha o queabriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setId(rs.getInt("id"));
					to.setNome(rs.getString("nome"));
					to.setFone(rs.getString("fone"));
					to.setEmail(rs.getString("email"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return to;
	}
	
	public static ArrayList<Cliente> listarTodos() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, fone, email FROM cliente";
		Cliente cliente;

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					cliente = new Cliente();
					cliente.setId(rs.getInt("id"));
					cliente.setNome(rs.getString("nome"));
					cliente.setFone(rs.getString("fone"));
					cliente.setEmail(rs.getString("email"));
					clientes.add(cliente);
				} 
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return clientes;
	}

}
