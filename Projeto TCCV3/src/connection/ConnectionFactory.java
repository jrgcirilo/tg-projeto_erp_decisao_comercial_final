package connection;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory  {
	
	public static Connection getConnection()
	  {
		String strConexao = "jdbc:mysql://localhost/dbtcc?useTimezone=true&serverTimezone=America/Sao_Paulo";
  	String user = "root";
  	String senha = "1234";
	    try {
	    	return DriverManager.getConnection(
					strConexao,
					user,
					senha);
			
	    	
			
	    	
	    } catch ( SQLException e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Banco aberto com sucesso");
		return null;
	    
	    
	  }
}