package entity;

import gfx.Explosion;
import gfx.Textures;
import java.awt.Color;
import java.awt.Graphics;
import Main.Main;
import controll.Controller;
import libs.Identities;
import core.CoreObject;

public class PlayerShots extends Shots
{
	private Enemy enemy;
	private int damage = 25;
	private static int offsetX = 0;
	private static int offsetY = 0;
	private static int width = 10;
	private static int height = 2;
	
	public PlayerShots(int x, int y, int id, Textures tex) 
	{
		super(x, y, offsetX, offsetY, id, tex, width, height);
	}

	@Override
	public void tick() 
	{
		if(x+10 < Main.WIDTH)
			x += 12;
		else
		{
			Controller.getObjects().remove(this);
		}
		
		checkCollision();
	}

	@Override
	public void render(Graphics g) 
	{
		g.setColor(Color.RED);
		g.fillRect(x, y, 12, 3);
	}
	
	private void checkCollision()
	{
		for(CoreObject obj : Controller.getObjects())
		{
			if (obj.getId() == Identities.ENEMY || obj.getId() == Identities.BOSS)
			{
				enemy = (Enemy) obj;
				if(getBounds().intersects(enemy.getBounds()))
				{
					enemy.hull -= damage;
					if(enemy.hull <= 0){
						Controller.getObjects().remove(enemy);
						Controller.addObject(new Explosion(getX(), getY(), Identities.EXPLOSION, null));
						if(obj.getId() == Identities.BOSS)
						{
							Main.bossDestroed = true;
							Controller.addObject(new Explosion(getX()+27, getY()+68, Identities.EXPLOSION, null));
							Controller.addObject(new Explosion(getX()+84, getY()+159, Identities.EXPLOSION, null));
						}
					}
					Controller.getObjects().remove(this);
				}
			}
		}
	}
}
