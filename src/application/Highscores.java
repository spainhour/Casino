package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Highscores {
	
	public static final String UserInfoFile = "Highscores.txt";
	
	HashMap<String, int[]> highscores = new HashMap<>();
	
	public void addUser(String username) {
		highscores.put(username, new int[]{0,0,0});
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
