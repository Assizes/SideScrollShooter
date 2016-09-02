package utils;
import java.io.IOException;

import libs.Audio;
import libs.Fonts;
import libs.Images;

public class ResourceLoader 
{
	private static BufferedImageLoader imageLoader = new BufferedImageLoader();
	
	/**
	 * Loads images and sprites to be used in the game
	 */
	public static void loadImages()
	{
		try
		{
			Images.title = imageLoader.loadImage("title.png");
			Images.spriteSheet96 = imageLoader.loadImage("96textures.png");
			Images.Expl = imageLoader.loadImage("explosion.gif");
			Images.background = imageLoader.loadImage("farback.png");
			Images.bossShip = imageLoader.loadImage("Boss.png");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}	
	}
	/**
	 * loads fonts to be used in the game
	 */
	public static void loadFonts()
	{
		Fonts.addFont(new Fonts("Prototype.ttf"));
	}
	
	/**
	 * Load sounds and music into game<br>
	 * This includes <code>Sound</code> effects and <code>Music</code>
	 */
	public static void loadSounds()
	{
		AudioPlayer.addSound(Audio.MAIN_BUTTON, "button.ogg");
		AudioPlayer.addMusic(Audio.MUSIC_BACK, "background.ogg");
	}
}
