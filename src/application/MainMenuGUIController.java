package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	@FXML
	ImageView king;
	
	@FXML
	ImageView jack;
	
	@FXML
	ImageView joker;
	
	@FXML
	Button aboutWar;
	
	@FXML
	Button aboutBlackjack;
	
	@FXML
	Button aboutCheat;

	String username = "";

	void initialize() {
		Image kingCard  = new Image("PNG-cards-1.3/king_of_hearts2.png");
		king.setImage(kingCard);
		Image jackCard = new Image("PNG-cards-1.3/jack_of_spades2.png");
		jack.setImage(jackCard);
		Image jokerCard = new Image("PNG-cards-1.3/black_joker.png");
		joker.setImage(jokerCard);
		
		//Card card3 = new Card(new cardImage());
		//king.setImage(card3.getCardImage());
	}
	
	@FXML
	private void aboutWar() {
		createAboutAlert(AlertType.INFORMATION, "War", "The deck is divided evenly among the players, giving each a down stack. In unison, each player reveals the top card of their deck – this is a battle – and the player with the higher card takes both of the cards played and moves them to their stack. Aces are high, and suits are ignored. If the two cards played are of equal value, then there is a war. Both players place the next 3 cards from their pile face down, and then another card face-up. The owner of the higher face-up card wins the war and adds all eight cards on the table to the bottom of their deck. If the face-up cards are again equal then the battle repeats with another set of face-down/up cards. This repeats until one player's face-up card is higher than their opponent's.");
	}
	
	@FXML
	private void aboutBlackjack() {
		createAboutAlert(AlertType.INFORMATION, "Blackjack", "The aim of the game is to accumulate a higher point total than the dealer, but without going over 21. You compute your score by adding the values of your individual cards. The cards 2 through 10 have their face value, Jack, Queen, and King are worth 10 points each, and the Ace is worth either 1 or 11 points (player's choice).");
	}
	
	@FXML
	private void aboutCheat() {
		createAboutAlert(AlertType.INFORMATION, "Cheat", "On the table is a discard pile, which starts empty. A turn consists of discarding one or more cards face down on the pile, and calling out their rank. The first player must discard Aces, the second player discards Twos, the next player Threes, and so on. After Tens come Jacks, then Queens, then Kings, then back to Aces, etc.Since the cards are discarded face down, you do not in fact have to play the rank you are calling. For example if it is your turn to discard Sevens, you may actually discard any card or mixture of cards; in particular, if you don't have any Sevens you will be forced to play some other card or cards. Any player who suspects that the card(s) discarded by a player do not match the rank called can challenge the play by calling Cheat! Then the cards played by the challenged player are exposed and one of two things happens: 1. if they are all of the rank that was called, the challenge is false, and the challenger must pick up the whole discard pile; 2. if any of the played cards is different from the called rank, the challenge is correct, and the person who played the cards must pick up the whole discard pile. After the challenge is resolved, play continues in normal rotation: the player to the left of the one who was challenged plays and calls the next rank in sequence. The first player to get rid of all their cards and survive any challenge resulting from their final play wins the game. If you play your last remaining card(s), but someone challenges you and the cards you played are not what you called, you pick up the pile and play continues.");
	}

	public void setUsername(String username) {
		usernameLabel.setText(username);
	}
	
	public void createAboutAlert(AlertType alertType, String game, String message) {
		Alert alert = new Alert(alertType, message);
		alert.setHeaderText("How to play: " + game);
		alert.setResizable(true);
		alert.showAndWait();
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
			War.initialize();
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
