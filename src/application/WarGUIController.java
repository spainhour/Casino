package application;

import javafx.application.Platform;
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
	Button playToEnd;
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
	
	private User player;
	private Highscores hs;
	private War war;

	
	void initialize(User player, Highscores hs) {
		usernameLabel.setText(player.getCasino());
		this.player=player;
		this.hs = hs;
		this.war = new War(player, hs, this);
		setCardCounts();
	}
	

	@FXML
	void playCard() {
		playerCard.setImage(this.war.currentPlayerCard.getCardImage());
		dealerCard.setImage(this.war.currentDealerCard.getCardImage());
		
		if (war.higherCardWins()) {
			outputMessages.setText("Player card is higher");
		} else {
			outputMessages.setText("Dealer card is higher");
		}
		setCardCounts();
		
	}
	
	@FXML
	void playToEnd() throws Exception {
		while(!war.gameOver())
		{
			Platform.runLater(new Runnable() {
			    public void run() {
			    	playCard();
			    }
			});
			
			Thread.sleep(250);
		}
		
	}

	public void setOutputMessagesLabel(String message) {
		System.out.println(message);
		outputMessages.setText(message);
	}

	public void setCardCounts() {
		String playerCards = Integer.toString(war.getPlayerCardCount());
		String dealerCards = Integer.toString(war.getDealerCardCount());
		myCardCount.setText(playerCards);
		opponentCardCount.setText(dealerCards);
	}
	
	public void leaveGameWrapper(){
		Platform.runLater(new Runnable() {
		    public void run() {
		    	leaveGame();
		    }
		});
	}

	@FXML
	void leaveGame() {
		try {
			hs.saveHighscores();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MainMenuGUIController mainMenu = (MainMenuGUIController) loader.getController();
			Stage mainMenuStage = new Stage();
			Scene scene = new Scene(root);
			mainMenuStage.setScene(scene);
			mainMenu.initialize(player);
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
