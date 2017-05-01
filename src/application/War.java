package application;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;


public class War {

	Deck mainDeck = new Deck();
	Deck playerDeck = new Deck(true);
	Deck dealerDeck = new Deck(true);
	boolean winner = false;
	public Card currentPlayerCard;
	public Card currentDealerCard;
	private User player;
	private Highscores hs;
	private WarGUIController warGUI;
	
	public War(User player, Highscores hs, WarGUIController wr) {
		this.player=player;
		this.hs = hs;
		this.warGUI = wr;
		fillDecks();
		currentPlayerCard = playTopPlayerCard();
		currentDealerCard = playTopDealerCard();
	}

	public javafx.scene.image.Image getCurrentPlayerCardImage() { return this.currentPlayerCard.getCardImage(); }
	public javafx.scene.image.Image getCurrentDealerCardImage() { return this.currentDealerCard.getCardImage(); }
	
	public void fillDecks() {
		mainDeck.shuffle();
		for (int i = 0; i < 8; i++) {
			playerDeck.putCardBack(mainDeck.getCard(0));
		}
		for (int i = 0; i < 8; i++) {
			dealerDeck.putCardBack(mainDeck.getCard(0));
		}
	}

	public boolean gameOver() {
		return (playerDeck.size() == 0 || dealerDeck.size() == 0);
	}

	//Ace is glitch
	public boolean cardsEqual() { return currentPlayerCard.getCardVal() == currentDealerCard.getCardVal(); }

	public boolean higherCardWins() {
		if(cardsEqual()){
			return war();
		} else if (currentPlayerCard.getCardVal() > currentDealerCard.getCardVal()) {
			winner = true;
			playerDeck.putCardBack(currentDealerCard);//player gets both cards
			playerDeck.putCardBack(currentPlayerCard);
			currentPlayerCard = playTopPlayerCard();
			currentDealerCard = playTopDealerCard();
			return true;
		} else {
			winner = false;
			dealerDeck.putCardBack(currentDealerCard);//dealer gets both cards
			dealerDeck.putCardBack(currentPlayerCard);
			currentPlayerCard = playTopPlayerCard();
			currentDealerCard = playTopDealerCard();
			return false;
		}
	}
	
	public void displayWinner() {
		if (dealerDeck.size() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION, "Player Wins!");
			player.setWarHighscore(1);
			hs.saveHighscores();
			alert.showAndWait();
			warGUI.leaveGame();
			
		} else if (playerDeck.size() == 0){
			Alert alert = new Alert(AlertType.INFORMATION, "Dealer Wins!");
			alert.showAndWait();
			warGUI.leaveGame();
		}
	}

	public Card playTopPlayerCard() {
		if(this.getPlayerCardCount()>0)
		{
		warGUI.setPlayerCardCount(playerDeck.size());
		return playerDeck.getCard(0);
		}
		return this.currentPlayerCard;
	}

	public Card playTopDealerCard() {
		if(this.getDealerCardCount()>0)
		{
			warGUI.setDealerCardCount(dealerDeck.size());
			return dealerDeck.getCard(0);
		}
			return this.currentDealerCard;
	}

	public boolean war() {
		int war = 0;
		ArrayList<Card> playerWarCards = new ArrayList<Card>();
		ArrayList<Card> dealerWarCards = new ArrayList<Card>();
		playerWarCards.add(currentPlayerCard);
		dealerWarCards.add(currentDealerCard);
		while (war < 3) {
			if(getPlayerCardCount()==0){return false; }
			if(getDealerCardCount()==0){return true; }
			playerWarCards.add(playTopPlayerCard());
			dealerWarCards.add(playTopDealerCard());
			
			//System.out.print("test ");
			war++;
		}
		currentPlayerCard = playTopPlayerCard();
		currentDealerCard = playTopDealerCard();
		
		if (higherCardWins()){//player wins
			for (Card c : playerWarCards){ playerDeck.putCardBack(c); }
			for (Card c : dealerWarCards){ playerDeck.putCardBack(c); }
			return true;
		}else{//dealer wins
			for (Card c : playerWarCards){ dealerDeck.putCardBack(c); }
			for (Card c : dealerWarCards){ dealerDeck.putCardBack(c); }
			return false;
		}
		
		
	}

	public int getPlayerCardCount()
	{ 
		if (gameOver()) 
		{
			displayWinner();
			return 0;
		}else{
			return playerDeck.size(); 
		}
	}
	public int getDealerCardCount()
	{
		if (gameOver()) 
		{
			displayWinner();
			return 0;
		}else{
			return dealerDeck.size(); 
	} }
}
