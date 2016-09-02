package screens;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import libs.Images;
import utils.Button;

public class Game 
{

	private int x = 0;
	private int x2 = 2138;
//	private int x3 = 1920;
	private int timer = 0;

	public Button back;
	
	/**
	 * Creates a new menu with the play, option and quit button
	 */
	public Game()
	{
		
	}
	
	/**
	 * Draws the menu
	 * @param g the Graphics context of our <strong> <code> Main class </code> </strong>
	 */
	public void render(Graphics g)
	{
		
		if(x > -2138)
		{
			g.drawImage(Images.background, x, 0, null);
			if(timer > 10)
				x--;
		}
		else
			x = 2138;
		if (x2 > -2138)
		{
			g.drawImage(Images.background, x2, 0, null);
			if(timer > 10){
				x2--;
				timer = 0;
			}
		}
		else
			x2 = 2138;
		
        timer += 1;
        
		Font prototype = new Font("Prototype", Font.BOLD, 45);
		g.setFont(prototype);
	}
}
