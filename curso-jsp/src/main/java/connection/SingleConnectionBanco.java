package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	public static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	public static String user = "postgres";
	public static String senha = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {/*quando tiver uma instância vai conectar*/
		conectar();
	}
		
	private static void conectar() {
		
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver"); /*Carrega o driver de conexão do banco*/
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); /*para não efetuar alterações no banco sem nosso comando*/
			}
		} catch (Exception e) {
			e.printStackTrace();/*Mostrar qualquer erro no momento de conectar*/
		}
		
	}

}
