package Control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

public  class Sistema {

	private static Vector <Perfil> perfiles = new Vector <Perfil>();
	
	private static Perfil jug = new Perfil("Default");

	public static Perfil getJug() {
		return jug;
	}

	public static void  cargarP() {
		
		int [] num = new int[6];
				
		int cont =0;
		try {
			
			Scanner lectura = new Scanner(new File("p.txt"));
			lectura.useDelimiter(";");
			while (lectura.hasNext()) {
				if(cont>=1) {
					num[cont-1]=lectura.nextInt();
				}
				else {
					jug.nombre=lectura.next();
				}
				
			cont++;
			
			if(cont>=7) {
				cont=0;
				
				Perfil jug2 = new Perfil(jug.nombre);
				int aux = 0;
				int aux2 =0;
				for(int i=0; i<6; i++) {
					if(aux==2) {
						aux=0;
					}
					
					
					if(aux==0) {
						if(aux2<=2) {
							jug2.partidas[0][aux2]=num[i];System.out.println("CARGA "+jug2.partidas[0][aux2]+"+++"+num[i] );
							
							jug2.partidas[1][aux2]=num[i+1];System.out.println("CARGA "+jug2.partidas[1][aux2]+"+++"+num[i+1] );
							aux2++;
						}
						
					}
						aux++;
				}//Fin for
				//jug2.partidas=jug.partidas;
				perfiles.add(jug2);
			}
			}//Fin while
			System.out.println(perfiles.size());
				
				System.out.println(perfiles.firstElement().nombre+"vector");
				System.out.println(perfiles.firstElement().partidas[0][1]+"vector");
				System.out.println(perfiles.firstElement().partidas[1][1]+"vector");
				System.out.println(perfiles.elementAt(1).nombre+"vector1");
				System.out.println(perfiles.elementAt(1).partidas[0][1]+"vector1");
				System.out.println(perfiles.elementAt(1).partidas[1][1]+"vector1");
				System.out.println(perfiles.elementAt(2).nombre+"vector2");
				System.out.println(perfiles.elementAt(2).partidas[0][0]+"vector1");
				System.out.println(perfiles.elementAt(2).partidas[1][0]+"vector1");
				
				/*ComparadorP comp = new  ComparadorP();
				comp.p=2;
				Collections.sort(perfiles,comp);
				System.out.println(perfiles.firstElement().nombre+"comparacion");*/
				/*System.out.println(perfiles.firstElement().nombre+"vector ordenado");
				System.out.println(perfiles.elementAt(1).nombre+"vector1 ordenado");
				System.out.println(perfiles.elementAt(2).nombre+"vector2 ordenado");*/
				
			
		
	}//Fin Try
	catch (FileNotFoundException e) {
		System.out.println("Archivo no encontrado");
		
	}
		
		jug.nombre=null;
	}//Fin cargar P.
	
	public static boolean buscarP(String n ) {//Busca un perfil en el vector.
		Boolean encontrado = false;
		System.out.println( n+" NOMBRE A BUSCAR BUSCAR.");
		
		
		for (int j = 0; j < perfiles.size(); j++) {
		    System.out.println(perfiles.elementAt(j).nombre + " CICLO BUSCAR." + n+j);
		    if (perfiles.elementAt(j).nombre.equals(n)) {
		        encontrado = true;
		        jug = perfiles.elementAt(j);
		        System.out.println("Perfil Encontrado." + jug.partidas[0][1] + " ... " + perfiles.elementAt(j).partidas[1][1]);
		        break;
		    }
		}
		
	    
		if(!encontrado) {
			System.out.println("Perfil NO Encontrado.");
		}
	    
		
		
		return encontrado;
		
	}
	
	public static boolean nombreRepetido(String s) {//True si el nombre ya existe.Interactua con devolverPerfil().
		Boolean repetido = false;
		
		
		for (int i =0; i<perfiles.size(); i++) {
			
			if(i== perfiles.size()-1) {
				repetido = false;
			}
			
			if(perfiles.elementAt(i).nombre.equals(s)) {
				repetido= true;
				i=perfiles.size()+1;
				System.out.println("Nombre Inválido.");
				
			}
			
		}//Fin for.
		if(!repetido) {
			System.out.println("Nombre Aceptado.");
			Perfil p = new Perfil(s);
			perfiles.add(p);
			
		}
		
		return repetido;
	}
	
	
	public static String ordenar(int i) {
		ComparadorP comp = new ComparadorP();
		
		comp.p=i;
		
		Collections.sort(perfiles,comp);
		String tabla = "";
		
		for(int j =0; j<perfiles.size();j++) {
			tabla+=perfiles.elementAt(j).nombre;
			tabla+= " Victorias/puntos: ";
			tabla+= perfiles.elementAt(j).partidas[0][i];
			tabla+= " Partidas: ";
			tabla+= perfiles.elementAt(j).partidas[1][i];
			tabla+= " \n";
		}
		
	return tabla;
	}
	


	public static boolean checkJug() {//Miramos si hay algún perfil cargado./*Esta función es importante pero sinceramente
		boolean t= true;
		
		//no está muy bien implementada.
		/*
		 * OJO, LÓGICA INVERTIDA PARA FUNCIONAR CON nombreRepetido();
		 */
		if(jug.nombre==null) {
			t=true;//VERDADERI y el juego NO se inicia.
		}
		else {
			t=false;//FALSO y el juego se inicia.
		}
		
		return t;
	}
	
	public static int[] cargarResultados(int [] n, int p) {//Carga los resultados.
		n[0]= jug.partidas[0][p];//Victorias.
		n[1]= jug.partidas[1][p];//Partidas jugadas.
		
		
		return n;
		
	}
	
	public static void guardarResultados(int [] n, int p) {//Guardar los resultados.
		
		if(p>1) {//Para guardar el record de Pong, en vez de sumar victorias.
			System.out.println("Resultados: "+ "Victorias"+jug.partidas[0][p]+"Derrotas"+jug.partidas[1][p]);
			if(n[0]>jug.partidas[0][p]) {
				jug.partidas[0][p]= n[0];
			}
			
			jug.partidas[1][p]= jug.partidas[1][p]+n[1];
			System.out.println("Resultados Guardados: "+ "Record"+jug.partidas[0][p]+"Partidas"+jug.partidas[1][p]);	
		
		
		}
		else {
			
			System.out.println("Resultados: "+ "Victorias"+jug.partidas[0][p]+"Derrotas"+jug.partidas[1][p]);
			jug.partidas[0][p]= jug.partidas[0][p]+n[0];
			jug.partidas[1][p]= jug.partidas[1][p]+n[1];
			System.out.println("Resultados Guardados: "+ "Victorias"+jug.partidas[0][p]+"Derrotas"+jug.partidas[1][p]);
			
		}
		
		
	}

	public static void guardarVectores() {//GUARDAR EL VECTOR EN .txt AL FINALIZAR EL PROGRAMA.
	String fileName = "Prueba.txt";

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
        for(int i=0;i<perfiles.size();i++) {
        	bw.write(perfiles.elementAt(i).nombre);
        	bw.write(";");
        	bw.write(String.valueOf(perfiles.elementAt(i).partidas[0][0]));
        	bw.write(";");
        	bw.write(String.valueOf(perfiles.elementAt(i).partidas[1][0]));
        	bw.write(";");
        	bw.write(String.valueOf(perfiles.elementAt(i).partidas[0][1]));
        	bw.write(";");
        	bw.write(String.valueOf(perfiles.elementAt(i).partidas[1][1]));
        	bw.write(";");
        	bw.write(String.valueOf(perfiles.elementAt(i).partidas[0][2]));
        	bw.write(";");
        	bw.write(String.valueOf(perfiles.elementAt(i).partidas[1][2]));
        	bw.write(";");
        	
        }
        	
    	/*for (int i : perfiles) {
            bw.write(String.valueOf(i));
            bw.newLine();
        }*/
        System.out.println("Vector guardado en " + fileName);
    } catch (IOException e) {
        System.out.println("Error al escribir en el archivo " + fileName);
        e.printStackTrace();
    }
}
	
	
	public static void devolverPerfil() {//Devolver perfil al vextor para guardar otro.TIENE QUE SOBREESCRIBIR EL YA EXISTENTE.
		boolean t=true;
		t=nombreRepetido(jug.nombre);
		if(t) {//Si existe, lo buscamos y remplazamos.
			for (int i =0; i<perfiles.size(); i++) {
				if(perfiles.elementAt(i).nombre.equals(jug.nombre)) {
					perfiles.remove(i);
					Perfil p = new Perfil(jug.nombre);
					p.partidas=jug.partidas;
					perfiles.add(p);
				}
				
			}
		}//FIn if
		else {
			Perfil p = new Perfil(jug.nombre);
			p.partidas=jug.partidas;
			perfiles.add(p);
		}
	}
	
	
	private static String nombre = null;

	private static int puntos = 0;
	
	

	public static String getNombre() {
		return nombre;
	}

	public static void setNombre(String nombre) {
		Sistema.nombre = nombre;
	}

	public static int getPuntos() {
		return puntos;
	}

	public static void setPuntos(int puntos) {
		Sistema.puntos = puntos;
	}


	
	
}
