package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class UsernameGUIController {

	@FXML
	Label enterUsername;

	@FXML
	TextField usernameTextField;
	
	Highscores hs;
	
	
	@FXML
	void initialize() {
		hs = new Highscores();
		System.out.println(hs.getKeys());
	}

	@FXML
	void getUsername() {
		if (usernameTextField.getText().equals("")) {
			outputMessage(AlertType.ERROR, "Enter a username");
		} else {
			String tempUser = usernameTextField.getText();
			if (hs.userExists(tempUser)) {
				System.out.println("Made it");
				showMainMenu(hs.getUser(tempUser),hs);
				hs.saveHighscores();
				System.out.println(hs.userExists(tempUser));
			} else {
				showMainMenu(new User(tempUser),hs);
				hs.saveHighscores();
				System.out.println(hs.userExists(tempUser));
			}
		}
	}

	private void outputMessage(AlertType alertType, String message) {
		Alert alert = new Alert(alertType, message);
		alert.showAndWait();
	}

	private void showMainMenu(User player, Highscores hs) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MainMenuGUIController mainMenu = (MainMenuGUIController) loader.getController();
			mainMenu.initialize(player, hs);
			Stage mainMenuStage = new Stage();
			Scene scene = new Scene(root);
			mainMenuStage.setScene(scene);
			mainMenuStage.show();
			usernameTextField.getScene().getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
			outputMessage(AlertType.ERROR, "Could not load Main Menu");
		}
	}
}
