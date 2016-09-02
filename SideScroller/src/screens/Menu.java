package screens;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import utils.Button;
import libs.Images;
import libs.Reference;
import Main.Main;

public class Menu {

	public Button play, option, quit;
	
	/**
	 * Creates a new menu with the play, option and quit button
	 */
	public Menu()
	{
		int fillerY = 200;
		play = new Button(libs.Reference.CENTER_X - 100, fillerY, 200, 50).setText("Play");
		option = new Button(libs.Reference.CENTER_X -100, fillerY += 65,200,50 ).setText("Options");
		quit = new Button(libs.Reference.CENTER_X -100, fillerY += 65,200,50 ).setText("Quit");
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
		Image back = toolkit.getImage(Reference.SPRITE_LOCATION + "ezgif-resize.gif");
		g.drawImage(back,libs.Reference.CENTER_X - 540, libs.Reference.CENTER_Y-360, null);
		g.drawImage(Images.title, libs.Reference.CENTER_X - 186, 50, null);
		Font prototype = new Font("Prototype", Font.BOLD, 45);
		g.setFont(prototype);
		play.drawButton(g, 55);
		option.drawButton(g, 15);
		quit.drawButton(g, 55);
	}
}