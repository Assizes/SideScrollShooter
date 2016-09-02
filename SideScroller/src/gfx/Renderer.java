package gfx;
import Main.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Renderer 
{
	/**
	 * Renders the background of the game
	 * This includes things that will not effect or interact with the player
	 * @param g the Graphics context of Main class
	 */
	public void renderBackground(Graphics g)
	{
		switch(Main.state)
		{
		case Game:
			Main.getInstance().getGame().render(g);
			break;
		case Menu:
			Main.getInstance().getMenu().render(g);
			break;
		case Options:
			Main.getInstance().getOptions().render(g);
			break;
		case Pause:
			break;
		default:
			g.setColor(Color.red);
			g.drawString("Unknown Game State", 150 , 150);
			break;
		}
	}
	
	/**
	 * Renders the foreground of the game
	 * This includes things that will effect or interact with player
	 * @param g the Graphics context of Main class
	 */
	public void renderForeground(Graphics g)
	{
		switch(Main.state)
		{
		case Game:
			Main.getInstance().getController().render(g);
			break;
		case Menu:
			break;
		case Options:
			break;
		case Pause:
			break;
		default:
			g.setColor(Color.red);
			g.drawString("Unknown Game State", 150 , 150);
			break;
		}
	}

}
