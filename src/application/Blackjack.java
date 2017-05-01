package application;

import java.util.ArrayList;

public class Blackjack {

	private int index = 0;
	Deck aDeck = new Deck();
	public Hand playerHand;
	public Hand dealerHand;

	public boolean playerBusted;
	public boolean dealerBusted;
	public boolean gameOver;
	private boolean gameStarted;
	public boolean hit;
	public boolean doubleDown;
	public boolean playerWins;
	public boolean dealerWins;
	public boolean push;

	public int points = 1000;
	public int bet;

	private ArrayList<Card> cards;
	int playerTotal = 0;
	int dealerTotal = 0;


	public Blackjack(){
	}

	public void newGameAction(){
		reset();
		gameOver = true;
		aDeck = new Deck();
		aDeck.shuffle();
	}

	public void setBet(int b){
		bet =  b;
	}


	public void reset()
	{
		dealerHand = new Hand();
		playerHand = new Hand();
		playerBusted = false;
		dealerBusted = false;
		gameOver = true;
		hit = false;
		doubleDown = false;
		gameStarted = false;
		playerWins = false;
		dealerWins = false;
		push = false;

	}

	void betting(){
		if(playerWins || push){
			points = points + bet;
		} else{
			points = points - bet;
		}
	}

	public void deal(){
		playerHand = new Hand();
		dealerHand = new Hand();
		playerHand.add(aDeck.getRandomCard());
		dealerHand.add(aDeck.getRandomCard());
		playerHand.add(aDeck.getRandomCard());
		dealerHand.add(aDeck.getRandomCard());
		playerTotal = playerHand.getTotal(true);
	}


	public void playerHit()
	{
		playerHand.add(aDeck.getRandomCard());
		if(playerHand.getTotal(true)>21)
		{
			playerBusted = true;
			gameOver = true;
		}
		playerTotal  = playerTotal + playerHand.getCard(playerHand.size()-1).getCardVal();
	}

	public void dealersTurn()
	{
		if(!playerBusted && dealerHand.getTotal(true) <= 16){
			dealerHand.add(aDeck.getRandomCard());
		}
		if(dealerHand.getTotal(true)>21){
			dealerBusted = true;
		}
		dealerTotal = dealerHand.getTotal(true);
	}

	public void checkForWinner()
	{
		if(!playerBusted && !dealerBusted && playerHand.getTotal(true) > dealerHand.getTotal(true)){
			playerWins = true;
		} else if(!playerBusted && !dealerBusted && playerHand.getTotal(true)< dealerHand.getTotal(true)){
			dealerWins = true;
		} else if(playerHand.getTotal(true) == dealerHand.getTotal(true)){
			push = true;
		} else if(playerBusted){
			dealerWins = true;
		} else if(dealerBusted){
			playerWins = true;
		}

		betting();
	}

	public String toString(){
		return ("Player Hand: " + playerHand.toString() + "\nPlayer Total: " + playerHand.getTotal(true)
				+ "\nDealer Hand: " + dealerHand.toString() + "\nDealer Total: " + dealerHand.getTotal(true));

	}

}
