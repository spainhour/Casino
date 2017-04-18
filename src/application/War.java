package application;

import java.util.ArrayList;

public class War {
	
	ArrayList<Card> playerDeck;
	ArrayList<Card> dealerDeck;
	int playerDeckCount;
	int dealerDeckCount;
	Card currentPlayerCard;
	Card currentDealerCard;
	
	public War() {
	}
	
	private void fillPlayerDeck() {
	}
	
	private boolean gameOver() {
		if (playerDeckCount == 0 || dealerDeckCount == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private void compareCards() {
		if (currentPlayerCard.getCardVal() == currentDealerCard.getCardVal()) {
			war();
		}
	}
	
	private void war(){
	}
	
	private void displayCard() {
		
	}
}
