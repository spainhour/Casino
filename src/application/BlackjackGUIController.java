package application;

import java.awt.image.BufferedImage;
import java.io.File;

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

	int hitNum = 1;
	int dealerFinalScore = 0;
	Card d1;
	Card d2;


	void initialize() {
		Card p1 = new Card(new cardImage());
		Card p2 = new Card(new cardImage());
		// Need to fix this. The card constructor automatically generates a random card
		// so causes a bug here. Looking into it.
		/*if(p1.getCardNumber() == 40 || p2.getCardNumber() == 40 ){
			Card p1 = new Card(new cardImage());
			Card p2 = new Card(new cardImage());
		}*/
		pFirstCard.setImage(p1.getCardImage());
		pSecondCard.setImage(p2.getCardImage());

		d1 = new Card(new cardImage());
		d2 = new Card(new cardImage());

		//dFirstCard.setImage(new Image("\src\blackCard.jpg"));
		dSecondCard.setImage(d2.getCardImage());
		setInitialScore(p1,p2,d1,d2);

		pThirdCard.setImage(null);
		pFourthCard.setImage(null);
		pFifthCard.setImage(null);

		if(isOver() == true){
			if(didPBust()){
				dFirstCard.setImage(d1.getCardImage());
				whoWon("Dealer wins!!");
			} else if(isTwentyOne()){
				dFirstCard.setImage(d1.getCardImage());
				whoWon("Player wins!!");
			} else if(didDBust()){
				dFirstCard.setImage(d1.getCardImage());
				whoWon("Player wins!!");
			} else if(dealerFinalScore == 21){
				dFirstCard.setImage(d1.getCardImage());
				whoWon("Player wins!!");
			}
		}
	}

	public void setUsername(String username) {
		usernameLabel.setText(username);
	}

	@FXML
	void hit(){
		if(isOver() == false){
			if(Integer.parseInt(myScore.getText()) < 21 && hitNum == 1){
				Card p3 = null;
				moreHits(pThirdCard, p3);
			} else if(Integer.parseInt(myScore.getText()) < 21 && hitNum == 2){
				Card p4 = null;
				moreHits(pFourthCard, p4);
			} else if(Integer.parseInt(myScore.getText()) < 21 && hitNum == 3){
				Card p5 = null;
				moreHits(pFifthCard, p5);
			}

			dealerHit(d1, d2);
		}

		if(isOver() == true){
			if(didPBust()){
				dFirstCard.setImage(d1.getCardImage());
				whoWon("Dealer wins!!");
			} else if(didDBust()){
				dFirstCard.setImage(d1.getCardImage());
				whoWon("Player wins!!");
			} else if(isTwentyOne()){
				dFirstCard.setImage(d1.getCardImage());
				whoWon("Player wins!!");
			}
		}

	}


	void moreHits(ImageView pImage, Card pNum){
		pNum = new Card(new cardImage());
		pImage.setImage(pNum.getCardImage());
		calculateScore(Integer.parseInt(myScore.getText()), pNum);
		hitNum++;

	}

	private void calculateScore(int current, Card p) {
		if(p.getCardNumber() == 36 || p.getCardNumber() == 37 || p.getCardNumber() == 38 || p.getCardNumber() == 39 ){
			int num = whatValue();
			String score = Integer.toString(current + num);
			myScore.setText(score);
		} else{
			String score = Integer.toString(current + p.getCardVal());
			myScore.setText(score);
		}
	}

	private int whatValue() {
		ButtonType one = new ButtonType("1");
		ButtonType eleven = new ButtonType("11");
		Alert alert = new Alert(AlertType.NONE, "What value do you want your Ace to be?", one, eleven);
		alert.showAndWait();

		if(alert.getResult() == one){
			return 1;
		} else if(alert.getResult() == eleven){
			return 11;
		}

		return 1;
	}

	void dealerHit(Card d1, Card d2){
		if(d1.getCardVal() + d2.getCardVal() <= 16){
			Card d3 = new Card(new cardImage());
			System.out.println(d3.getCardVal());
			dealerFinalScore = dealerFinalScore + d3.getCardVal();
			System.out.println(dealerFinalScore);
		}
	}

	@FXML
	void stand(){
		int playerFinalScore = Integer.parseInt(myScore.getText());
		if(dealerFinalScore == 21 && playerFinalScore == 21){
			dFirstCard.setImage(d1.getCardImage());
			whoWon("It's a push!!");
		} else if(isTwentyOne()){
			dFirstCard.setImage(d1.getCardImage());
			whoWon( "Player wins!!");
		} else if(playerFinalScore > dealerFinalScore ){
			dFirstCard.setImage(d1.getCardImage());
			whoWon("Player wins!!");
		} else if(didPBust()){
			dFirstCard.setImage(d1.getCardImage());
			whoWon( "Dealer wins!!");
		} else if(didDBust()){
			dFirstCard.setImage(d1.getCardImage());
			whoWon( "Player wins!!");
		}  else{
			dFirstCard.setImage(d1.getCardImage());
			whoWon("Dealer wins!!");
		}
	}

	void whoWon(String text){
		ButtonType exit = new ButtonType("Exit to lobby");
		ButtonType playAgain = new ButtonType("Play again");
		Alert alert = new Alert(AlertType.INFORMATION, text, playAgain, exit);
		alert.showAndWait();
		if(alert.getResult() == playAgain){
			initialize();
			hitNum = 1;
			dealerFinalScore = 0;
		} else if(alert.getResult() == exit){
			leaveGame();
		}
	}


	boolean didPBust(){
		if(Integer.parseInt(myScore.getText()) > 21){
			return true;
		} return false;
	}

	boolean didDBust(){
		if(dealerFinalScore > 21){
			return true;
		} return false;
	}

	boolean isTwentyOne(){
		if(Integer.parseInt(myScore.getText()) == 21){
			return true;
		} return false;
	}

	boolean isOver(){
		if(didDBust() == true || didPBust() == true || isTwentyOne() == true){
			return true;
		} return false;
	}


	void setInitialScore(Card p1, Card p2, Card d1, Card d2){
		String pScore = Integer.toString(p1.getCardVal() + p2.getCardVal());
		String dScore = Integer.toString(d1.getCardVal() + d2.getCardVal());
		myScore.setText(pScore);
		dealerScore.setText(dScore);
		dealerFinalScore = d1.getCardVal() + d2.getCardVal();
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
