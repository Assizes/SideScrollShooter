package entity;

import java.awt.Color;
import java.awt.Graphics;
import Main.Main;
import libs.Identities;
import controll.Controller;
import gfx.Explosion;
import gfx.Textures;
import core.CoreObject;

public class EnemyShots extends Shots
{
	private static int offsetX = 0;
	private static int offsetY = 0;
	private static int width = 10;
	private static int height = 2;
	private Player player;
	private int damage;

	public EnemyShots(int x, int y, int id, Textures tex, int damage) 
	{
		super(x, y, offsetX, offsetY, id, tex, width, height);
		this.damage = damage;
	}

	@Override
	public void tick() 
	{
		if(x-10 > 0)
			x -= 5;
		else
		{
			Controller.getObjects().remove(this);
		}
		
		checkCollision();
	}

	@Override
	public void render(Graphics g) 
	{
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 12, 3);
	}
	
	private void checkCollision()
	{
		for(CoreObject obj : Controller.getObjects())
		{
			if (obj.getId() == Identities.PLAYER)
			{
				player = (Player) obj;
				if(getBounds().intersects(player.getBounds()))
				{
					player.hull -= damage;
					if(player.hull <= 0){
						Controller.getObjects().remove(player);
						Controller.addObject(new Explosion(getX(), getY(), Identities.EXPLOSION, null));
						Main.state = enums.GameState.Menu;
					}
					Controller.getObjects().remove(this);
				}
			}
		}
	}
}
