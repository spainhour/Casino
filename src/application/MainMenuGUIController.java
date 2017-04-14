package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenuGUIController {

	@FXML
	Label usernameLabel;

	@FXML
	Button playWar;

	@FXML
	Button playBlackjack;

	@FXML
	Button playCheat;

	String username = "";

	void initialize() {

	}

	public void setUsername(String username) {
		usernameLabel.setText(username);
	}

	@FXML
	void playWar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WarGUI.fxml"));
			BorderPane root = (BorderPane) loader.load();
			WarGUIController War = (WarGUIController) loader.getController();
			Stage warStage = new Stage();
			Scene scene = new Scene(root);
			warStage.setScene(scene);
			War.setUsername(usernameLabel.getText());
			warStage.show();
			usernameLabel.getScene().getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
			outputMessage(AlertType.ERROR, "Could not load War");
		}
	}


	@FXML
	void playBlackjack() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BlackjackGUI.fxml"));
			BorderPane root = (BorderPane) loader.load();
			BlackjackGUIController Blackjack = (BlackjackGUIController) loader.getController();
			Stage blackjackStage = new Stage();
			Scene scene = new Scene(root);
			blackjackStage.setScene(scene);
			Blackjack.initialize();
			Blackjack.setUsername(usernameLabel.getText());
			blackjackStage.show();
			usernameLabel.getScene().getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
			outputMessage(AlertType.ERROR, "Could not load Blackjack");
		}
	}

	@FXML
	void playCheat() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CheatGUI.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			CheatGUIController Cheat = (CheatGUIController) loader.getController();
			Stage cheatStage = new Stage();
			Scene scene = new Scene(root);
			cheatStage.setScene(scene);
			Cheat.setUsername(usernameLabel.getText());
			Cheat.initialize();
			cheatStage.show();
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
