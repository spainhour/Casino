package application;

import java.util.Random;

public class Card {
	private cardSuit cardSuit;
	private cardNumber cardNumber;
	
	public static void main(String Args[])
	{
		
	}
	
	public Card()
	{
		Random rn = new Random();
		this.cardSuit = cardSuit.values()[rn.nextInt(3) + 1];
		this.cardNumber = cardNumber.values()[rn.nextInt(13) + 1];
	}
	
	private enum cardSuit 
	{
		SPADES ("spades"),
		CLUBS ("clubs"),
		DIAMONDS ("diamonds"),
		HEARTS ("hearts");
		
		private final String name;       

	    private cardSuit(String s) {
	        name = s;
	    }
	    
	    public String get(){ return this.name(); }
		
	};
	
	private enum cardNumber 
	{
		ONE ("1"),
		TWO ("2"),
		THREE ("3"),
		FOUR ("4"),
		FIVE ("5"),
		SIX ("6"),
		SEVEN ("7"),
		EIGHT ("8"),
		NINE ("9"),
		TEN ("10"),
		JACK ("jack"),
		KING ("king"),
		QUEEN ("queen"),
		ACE ("ace");
		
		private final String val;       

	    private cardNumber(String val) 
	    {
	        this.val = val;
	    }
	    
	    public String get(){ return this.val; }
		
	};
	
	public String getCardPNG()
	{
		return this.cardNumber.get() + "_of_" + this.cardSuit.get();
	}
	
	public int getCardValue()
	{
		if(this.cardNumber.get() == "jack"){ return 11; }
		else if (this.cardNumber.get() == "king"){ return 12; }
		else if (this.cardNumber.get() == "queen"){ return 13; }
		else if (this.cardNumber.get() == "ace"){ return 14; }
		else
		{
			return Integer.parseInt(this.cardNumber.get());
		}
		
	}
	
}

