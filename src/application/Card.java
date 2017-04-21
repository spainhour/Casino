package application;

import java.util.Random;

public class Card {
	private card card;
	private cardImage cardImageArray;

	public Card(cardImage cI)
	{
		Random rn = new Random();
		this.card = card.values()[rn.nextInt(card.values().length)];
		this.cardImageArray = cI;
	}

	public Card(cardImage cI, int cardIndex)
	{
		Random rn = new Random();
		this.card = card.values()[cardIndex];
		this.cardImageArray = cI;
	}

	private enum card
	{
		ten_of_clubs ( 10, 1),
		ten_of_diamonds ( 10, 2),
		ten_of_hearts ( 10, 3),
		ten_of_spades ( 10, 4),
		two_of_clubs ( 2, 1),
		two_of_diamonds ( 2, 2),
		two_of_hearts ( 2, 3),
		two_of_spades ( 2, 4),
		three_of_clubs ( 3, 1),
		three_of_diamonds ( 3, 2),
		three_of_hearts ( 3, 3),
		three_of_spades ( 3, 4),
		four_of_clubs ( 4, 1),
		four_of_diamonds ( 4, 2),
		four_of_hearts ( 4, 3),
		four_of_spades ( 4, 4),
		five_of_clubs ( 5, 1),
		five_of_diamonds ( 5, 2),
		five_of_hearts ( 5, 3),
		five_of_spades ( 5, 4),
		six_of_clubs ( 6, 1),
		six_of_diamonds ( 6, 2),
		six_of_hearts ( 6, 3),
		six_of_spades ( 6, 4),
		seven_of_clubs ( 7, 1),
		seven_of_diamonds ( 7, 2),
		seven_of_hearts ( 7, 3),
		seven_of_spades ( 7, 4),
		eight_of_clubs ( 8, 1),
		eight_of_diamonds ( 8, 2),
		eight_of_hearts ( 8, 3),
		eight_of_spades ( 8, 4),
		nine_of_clubs ( 9, 1),
		nine_of_diamonds ( 9, 2),
		nine_of_hearts ( 9, 3),
		nine_of_spades ( 9, 4),
<<<<<<< HEAD
		ace_of_clubs ( 1, 1),
		ace_of_diamonds ( 1, 2),
		ace_of_hearts ( 1, 3),
		ace_of_spades ( 1, 4),
		//black_joker ( 0, 0),
		jack_of_clubs ( 11, 1),
		jack_of_diamonds ( 11, 3),
		jack_of_hearts ( 11, 3),
		jack_of_spades ( 11, 4),
		king_of_clubs ( 13, 1),
		king_of_diamonds ( 13, 2),
		king_of_hearts ( 13, 3),
		king_of_spades ( 13, 4),
		queen_of_clubs ( 12, 2),
		queen_of_diamonds ( 12, 2),
		queen_of_hearts ( 12, 3),
		queen_of_spades ( 12, 4);
//		red_joker ( 0, 0); 
		
		private final int val; 
		private final int suit; 
=======
		ace_of_clubs ( 15, 1),
		ace_of_diamonds ( 15, 2),
		ace_of_hearts ( 15, 3),
		ace_of_spades ( 15, 4),
		black_joker ( 0, 0),
		jack_of_clubs ( 12, 1),
		jack_of_diamonds ( 12, 3),
		jack_of_hearts ( 12, 3),
		jack_of_spades ( 12, 4),
		king_of_clubs ( 14, 1),
		king_of_diamonds ( 14, 2),
		king_of_hearts ( 14, 3),
		king_of_spades ( 14, 4),
		queen_of_clubs ( 13, 2),
		queen_of_diamonds ( 13, 2),
		queen_of_hearts ( 13, 3),
		queen_of_spades ( 13, 4);
//		red_joker ( 0, 0);

		private final int val;
		private final int suit;
>>>>>>> origin/master

	    private card(int  val, int suit)
	    {
	        this.val = val;
	        this.suit = suit;
	    }
	};

	public String toString(){ return "" + this.card.val;}

	public int getCardVal(){ return this.card.val;}
	public int getCardSuit(){ return this.card.suit;}
	public int getCardNumber(){ return this.card.ordinal();}
	public javafx.scene.image.Image getCardImage(){ return this.cardImageArray.get(this.card.ordinal()); }

}

