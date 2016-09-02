package gfx;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import libs.Reference;
import controll.Controller;
import core.CoreObject;

public class Explosion extends CoreObject
{
	private static int offsetX = 0;
	private static int offsetY = 0;
	private static int width = 0;
	private static int height = 0;
	long lastTime = System.currentTimeMillis();
	long currentTime;
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image backg = null;
	
	public Explosion(int x, int y, int id, Textures tex)
	{
		super(x,y, offsetX, offsetY, id, tex, width, height);
		backg = toolkit.createImage(Reference.SPRITE_LOCATION + "explosion.gif");
	}
	
	@Override
	public void render(Graphics g) 
	{
		g.drawImage(backg, x, y-55, null);
		if(currentTime - lastTime  >= 1800){
			backg.flush();
			Controller.getObjects().remove(this);
		}
	}

	@Override
	public void tick() 
	{
		currentTime = System.currentTimeMillis();
	}
}
