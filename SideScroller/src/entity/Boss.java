package entity;

import gfx.Textures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import libs.Identities;
import libs.Images;
import Main.Main;
import controll.Controller;

public class Boss extends Enemy
{
	private static int offsetX = 10;
	private static int offsetY = 4;
	private static int width = 465;
	private static int height = 330;
	public static int hull = 2000;
	public static int shield = 0;
	private Random r = new Random();
	private int d;
	private int timerMov = 0;
	private int timerShot = 80;
	private int timerSecShot = 0;
	private int timerLas = 0;
	private int xH;
	private int yH = 10; 
	
	public Boss(int x, int y, int id, Textures tex) 
	{
		super(x, y, offsetX, offsetY, id, tex, width, height, hull, shield);
	}

	@Override
	public void tick()
	{
		if(timerShot > 90 && x < Main.WIDTH-50)
		{
			Controller.addObject(new EnemyShots(x+19, y+110, Identities.SHOT, null, 20));
			Controller.addObject(new EnemyShots(x+153, y+260, Identities.SHOT, null, 20));
			timerShot = 0;
		}
		if (timerSecShot > 60 && x < Main.WIDTH -50)
		{
			Controller.addObject(new EnemyShots(x+54, y+209, Identities.SHOT, null, 10));
			Controller.addObject(new EnemyShots(x+153, y+244, Identities.SHOT, null, 10));
			Controller.addObject(new EnemyDioganalUpShots(x+15, y+105, Identities.DiagShot, null, 10));
			Controller.addObject(new EnemyDioganalUpShots(x+57, y+60, Identities.DiagShot, null, 10));
			Controller.addObject(new EnemyDioganalDownShots(x+130, y+226, Identities.DiagShot, null, 10));
			Controller.addObject(new EnemyDioganalDownShots(x+186, y+293, Identities.DiagShot, null, 10));
			timerSecShot = 0;
		}
		if(timerLas > 300 && x < Main.WIDTH-100){
			Controller.addObject(new EnemyLaserShots(x+30, y+164, Identities.BOSS_SHOT, null, 2));
			timerLas = 0;
		}
		timerLas++;
		timerShot++;
		timerSecShot++;
		movmentControll();
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Images.bossShip, x ,y ,null);
		if(x < Main.WIDTH - 100){
			xH = 1000;
			for(int i=0; i<getHull(); i += 50)
			{
				g.setColor(Color.GREEN);
				g.fillRect(xH, yH, 2, 10);
				xH +=3;
			}
		}
	}

	private void movmentControll()
	{
		if (x > 800)
			velX = -2;
		else
			velX = 0;
		if(timerMov > 150)
		{
			d = r.nextInt(2);
			timerMov = 0;
		}
		switch (d) {
		case 0:
			if (y-2 > 0)
				velY = -2;
			else
				d = 1;
			break;
		case 1:
			if (y+2 < Main.HEIGHT-350)
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
