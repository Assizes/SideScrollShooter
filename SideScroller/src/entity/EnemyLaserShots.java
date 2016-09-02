package entity;

import gfx.Explosion;
import gfx.Textures;
import java.awt.Color;
import java.awt.Graphics;
import Main.Main;
import libs.Identities;
import controll.Controller;
import core.CoreObject;

public class EnemyLaserShots extends Shots
{
	private static int offsetX = -1500;
	private static int offsetY = 0;
	private static int width = 1250;
	private static int height = 3;
	private Player player;
	private Boss boss;
	private int damage;
	private int timer = 0;
	
	public EnemyLaserShots(int x, int y, int id, Textures tex, int damage) 
	{
		super(x, y, offsetX, offsetY, id, tex, width, height);
		this.damage = damage;
	}

	@Override
	public void tick() 
	{	
		if(timer > 150)
			Controller.getObjects().remove(this);
		timer++;
		for(CoreObject obj : Controller.getObjects())
		{
			if (obj.getId() == Identities.BOSS)
			{
				boss = (Boss) obj;
			}
		}
		x += boss.getVelX();
		y += boss.getVelY();
		checkCollision();
		if(Main.bossDestroed)
			Controller.getObjects().remove(this);
	}

	@Override
	public void render(Graphics g) 
	{
		g.setColor(Color.GREEN);
		g.fillRect(x, y, -1250, height);
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
						Controller.addObject(new Explosion(player.getX(), player.getY(), Identities.EXPLOSION, null));
						Main.state = enums.GameState.Menu;
					}
				}
			}
		}
	}
}