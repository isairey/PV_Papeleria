package PuntoVentas.model;
import java.sql.*;
import java.sql.Connection;

public class ConnectorMySQL {
	public static Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:puntoventas.db");
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
