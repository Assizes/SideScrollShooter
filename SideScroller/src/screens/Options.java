package screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import libs.Images;
import Main.Main;
import utils.Button;

public class Options 
{
	public Button back;
	
	/**
	 * Creates a new menu with the play, option and quit button
	 */
	public Options()
	{
		int fillerY = 200;
		back = new Button(libs.Reference.CENTER_X - 100, fillerY, 200, 50).setText("Back");
	}
	
	/**
	 * Draws the menu
	 * @param g the Graphics context of our <strong> <code> Main class </code> </strong>
	 */
	public void render(Graphics g)
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		g.setColor(Color.decode("#323232"));
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		Image background = toolkit.getImage("./res/sprites/ezgif-resize.gif");
		g.drawImage(background,libs.Reference.CENTER_X - 540, libs.Reference.CENTER_Y-360, null);
		g.drawImage(Images.title, libs.Reference.CENTER_X - 186, 50, null);
		Font prototype = new Font("Prototype", Font.BOLD, 45);
		g.setFont(prototype);
		back.drawButton(g, 50);
	}
}