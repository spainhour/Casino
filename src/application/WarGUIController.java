package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WarGUIController {
	
	@FXML
	Label opponentCardsLabel;
	@FXML
	Label myCardsLabel;
	@FXML
	Button leaveGame;
	@FXML
	Button playCard;
	@FXML
	Label usernameLabel;
	@FXML
	ImageView cardLeft;
	@FXML
	ImageView cardRight;
	
	void initialize() {
		Image card1 = new Image("PNG-cards-1.3/3_of_clubs.png");
		cardLeft.setImage(card1);
		Image card2 = new Image("PNG-cards-1.3/6_of_spades.png");
		cardRight.setImage(card2);
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
