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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2954614235082362747L;
	public static final String UserInfoFile = "Highscores.txt";
	
	HashMap<String, User> highscores; 
	
	public void addUser(User user) {
		highscores.put(user.getUsername(),user);
	}
	
	public HashMap<String, User> getHashmap() {
		return highscores;
	}
	
	public Set<String> getKeys() {
		return highscores.keySet();
	}
	/*
	public Collection<int[]> getValues() {
		return highscores.values();
	}*/
	
	public User getUser(String username){
		return this.highscores.get(username);
	}
	
	public void saveHighscores() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(UserInfoFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.highscores);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, User> loadHighscores() {
		if (fileExists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(UserInfoFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				HashMap<String, User> tempHighscores = (HashMap<String, User>) ois.readObject();
				ois.close();
				return tempHighscores;
			} catch (ClassNotFoundException | IOException e){
				e.printStackTrace();
			}
		}			
		Highscores tempHighscores = new Highscores();
		return tempHighscores.getHashmap();
	}
	
	public Highscores(){
		this.highscores = loadHighscores();
	}
	
	public static boolean fileExists() {
		return new File(UserInfoFile).isFile();
	}
}
