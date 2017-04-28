package application;


import java.util.ArrayList;

public class Hand
{
	private ArrayList<Card> myHand = new ArrayList<Card>();

	public void add(Card c)
	{
		myHand.add(c);
	}

	public Card getCard(int num){
		Card m = myHand.get(num);
		return m;
	}

	public int getTotal(boolean all)
	{
		int total = 0;
		for(int x = 0; x < myHand.size(); x++){
			int cardValue = myHand.get(x).getCardVal();
			if(cardValue == 1){
				total += 1;
			} else{
				total += cardValue;
			}
		}


		int index = 0;
		while(total > 21 && index < myHand.size())
		{
			int cardValue = myHand.get(index).getCardVal();
			if(cardValue == 1)
				total = total - 10;
			index ++;
		}
		return total;
	}

	public int setAce11(){
		return getTotal(true) + 10;
	}

	public boolean contains(){
		for(int i = 0; i < myHand.size(); i++){
			if(myHand.get(i).getCardVal() == 1){
				return true;
			}
		} return false;
	}

	public int size(){
		return myHand.size();
	}

	public String toString()
	{
		return myHand.toString();
	}

}

