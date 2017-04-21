package application;

import java.util.ArrayList;

public class Blackjack {

	/*private Card player1 = new Card(new cardImage());
	private Card player2 = new Card(new cardImage());
	private Card dealer1 = new Card(new cardImage());
	private Card dealer2 = new Card(new cardImage());*/
	private int index = 0;
	Deck aDeck = new Deck(Main.cardIMG);
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

	private ArrayList<Card> cards;
	int playerTotal = 0;
	int dealerTotal = 0;

	public void BlackJack(){ //Card player1, Card player2, Card dealer1, Card dealer2){
		/*this.player1 = player1;
		this.player2 = player2;
		this.dealer1 = dealer1;
		this.dealer2 = dealer2;*/
		this.cards = cards;

	}

	public void newGameAction(){
		reset();
		gameOver = true;
		aDeck.shuffle();
	}

	public void reset()
	{
		aDeck = new Deck(Main.cardIMG);
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

	public void deal(){
		playerHand = new Hand();
		dealerHand = new Hand();
		playerHand.add(aDeck.getRandomCard());
		dealerHand.add(aDeck.getRandomCard());
		playerHand.add(aDeck.getRandomCard());
		dealerHand.add(aDeck.getRandomCard());
	}

	
	public void playerHit()
	{
		playerHand.add(aDeck.getRandomCard());
		if(playerHand.getTotal(true)>21)
		{
			playerBusted = true;
			gameOver = true;
		}
		playerTotal  = playerHand.getTotal(true);
	}

	public void dealersTurn()
	{
		if(!playerBusted)
			while(dealerHand.getTotal(true)<= 16)
				dealerHand.add(aDeck.getRandomCard());
		if(dealerHand.getTotal(true)>21)
			dealerBusted = true;
		dealerTotal = dealerHand.getTotal(true);
	}

	public void checkForWinner()
	{
		if(playerBusted)
		{
			dealerWins = true;
			//System.out.println("Dealer wins");
		}
		else
			if(dealerBusted)
			{
				playerWins = true;
				//System.out.println("Player wins");
			}
			else
				if(playerHand.getTotal(true)>dealerHand.getTotal(true))
				{
					playerWins = true;
					//System.out.println("Player wins");
				}
				else
					if(playerHand.getTotal(true)< dealerHand.getTotal(true))
					{
						dealerWins = true;
						//System.out.println("Dealer wins");

					}
					else
						if(playerHand.getTotal(true) == dealerHand.getTotal(true))
						{
							push = true;
						}
	}

	public String toString(){
		return ("Player Hand: " + playerHand.toString() + "\nPlayer Total: " + playerHand.getTotal(true)
				+ "\nDealer Hand: " + dealerHand.toString() + "\nDealer Total: " + dealerHand.getTotal(true));

	}

}
