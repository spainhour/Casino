package application;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HighscoresGUIController {
	
	@FXML
	Label usernameLabel;
	
	@FXML
	Button back;
	
	@FXML
	ListView<Integer> highscoreList;
	
	ObservableList<Integer> users;
	
	@FXML
	ChoiceBox<String> cb;

	private Highscores hs;

	private User player;
	
	void initialize(User player, Highscores hs) {
		this.player = player;
		this.hs = hs;
		cb.getItems().addAll("Choose a game", "War", "Blackjack", "Texas Hold 'Em");
		cb.getSelectionModel().select(0);
		cb.getSelectionModel().selectedItemProperty()
		.addListener((obs, oldV, newV) ->
		displayHighscores());	
	}
	
	private void displayHighscores() {
		if (cb.getValue().equals("War")) {
			System.out.println("It worked");
			for (String username : hs.getKeys()) {
				System.out.println(hs.getUser(username).getWarHighscore());
				users.add(hs.getUser(username).getWarHighscore());
			}
		}
		if (cb.getValue().equals("Blackjack")) {
			for (String username : hs.getKeys()) {
				users.add(hs.getUser(username).getBlackjackHighscore());
			}
		}
		if (cb.getValue().equals("Texas Hold 'Em")) {
			for (String username : hs.getKeys()) {
				users.add(hs.getUser(username).getTexasHighscore());
			}
		}
	//	highscoreList.setItems(users);
	}

	@FXML
	void back() {
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

	public void setUsername(String username) {
		usernameLabel.setText(username);
	}

}
