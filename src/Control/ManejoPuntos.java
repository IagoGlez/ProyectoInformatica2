package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ManejoPuntos {
	public int cargarRecord(String a, String b) {

		
		Connection connection = null;
		int puntos =0;
		
		try {
			 String url = "jdbc:mysql://localhost:3306/retro"; // Cambia localhost y el puerto si es necesario
	           String username = "root";
	           String password = "baseour";
	           connection = DriverManager.getConnection(url, username, password);
	           System.out.print("Conexion correcta tienen");
	           // Realiza operaciones con la base de datos
	          
	           
	           String Query = "SELECT Puntos FROM tienen WHERE idJugador = (?) AND idJuego = (?)";

	           //Try para el SELECT de jugadores.Quiero comprobar si el nombre existe en la base de datos.
	           try (PreparedStatement statement = connection.prepareStatement(Query)) {
	               statement.setString(1, a);
	               statement.setString(2, b);
	               ResultSet r =statement.executeQuery();
	               
	               if (r.next()) {//Existen resultados en el ResultSet
	            	   while(r.next()) {
		                	puntos = r.getInt("Puntos");              
		                	}
	            	   connection.close();
	            	   return puntos;
	               } else {//No hay puntuación asociada, así que creamos una iniciada a 0
	            	   String QueryInsert = "INSERT INTO tienen (idJugador,idJuego,Puntos) VALUES (?,?,?)";
	            	   PreparedStatement statement2 = connection.prepareStatement(QueryInsert);
	            	   statement.setString(1, a);
	                   statement.setString(2, b);
	                   statement.setString(3, String.valueOf(puntos));
	                    r =statement2.executeQuery();
	                    connection.close();
	                    return puntos;
	               
	               }
	           
	           
	           }

	           
	           catch (SQLException e) {
	               e.printStackTrace();
	           }
	          
	           connection.close();
		}
		 catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return puntos;
		
	}

	public void updatePuntos(int a,String j,String juego) {
		Connection connection = null;
		try {
			 String url = "jdbc:mysql://localhost:3306/retro"; // Cambia localhost y el puerto si es necesario
	          String username = "root";
	          String password = "baseour";
	          connection = DriverManager.getConnection(url, username, password);
	          System.out.print("Conexion correcta tienen");
	          
	          String Query = "UPDATE tienen SET= (?) puntos WHERE idJugador =(?) AND idJuego = (?) ";
	          
	          try(PreparedStatement statement = connection.prepareStatement(Query)){
	        	  statement.setString(1, String.valueOf(a));
	              statement.setString(2, j);
	              statement.setString(3, juego);
	              statement.executeQuery();
	          }
	          catch(SQLException e){
	        	  
	          }
	          connection.close();
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public Vector<Pole> listaPuntos(String juego) {
		Connection connection = null;
		Vector<Pole> vp = new Vector<>();
		try {
			 String url = "jdbc:mysql://localhost:3306/retro"; 
	          String username = "root";
	          String password = "baseour";
	          connection = DriverManager.getConnection(url, username, password);
	          System.out.print("Conexion correcta tienen");
	          
	          String Query = "SELECT * FROM tienen WHERE idJuego=(?) LIMIT 10 ";
	          
	          try(PreparedStatement statement = connection.prepareStatement(Query)){
	        	  statement.setString(1, String.valueOf(juego));
	        	  ResultSet r =statement.executeQuery();
	        	  
	        	  while(r.next()) {
	        		  Pole p = new Pole();
	        		  p.nombre=r.getNString("idJugador");
	        		  p.puntos=r.getInt("puntos");
	        		  p.juego=r.getString("idJuego");//Dependiendo de como lo representemos esto igual no es necesario.
	        		  vp.add(p);
	        		  
	        	  }
	        	  return vp;
	          
	          }
	          catch(SQLException e){
	        	  
	          }
	          connection.close();
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
		return vp;
		
	}
	


}//Fin de la clase

