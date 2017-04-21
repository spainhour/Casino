package application;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
	private Card[] cardDeck = new Card[52];
	
	public Deck(cardImage cI)
	{
		for(int i = 0; i < cardDeck.length; i++)
		{
			cardDeck[i] = new Card(cI,i);
		}
	}
	
	public void shuffle()
	{
		Random rnd = ThreadLocalRandom.current();
	    for (int i = cardDeck.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      Card a = this.cardDeck[index];
	      cardDeck[index] = cardDeck[i];
	      cardDeck[i] = a;
	    }
	}
	
	public boolean hasNext() {
		for (int i = 0; i < cardDeck.length - 1; i++) {
			if (cardDeck[i + 1] != null) {
				return true;
			} else {
				i++;
			}
		}
		return false;
	}
	
	public int size() {
		return cardDeck.length;
	}
	
	public Card getCard(int i)
	{
		return cardDeck[i];
	}
	
	public Card getRandomCard()
	{
		Random rnd = ThreadLocalRandom.current();
		int index = rnd.nextInt(cardDeck.length);
		return cardDeck[index];
	}
}
