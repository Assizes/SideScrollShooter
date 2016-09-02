package entity;
import gfx.Explosion;
import gfx.Textures;
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;
import libs.Identities;
import controll.Controller;
import Main.Main;
import core.CoreObject;

public class Player extends CoreObject
{
	private static CopyOnWriteArrayList<CoreObject> objects = Controller.getObjects();
	private Enemy enemy ;
	private static int offsetX = 1;
	private static int offsetY = 35;
	private static int width = 90;
	private static int height = 23;
	public int timer = 11;
	public boolean spaceKey = false;
	public int hull = 100;
	public int shield = 50;
	private int xH;
	private int yH = 10; 
	private boolean hitted = false;
	private int hitTimer = 0;
	private boolean dead = false;
	
	public Player(int x, int y, int id, Textures tex) 
	{
		super(x+1, y+35, offsetX, offsetY, id, tex, width, height);
	}

	@Override
	public void tick() 
	{
		if(x+velX > 5 && x+velX <Main.WIDTH-100)
			x += velX;
		if(y+velY > -20 && y+velY <Main.HEIGHT-100)
			y += velY;	
		if(spaceKey == true)
		{
			if(timer > 10)
			{
				Controller.addObject(new PlayerShots(x+86, y+58, Identities.SHOT, null));
				timer = 0;
			}
			timer++;
		}
		else
			timer = 11;
	
		if(hitTimer > 60){
			hitTimer = 0;
			hitted = false;
		}		
		if(hitted)
			hitTimer++;
		checkCollision();
	}

	@Override
	public void render(Graphics g) 
	{
		xH = 20;
		g.drawImage(tex.playerShip, x ,y ,null);
		for(int i=0; i<hull; i += 5)
		{
			g.setColor(Color.RED);
			g.fillRect(xH, yH, 2, 10);
			xH +=3;
		}
		xH = 20;
		for(int i=0; i<shield; i += 5)
		{
			g.setColor(Color.CYAN);
			g.fillRect(xH, yH + 15, 2, 10);
			xH +=3;
		}
	}
	private void checkCollision()
	{
		if(!hitted)
		{
			for(CoreObject obj : objects)
			{
				if (obj.getId() == Identities.ENEMY || obj.getId() == Identities.BOSS)
				{
					enemy = (Enemy) obj;
					if(getBounds().intersects(enemy.getBounds()))
					{
						int s = hull;
						hull -= enemy.hull;
						enemy.hull -= s;
						hitted = true;
						if(this.hull <= 0){
							objects.remove(this);
							Controller.addObject(new Explosion(getX(), getY(), Identities.EXPLOSION, null));
						}
						if(enemy.hull <= 0){
							objects.remove(enemy);
							Controller.addObject(new Explosion(getX(), getY(), Identities.EXPLOSION, null));
						}
					}
				}
			}
		}
	}
}
