package application;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BlackjackGUIController {
	
	@FXML
	Label usernameLabel;
	
	@FXML
	ImageView firstCard;
	
	@FXML
	ImageView secondCard;
	
	@FXML
	ImageView thirdCard;
	
	@FXML
	Label myScore;
	
	@FXML
	Label dealerScore;
	
	
	void initialize() {
			Image card = new Image("PNG-cards-1.3/10_of_clubs.png");
			firstCard.setImage(card);
			Image card1 = new Image("PNG-cards-1.3/4_of_diamonds.png");
			secondCard.setImage(card1);
			Image card2 = new Image("PNG-cards-1.3/ace_of_spades.png");
			thirdCard.setImage(card2);
			myScore.setText("21");
			dealerScore.setText("");
			
	}
	
	public void setUsername(String username) {
		usernameLabel.setText(username);
	}
	
	@FXML
	void leaveGame() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MainMenuGUIController mainMenu = (MainMenuGUIController) loader.getController();
			Stage mainMenuStage = new Stage();
			Scene scene = new Scene(root);
			mainMenuStage.setScene(scene);
			mainMenu.setUsername(usernameLabel.getText());
			mainMenuStage.show();
			usernameLabel.getScene().getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
			outputMessage(AlertType.ERROR, "Could not load Main Menu");
		}
	}
	
	private void outputMessage(AlertType error, String string) {
		Alert alert = new Alert(error, string);
		alert.showAndWait();	
	}
}
