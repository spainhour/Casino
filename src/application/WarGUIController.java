package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WarGUIController {

	@FXML
	Button leaveGame;
	@FXML
	Button playCard;
	@FXML
	Label usernameLabel;
	@FXML
	Label opponentCardCount;
	@FXML
	Label myCardCount;
	@FXML
	Label outputMessages;
	@FXML
	ImageView dealerCard;
	@FXML
	ImageView playerCard;


	int intOpponentCardCount = 26;
	int intPlayerCardCount = 26;
	War war = new War();
	String winner = "";

	void initialize() {
		setCardCounts();
		war.fillDecks();
	}

	@FXML
	void playCard() throws InterruptedException {
		playerCard.setImage(war.playTopPlayerCard());
		dealerCard.setImage(war.playTopDealerCard());
		if (!war.cardsEqual()) {
			winner = war.higherCardWins();
			war.adjustCards();
			adjustCardCounts();
			setCardCounts();
			outputMessages.setText(winner);
		} else {
			outputMessages.setText("War!");
			winner = war.war();
			war.adjustCards();
			adjustCardCounts();
			adjustCardCounts();
			adjustCardCounts();
			adjustCardCounts();
			setCardCounts();
			outputMessages.setText(winner);
		}
		if (gameOver()) {
			displayWinner();
		}
	}

	private void displayWinner() {
		if (intOpponentCardCount == 0) {
			Alert alert = new Alert(AlertType.INFORMATION, "Player Wins!");
			alert.showAndWait();
			leaveGame();
		} else if (intPlayerCardCount == 0){
			Alert alert = new Alert(AlertType.INFORMATION, "Dealer Wins!");
			alert.showAndWait();
			leaveGame();
		}
	}

	private void adjustCardCounts() {
		if (winner.equals("Player card is higher")) {
			intPlayerCardCount += 1;
			intOpponentCardCount -= 1;
		} else if (winner.equals("Dealer card is higher")) {
			intOpponentCardCount += 1;
			intPlayerCardCount -= 1;
		}
	}

	public void setOutputMessagesLabel(String message) {
		System.out.println(message);
		outputMessages.setText(message);
	}

	public void setCardCounts() {
		String myCards = Integer.toString(intPlayerCardCount);
		String opponentCards = Integer.toString(intOpponentCardCount);
		myCardCount.setText(myCards);
		opponentCardCount.setText(opponentCards);
	}

	public void setUsername(String username) {
		usernameLabel.setText(username);
	}

	private boolean gameOver() {
		return (intPlayerCardCount == 0 || intOpponentCardCount == 0);
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
			mainMenu.initialize();
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
