package gfx;

import java.awt.image.BufferedImage;
import libs.Images;
import utils.SpriteSheet;

public class Textures 
{
	private utils.SpriteSheet sprites96;
	
	public static BufferedImage playerShip;
	public static BufferedImage enemyShip;
	
	public Textures()
	{
		sprites96 = new SpriteSheet(Images.spriteSheet96, 96);
		
		initTextures();
	}
	
	private void initTextures()
	{
		playerShip = sprites96.getSprite(1, 1);
		enemyShip = sprites96.getSprite(2, 1);
	}
}
