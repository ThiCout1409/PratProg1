package eng_PratProg_a01;

import java.sql.Connection;
import java.sql.SQLException;

public class Teste {

	public static void main(String[] args) {
		
		Pais pais = new Pais();
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = pais.obtemConexao();
			
			System.out.println(pais.maiorPais(conn));
			
			pais.tresPaises(conn);
			
			pais.menorArea(conn);
		} 
		catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} 
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} 
				catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
	}

}
