package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Deck {
	private ArrayList<Card> cardDeck = new ArrayList<Card>();
	private cardImage cI;

	public Deck()
	{
		this.cI = Main.cardIMG;
		refreshDeck();
		System.out.println("Done!");
		printCardsInDeck();
		System.out.println("Done!");
	}


	public void shuffle()
	{
		Random rnd = ThreadLocalRandom.current();
	    for (int i = cardDeck.size() - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      Card a = this.cardDeck.get(index);
	      cardDeck.set(index,cardDeck.get(i));
	      cardDeck.set(i,a);
	    }
	}


	public void refreshDeck(){
		for(int cardVal = 0; cardVal < Card.getDeckSize(); cardVal++)
		{

			Card addedCard = new Card(cardVal);
			//System.out.println(addedCard.card.ordinal());
			cardDeck.add(addedCard);
		}
	}

	public boolean hasNext() {
		for (int i = 0; i < cardDeck.size() - 1; i++) {
			if (cardDeck.get(i + 1) != null) {
				return true;
			} else {
				i++;
			}
		}
		return false;
	}

	public int size() {
		return cardDeck.size();
	}

	public ArrayList<Card> getCardsOfSuit(int suit)
	{
		ArrayList<Card> cards = new ArrayList<>();
		for (Iterator<Card> cardIter = cardDeck.iterator(); cardIter.hasNext();)
		{
			Card cardToCheck = cardIter.next();
			if (cardToCheck.getCardSuit() == suit)
			{
				int indexOF = this.cardDeck.indexOf(cardToCheck); //Basically, it removes the card from THIS deck and adds it to the cards Arraylist
				cards.add(this.cardDeck.remove(indexOF));
			}
		}
		return cards;
	}

	public void printCardsInDeck()
	{
		//ArrayList<Card> cards = new ArrayList<>();
		for (Iterator<Card> cardIter = cardDeck.iterator(); cardIter.hasNext();)
		{
			Card cardToCheck = cardIter.next();
			System.out.println(cardToCheck.card.ordinal());
		}
	}

	public ArrayList<Card> getCardsOfValue(int val)
	{
		ArrayList<Card> cards = new ArrayList<>();
		for (Iterator<Card> cardIter = cardDeck.iterator(); cardIter.hasNext();)
		{
			Card cardToCheck = cardIter.next();
			if (cardToCheck.getCardVal() == val)
			{
				int indexOF = this.cardDeck.indexOf(cardToCheck); //Basically, it removes the card from THIS deck and adds it to the cards Arraylist
				cards.add(this.cardDeck.remove(indexOF));
			}
		}
		return cards;
	}

	public Card getCard(int i)
	{
		return cardDeck.remove(i);
	}

	public Card getRandomCard()
	{
		Random rnd = ThreadLocalRandom.current();
		int index = rnd.nextInt(cardDeck.size());
		Card removedCard = cardDeck.remove(1);
		System.out.println("randomCard Val: " + removedCard.getCardVal());
		return removedCard;
	}
}
