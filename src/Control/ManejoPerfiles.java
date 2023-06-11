package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class ManejoPerfiles {

	public void guardar(String nombre) {
		Connection connection = null;
		try {
			 String url = "jdbc:mysql://localhost:3306/retro";
	            String username = "root";
	            String password = "baseour";
	            connection = DriverManager.getConnection(url, username, password);
	            System.out.print("Conexion correcta guardarJugador");
	            // Realiza operaciones con la base de datos
	           
	            
	            String insertQuery = "INSERT INTO Jugadores (idjugador) VALUES (?)";

	            //Try para el INSERT jugadores.
	            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
	                statement.setString(1, nombre);
	                statement.executeUpdate();
	                System.out.println("Nombre insertado correctamente.");
	            }
	 
	            catch(SQLIntegrityConstraintViolationException a) {
	            	System.out.println("Nombre repetido Guardar Jugador");
	            }
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	            
	            connection.close();
		}
		 catch (SQLException e) {
	         e.printStackTrace();
	     }
		
	}
	
	public boolean cargar(String nombre) {
		Connection connection = null;
		boolean t = false;
		try {
			 String url = "jdbc:mysql://localhost:3306/retro"; // Cambia localhost y el puerto si es necesario
	            String username = "root";
	            String password = "baseour";
	            connection = DriverManager.getConnection(url, username, password);
	            System.out.print("Conexion correcta guardarJugador");
	            // Realiza operaciones con la base de datos
	           
	            
	            String insertQuery = "SELECT idJugador FROM jugadores WHERE idJugador = (?)";

	            //Try para el SELECT de jugadores.Quiero comprobar si el nombre existe en la base de datos.
	            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
	                statement.setString(1, nombre);
	                ResultSet r =statement.executeQuery();
	                String resultado = "";
	                while(r.next()) {
	                	resultado=r.getString("idJugador");              
	                	}
	                
	                System.out.println("Nombre encontrado."+ nombre);
	                if(resultado.equals(nombre)) {
	                	t=true;
	                	Sistema.setNombre(nombre);
	                	connection.close();
	                	return t;
	                }
	                else {
	                	connection.close();
	                	return t;
	                }
	            }
	 
	            
	            catch (SQLException e) {
	                e.printStackTrace();
	            }
	            connection.close();
	            return t;
		
		}
		 catch (SQLException e) {
	         e.printStackTrace();
	     }
		return t;
		
	}//Fin cargar



}//Fin clase
