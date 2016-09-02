package utils;
import java.util.HashMap;
import java.util.Map;

import libs.Reference;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer 
{
	/**
	 * hash map to contain all game's sounds
	 */
	private static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	/**
	 * hash map to contain all game's music
	 */
	private static Map<String, Music> musicMap = new HashMap<String, Music>();
	/**
	 * Used to help sounds not playing multiple times when mouse hovers over a button
	 */
	public static boolean hasPlayedHover = false;
	
	/**
	 * Adds a new sound into the hash map<br>
	 * The file must be <strong>.xm, .mod, .aif or .ogg</strong> format
	 * @param key the key or ID that will be used to access this sound effect
	 * @param path the name of the sound file
	 */
	public static void addSound(String key, String path)
	{
		try {
			soundMap.put(key, new Sound(Reference.SOUND_LOCATION + path));
		} catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * Adds a new music into the hash map<br>
	 * The file must be <strong>.xm, .mod, .aif or .ogg</strong> format
	 * @param key the key or ID that will be used to access this music
	 * @param path the name of the sound file
	 */
	public static void addMusic(String key, String path)
	{
		try {
			musicMap.put(key, new Music(Reference.SOUND_LOCATION + path));
		} catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static Sound getSound (String key)
	{
		return soundMap.get(key);
	}
	
	public static Music getMusic (String key)
	{
		return musicMap.get(key);
	}
	
	public static void playSound(String key)
	{
		soundMap.get(key).play();
	}
	
	public static void playMusic(String key)
	{
		musicMap.get(key).loop(1, (float) 0.3);
	}
}
