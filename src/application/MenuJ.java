package application;

import Control.Sistema;

import Juegos.Minas;
import Juegos.PPT;
import Juegos.Pong;
import Juegos.Tenis2J;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuJ {

public static void display() {
    	
    	
    	Stage window;
    	window = new Stage();
        window.setTitle("Elegir juego.");
		
		TextField textField = new TextField();
        
		Button minas = new Button("Buscaminas.");
        
		//cargar.setOnAction(e->b=Sistema.buscarP(textField.getText(),b));
        minas.setOnAction(event -> {
        	window.close();
        	Minas m = new Minas();
        	try {
        		Stage window2;
            	window2 = new Stage();
        		m.start(window2);
        		window.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        });
        
        
        
        
		Button ppt = new Button("P,P & T.");
		ppt.setOnAction(event -> {
			PPT pp= new PPT();
			Stage window2;
        	window2 = new Stage();
    		pp.start(window2);
    		window.close();
        
        });
		
		Button pong = new Button("Pong");
		pong.setOnAction(event -> {
			window.close();
			MenuJ.dis();
        
        });
        
        Button volver = new Button("Volver");
        volver.setOnAction(event -> {
        	Menu1.show();
        	window.close();
        
        });
        
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(minas,ppt,pong, volver);

        Scene scene = new Scene(root, 300, 250);
        
        window.setScene(scene);
        window.show();
        
        
	}

	private static void dis() {
		Stage window;
    	window = new Stage();
        window.setTitle("Elegir Modo.");
       
        Button j1 = new Button("1J");
        j1.setOnAction(event -> {
        	Stage pongwin = new Stage();
        	Pong p = new Pong();
        	try {
				p.start(pongwin);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	window.close();
        
        });
        
        Button j2 = new Button("2J");
        j2.setOnAction(event -> {
        	Stage pongwin = new Stage();
        	Tenis2J tenis = new Tenis2J();
        	try {
				tenis.start(pongwin);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	window.close();
        
        });
        
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(j1,j2);
        Scene scene = new Scene(root, 300, 250);
        
        window.setScene(scene);
        
        window.show();
	}
}
