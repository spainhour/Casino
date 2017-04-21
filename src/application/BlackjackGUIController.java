package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	ImageView pFirstCard;

	@FXML
	ImageView dFirstCard;

	@FXML
	ImageView dSecondCard;

	@FXML
	ImageView pSecondCard;

	@FXML
	ImageView pThirdCard;

	@FXML
	ImageView pFourthCard;

	@FXML
	ImageView pFifthCard;

	@FXML
	Label myScore;

	@FXML
	Label dealerScore;

	Blackjack game = new Blackjack();
	int hitNum = 0;

	void initialize() throws IOException {
		game.newGameAction();
		game.reset();
		game.deal();
		pFirstCard.setImage(game.playerHand.getCard(0).getCardImage());
		pSecondCard.setImage(game.playerHand.getCard(1).getCardImage());
		dSecondCard.setImage(game.dealerHand.getCard(1).getCardImage());
		pThirdCard.setImage(null);
		pFourthCard.setImage(null);
		pFifthCard.setImage(null);

		setScores();

	}

	public void setUsername(String username) {
		usernameLabel.setText(username);
	}

	void setScores(){
		String pScore = Integer.toString(game.playerHand.getTotal(true));
		String dScore = Integer.toString(game.dealerHand.getTotal(true));
		myScore.setText(pScore);
		dealerScore.setText(dScore);
	}

	@FXML
	void hit() throws IOException{
		game.playerHit();
		if(hitNum == 0){
			pThirdCard.setImage(game.playerHand.getCard(2).getCardImage());
		} else if(hitNum == 1){
			pFourthCard.setImage(game.playerHand.getCard(3).getCardImage());
		} else if(hitNum == 2){
			pFifthCard.setImage(game.playerHand.getCard(4).getCardImage());
		}

		hitNum += 1;
		if(game.playerBusted == true){
			winner("Dealer wins!!");
		}
		game.dealersTurn();
		if(game.dealerBusted == true){
			winner("Player wins!!");
		}
		setScores();


	}


	void checkWin() throws IOException{
		game.checkForWinner();
		if(game.dealerWins == true){
			winner("Dealer wins!!");
		} else if(game.playerWins == true){
			winner("Player wins!!");
		} else if(game.push == true){
			winner("It's a push!!");
		}
	}

	@FXML
	void stand() throws IOException{
		checkWin();
	}

	void winner(String whoWon) throws IOException{
		ButtonType playAgain = new ButtonType("Play again?");
		ButtonType exit = new ButtonType("Exit to lobby?");
		Alert alert = new Alert(AlertType.NONE, whoWon, playAgain, exit);
		alert.showAndWait();
		if(alert.getResult() == playAgain){
			game.reset();
			initialize();
		}
		if(alert.getResult() == exit){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MainMenuGUIController mainMenu = (MainMenuGUIController) loader.getController();
			Stage mainMenuStage = new Stage();
			Scene scene = new Scene(root);
			mainMenuStage.setScene(scene);
			mainMenu.initialize();
			mainMenu.setUsername(usernameLabel.getText());
			mainMenuStage.show();
			usernameLabel.getScene().getWindow().hide();
		}

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
			mainMenu.initialize();
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
