package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class cardImage 
{
	private Image[] cardImages = new Image[52];

	public cardImage()
	{
		cardImages[0] = new Image("PNG-cards-1.3/10_of_clubs.png");
		cardImages[1] = new Image("PNG-cards-1.3/10_of_diamonds.png");
		cardImages[2] = new Image("PNG-cards-1.3/10_of_hearts.png");
		cardImages[3] = new Image("PNG-cards-1.3/10_of_spades.png");
		cardImages[4] = new Image("PNG-cards-1.3/2_of_clubs.png");
		cardImages[5] = new Image("PNG-cards-1.3/2_of_diamonds.png");
		cardImages[6] = new Image("PNG-cards-1.3/2_of_hearts.png");
		cardImages[7] = new Image("PNG-cards-1.3/2_of_spades.png");
		cardImages[8] = new Image("PNG-cards-1.3/3_of_clubs.png");
		cardImages[9] = new Image("PNG-cards-1.3/3_of_diamonds.png");
		cardImages[10] = new Image("PNG-cards-1.3/3_of_hearts.png");
		cardImages[11] = new Image("PNG-cards-1.3/3_of_spades.png");
		cardImages[12] = new Image("PNG-cards-1.3/4_of_clubs.png");
		cardImages[13] = new Image("PNG-cards-1.3/4_of_diamonds.png");
		cardImages[14] = new Image("PNG-cards-1.3/4_of_hearts.png");
		cardImages[15] = new Image("PNG-cards-1.3/4_of_spades.png");
		cardImages[16] = new Image("PNG-cards-1.3/5_of_clubs.png");
		cardImages[17] = new Image("PNG-cards-1.3/5_of_diamonds.png");
		cardImages[18] = new Image("PNG-cards-1.3/5_of_hearts.png");
		cardImages[19] = new Image("PNG-cards-1.3/5_of_spades.png");
		cardImages[20] = new Image("PNG-cards-1.3/6_of_clubs.png");
		cardImages[21] = new Image("PNG-cards-1.3/6_of_diamonds.png");
		cardImages[22] = new Image("PNG-cards-1.3/6_of_hearts.png");
		cardImages[23] = new Image("PNG-cards-1.3/6_of_spades.png");
		cardImages[24] = new Image("PNG-cards-1.3/7_of_clubs.png");
		cardImages[25] = new Image("PNG-cards-1.3/7_of_diamonds.png");
		cardImages[26] = new Image("PNG-cards-1.3/7_of_hearts.png");
		cardImages[27] = new Image("PNG-cards-1.3/7_of_spades.png");
		cardImages[28] = new Image("PNG-cards-1.3/8_of_clubs.png");
		cardImages[29] = new Image("PNG-cards-1.3/8_of_diamonds.png");
		cardImages[30] = new Image("PNG-cards-1.3/8_of_hearts.png");
		cardImages[31] = new Image("PNG-cards-1.3/8_of_spades.png");
		cardImages[32] = new Image("PNG-cards-1.3/9_of_clubs.png");
		cardImages[33] = new Image("PNG-cards-1.3/9_of_diamonds.png");
		cardImages[34] = new Image("PNG-cards-1.3/9_of_hearts.png");
		cardImages[35] = new Image("PNG-cards-1.3/9_of_spades.png");
		cardImages[36] = new Image("PNG-cards-1.3/ace_of_clubs.png");
		cardImages[37] = new Image("PNG-cards-1.3/ace_of_diamonds.png");
		cardImages[38] = new Image("PNG-cards-1.3/ace_of_hearts.png");
		cardImages[39] = new Image("PNG-cards-1.3/ace_of_spades.png");
		//cardImages[40] = new Image("PNG-cards-1.3/black_joker.png");
		cardImages[40] = new Image("PNG-cards-1.3/jack_of_clubs.png");
		cardImages[41] = new Image("PNG-cards-1.3/jack_of_diamonds.png");
		cardImages[42] = new Image("PNG-cards-1.3/jack_of_hearts.png");
		cardImages[43] = new Image("PNG-cards-1.3/jack_of_spades.png");
		cardImages[44] = new Image("PNG-cards-1.3/king_of_clubs.png");
		cardImages[45] = new Image("PNG-cards-1.3/king_of_diamonds.png");
		cardImages[46] = new Image("PNG-cards-1.3/king_of_hearts.png");
		cardImages[47] = new Image("PNG-cards-1.3/king_of_spades.png");
		cardImages[48] = new Image("PNG-cards-1.3/queen_of_clubs.png");
		cardImages[49] = new Image("PNG-cards-1.3/queen_of_diamonds.png");
		cardImages[50] = new Image("PNG-cards-1.3/queen_of_hearts.png");
		cardImages[51] = new Image("PNG-cards-1.3/queen_of_spades.png");
		//cardImages[53] = new Image("PNG-cards-1.3/red_joker.png");
	}
	
	public javafx.scene.image.Image get(int index){ return this.cardImages[index]; } 
	
}
