package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
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
	Label myPoints;

	@FXML
	Label dealerScore;

	@FXML
	Spinner bet;

	@FXML
	ChoiceBox aceValue;

	Blackjack game = new Blackjack();
	int hitNum = 0;

	List<String> values = new List<String>("Ace Value", "1", "11");

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
		myPoints.setText(Integer.toString(game.points));
		aceValue.set

		if(game.playerHand.contains()){
			aceValue.setVisible(true);
		}

		IntegerSpinnerValueFactory valueFactory = //
	                new IntegerSpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 10, 10);
		 bet.setValueFactory(valueFactory);

		setScores();
		while(game.playerHand.getTotal(true) > 21 || game.dealerHand.getTotal(true) > 21){
			game.newGameAction();
			game.reset();
		}
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
		game.setBet((int) bet.getValue());
		game.playerHit();
		if(hitNum == 0){
			pThirdCard.setImage(game.playerHand.getCard(2).getCardImage());
		} else if(hitNum == 1){
			pFourthCard.setImage(game.playerHand.getCard(3).getCardImage());
		} else if(hitNum == 2){
			pFifthCard.setImage(game.playerHand.getCard(4).getCardImage());
		}

		setScores();

		hitNum += 1;
		checkWin();


	}


	void checkWin() throws IOException{
		setScores();
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
			hitNum = 0;
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
