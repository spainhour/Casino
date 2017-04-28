package application;

import java.util.ArrayList;

import javafx.scene.image.Image;


public class War {

	Deck mainDeck = new Deck();
	ArrayList<Card> playerDeck = new ArrayList<>();
	ArrayList<Card> dealerDeck = new ArrayList<>();
	int playerDeckCount;
	int dealerDeckCount;
	Card currentPlayerCard;
	Card currentDealerCard;
	WarGUIController warGUI;
	boolean winner = false;
	int currentPlayerCardValue;
	int currentDealerCardValue;

	public War() {
	}

	public void fillDecks() {
		mainDeck.shuffle();
		for (int i = 0; i < 26; i++) {
			playerDeck.add(mainDeck.getCard(0));
		}
		for (int i = 26; i < 52; i++) {
			dealerDeck.add(mainDeck.getCard(0));
		}
	}

	public boolean gameOver() {
		return (playerDeck.size() == 0 || dealerDeck.size() == 0);
	}

	public boolean cardsEqual() {
		currentPlayerCardValue = currentPlayerCard.getCardVal();
		currentDealerCardValue = currentDealerCard.getCardVal();
		if (currentPlayerCardValue == 1) {
			currentPlayerCardValue = 15;
		}
		if (currentDealerCardValue == 1) {
			currentDealerCardValue = 15;
		}
		return currentPlayerCardValue == currentDealerCardValue;
	}

	public String higherCardWins() {
		if (currentPlayerCardValue > currentDealerCardValue) {
			winner = true;
			return "Player card is higher";
		} else {
			winner = false;
			return "Dealer card is higher";
		}
	}

	public void adjustCards() {
		if (winner) {
			playerDeck.add(playerDeck.size() -1, currentPlayerCard);
			playerDeck.add(playerDeck.size() -1, currentDealerCard);
		} else {
			dealerDeck.add(dealerDeck.size() -1, currentDealerCard);
			dealerDeck.add(dealerDeck.size() -1, currentPlayerCard);
		}
		playerDeck.remove(currentPlayerCard);
		dealerDeck.remove(currentDealerCard);
		System.out.println(playerDeck.size());
		System.out.println(dealerDeck.size());
	}

	public Image playTopPlayerCard() {
		currentPlayerCard = playerDeck.get(0);
		return currentPlayerCard.getCardImage();
	}

	public Image playTopDealerCard() {
		currentDealerCard = dealerDeck.get(0);
		return currentDealerCard.getCardImage();
	}

	public String war() {
		int war = 0;
		while (war < 4) {
			playTopPlayerCard();
			playTopDealerCard();
			System.out.print("test");
			war++;
		}
		return higherCardWins();
	}

}
