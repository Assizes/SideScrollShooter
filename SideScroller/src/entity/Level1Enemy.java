package entity;

import gfx.Textures;
import java.awt.Graphics;
import java.util.Random;
import libs.Identities;
import Main.Main;
import controll.Controller;

public class Level1Enemy extends Enemy
{
	private static int offsetX = 1;
	private static int offsetY = 31;
	private static int width = 88;
	private static int height = 40;
	public static int hull = 50;
	public static int shield = 0;
	private Random r = new Random();
	private int d;
	private int timerMov = 0;
	private int timerShot = 220;
	public Level1Enemy(int x, int y, int id, Textures tex) 
	{
		super(x, y, offsetX, offsetY, id, tex, width, height,hull,shield);
	}

	@Override
	public void tick() 
	{
		if(timerShot > 220 && x < Main.WIDTH-20)
		{
			Controller.addObject(new EnemyShots(getX()+13, getY()+58, Identities.SHOT, null, 10));
			Controller.addObject(new EnemyDioganalUpShots(getX()+13, getY()+58, Identities.DiagShot, null, 10));
			timerShot = 0;
		}
		timerShot++;
		x += velX;
		if(y+velY > -20 && y+velY <Main.HEIGHT-100)
			y += velY;	
		if(x == -300)
			Controller.getObjects().remove(this);
		movmentControll();
	}

	@Override
	public void render(Graphics g) 
	{
		g.drawImage(tex.enemyShip, x ,y ,null);
	}
	
	private void movmentControll()
	{
		velX = -2;
		if(timerMov > 100)
		{
			d = r.nextInt(2);
			timerMov = 0;
		}
		switch (d) {
		case 0:
			if (y-20 > -20)
				velY = -2;
			else
				d = 1;
			break;
		case 1:
			if (y+20 < Main.HEIGHT-100)
				velY = +2;
			else
				d = 0;
			break;
		default:
			break;
		}

		timerMov++;
	}

}
