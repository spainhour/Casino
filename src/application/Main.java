package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static cardImage cardIMG = new cardImage();
	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("UsernameGUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Casino");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPlayer(String newUsername){
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
