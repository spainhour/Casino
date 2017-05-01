package application;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.event.EventHandler;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
	ImageView dThirdCard;

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

	Blackjack game = new Blackjack();
	int hitNum = 1;
	private User player;

	void initialize(User player) throws IOException {
		this.player = player;
		usernameLabel.setText(player.getUsername());
		pFirstCard.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {

		    }
		});
		pSecondCard.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {

		    }
		});
		pThirdCard.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {

		    }
		});
		game.newGameAction();
		game.reset();
		game.deal();
		pFirstCard.setImage(game.playerHand.getCard(0).getCardImage());
		pSecondCard.setImage(game.playerHand.getCard(1).getCardImage());
		dSecondCard.setImage(game.dealerHand.getCard(1).getCardImage());
		pThirdCard.setImage(null);
		pFourthCard.setImage(null);
		pFifthCard.setImage(null);
		dThirdCard.setImage(null);
		myPoints.setText(Integer.toString(game.points));

		if(game.playerHand.getCard(0).getCardVal() == 1){
			pFirstCard.setOnMousePressed(new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent mouseEvent) {
			        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
			        	if(mouseEvent.isShiftDown() && mouseEvent.getClickCount() == 1){
			                System.out.println("shift");
			                game.playerTotal -= 10;
			                myScore.setText(Integer.toString(game.playerTotal));
			                System.out.println(game.playerTotal);
			            }
			            if(mouseEvent.getClickCount() == 2){
			            	game.playerTotal += 10;
			            	 myScore.setText(Integer.toString(game.playerTotal));
			                System.out.println(game.playerTotal);
			            }
			        }
			    }
			});
		}

		if(game.playerHand.getCard(1).getCardVal() == 1){
			pSecondCard.setOnMousePressed(new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent mouseEvent) {
			        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
			        	if(mouseEvent.isShiftDown() && mouseEvent.getClickCount() == 1){
			                System.out.println("shift");
			                game.playerTotal -= 10;
			                myScore.setText(Integer.toString(game.playerTotal));
			                System.out.println(game.playerTotal);
			            }
			            if(mouseEvent.getClickCount() == 2){
			            	game.playerTotal += 10;
			            	 myScore.setText(Integer.toString(game.playerTotal));
			                System.out.println(game.playerTotal);
			            }
			        }
			    }
			});
		}


		IntegerSpinnerValueFactory valueFactory =
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
		if(hitNum == 1){
			if(game.playerHand.getCard(2).getCardVal() == 1){
				pThirdCard.setOnMousePressed(new EventHandler<MouseEvent>() {
				    @Override
				    public void handle(MouseEvent mouseEvent) {
				        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
				        	if(mouseEvent.isShiftDown() && mouseEvent.getClickCount() == 1){
				                System.out.println("shift");
				                game.playerTotal += 10;
				                myScore.setText(Integer.toString(game.playerTotal));
				                System.out.println(game.playerTotal);
				            }
				            if(mouseEvent.getClickCount() == 2){
				            	game.playerTotal -= 10;
				            	 myScore.setText(Integer.toString(game.playerTotal));
				                System.out.println(game.playerTotal);
				            }
				        }
				    }
				});
			}
			pThirdCard.setImage(game.playerHand.getCard(2).getCardImage());
			//System.out.println("Index: " + 2);
		} else if(hitNum == 2){
			//System.out.println("Hit 2 -- Hand size: " + game.playerHand.size());
			pFourthCard.setImage(game.playerHand.getCard(3).getCardImage());
			//System.out.println("Index: " + 3);
		} else if(hitNum == 3){
			//System.out.println("Hit 3 -- Hand size: " + game.playerHand.size());
			pFifthCard.setImage(game.playerHand.getCard(4).getCardImage());
			//System.out.println("Index: " + 4);
		}

		setScores();
		game.dealersTurn();
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
		ButtonType playAgain = new ButtonType("Play Again");
		ButtonType exit = new ButtonType("Exit To Lobby");
		Alert alert = new Alert(AlertType.NONE, whoWon, playAgain, exit);
		alert.showAndWait();
		if(alert.getResult() == playAgain){
			game.reset();
			hitNum = 1;
			initialize(player);
		}
		if(alert.getResult() == exit){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MainMenuGUIController mainMenu = (MainMenuGUIController) loader.getController();
			Stage mainMenuStage = new Stage();
			Scene scene = new Scene(root);
			mainMenuStage.setScene(scene);
			mainMenu.initialize(player);
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
			mainMenu.initialize(this.player);
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
