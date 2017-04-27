package application;

public class User {
	private String username;
	private Highscores highscores;
	
	public User(String username, Highscores highscores){
		this.username = username;
		this.highscores = highscores;
		System.out.println(this.highscores.toString());
		highscores.addUser(this.username);
	}
	
	public String getUsername(){ return this.username; }
	public Highscores getHighscores() {
		return highscores;
	}
}
