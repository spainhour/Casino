package application;

public class User {
	private String username;
	private int warScore = 0;
	private int blackjackScore = 0;
	private int texasScore = 0;
	private int money = 0;
	
	public User(String username){ 
		this.username = username;
		this.warScore = 0;
		this.blackjackScore = 0;
		this.texasScore = 0;
		this.money = 1000;
	}
	
	public String getUsername(){ return this.username; }
	public String getCasino(){ 
		return this.username + "'s Casino"; }
	
	//GETTERS
	public int getWarHighscore() { return this.warScore; } 
	public int getBlackjackHighscore() { return this.blackjackScore; }
	public int getTexasHighscore() { return this.texasScore;}
	
	//SETTERS
	public int setMoney(int score) { return this.money += score; }
	public int setWarHighscore(int score) { return this.warScore += score; }
	public int setBlackjackHighscore(int score) { return this.blackjackScore += score; }
	public int setTexasHighscore(int score) { return this.texasScore += score; }
}
