package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class texasGUIController {

	@FXML
	Label usernameLabel;

	@FXML
	ImageView firstPlayerCard;

	@FXML
	ImageView secondPlayerCard;

	@FXML
	ImageView firstEnemyCard1;

	@FXML
	ImageView firstEnemyCard2;

	@FXML
	ImageView secondEnemyCard1;

	@FXML
	ImageView secondEnemyCard2;
	
	private User player;
	private Highscores hs;

	void initialize(User player, Highscores hs) {
		this.player = player;
		this.hs = hs;
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
			mainMenu.initialize(player,hs);
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
