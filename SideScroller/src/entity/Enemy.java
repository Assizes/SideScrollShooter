package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import gfx.Textures;
import core.CoreObject;

public class Enemy extends CoreObject
{
	public int hull;
	public int shield;
	
	public Enemy(int x, int y, int offsetX, int offsetY, int id, Textures tex, int width, int height, int hull, int shield) 
	{
		super(x, y, offsetX, offsetY, id, tex, width, height);
		this.hull = hull;
		this.shield = shield;
	}

	@Override
	public void tick() 
	{

	}

	@Override
	public void render(Graphics g) 
	{
		
	}
	public Rectangle getBounds()
	{
		return new Rectangle(x + offsetX, y + offsetY, width, height);
	}
	public int getHull()
	{
		return hull;
	}
}
