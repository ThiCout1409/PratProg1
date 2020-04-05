package ex_1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	// Obtémconexãocom o bancodedados
	public static Connection obtemConexao() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/vendas?user=root&password=xw15dv86");
	}
}
