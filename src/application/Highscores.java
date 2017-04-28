package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Highscores implements Serializable{
	
	public static final String UserInfoFile = "Highscores.txt";
	
	HashMap<String, int[]> highscores = new HashMap<String, int[]>();
	
	public void addUser(String username) {
		highscores.put(username, new int[3]);
	}
	
	public HashMap<String, int[]> getHashmap() {
		return highscores;
	}
	
	public Set<String> getKeys() {
		return highscores.keySet();
	}
	
	public Collection<int[]> getValues() {
		return highscores.values();
	}
	
	public int getWarHighscore(String username) {
		int[] userHighscores = highscores.get(username);
		return userHighscores[0];
	}
	
	public int getBlackjackHighscore(String username) {
		int[] userHighscores = highscores.get(username);
		return userHighscores[1];
	}
	
	public int getTexasHighscore(String username) {
		int[] userHighscores = highscores.get(username);
		return userHighscores[2];
	}
	
	public void setWarHighscore(String username, int score) {
		int[] tempHighscoresArray = highscores.get(username);
		tempHighscoresArray[0] = score;
		highscores.put(username, tempHighscoresArray);
	}
	
	public void setBlackjackHighscore(String username, int score) {
		int[] tempHighscoresArray = highscores.get(username);
		tempHighscoresArray[1] = score;
		highscores.put(username, tempHighscoresArray);
	}
	
	public void setTexasHighscore(String username, int score) {
		int[] tempHighscoresArray = highscores.get(username);
		tempHighscoresArray[2] = score;
		highscores.put(username, tempHighscoresArray);
	}
	
	
	public int[] getHighscoresFor(String username) {
		return highscores.get(username);
	}
	
	public static void saveHighscores(Highscores highscores) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(UserInfoFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(highscores);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Highscores loadHighscores() {
		if (fileExists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(UserInfoFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Highscores tempHighscores = (Highscores) ois.readObject();
				ois.close();
				return tempHighscores;
			} catch (ClassNotFoundException | IOException e){
				e.printStackTrace();
			}
		}			
		Highscores tempHighscores = new Highscores();
		return tempHighscores;
	}
	
	public static boolean fileExists() {
		return new File(UserInfoFile).isFile();
	
	}
}
