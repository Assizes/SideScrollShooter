package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import Input.MouseInput;

public class Button extends Rectangle 
{

	private String text;
	
	public Button() 
	{
		super();
	}

	public Button(Rectangle r) 
	{
		super(r);
		// TODO Auto-generated constructor stub
	}

	public Button(Point p) 
	{
		super(p);
		// TODO Auto-generated constructor stub
	}

	public Button(Dimension d) 
	{
		super(d);
		// TODO Auto-generated constructor stub
	}

	public Button(int width, int height) 
	{
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	public Button(Point p, Dimension d) 
	{
		super(p, d);
		// TODO Auto-generated constructor stub
	}

	public Button(int x, int y, int width, int height) 
	{
		super(x, y, width, height);
		
	}
	public Button setText (String text)
	{
		this.text = text;
		return this;
	}
	public void drawButton(Graphics g, int offset)
	{
		int xx = x+offset;
		int yy = y + 40;
		
		if(MouseInput.MOUSE.intersects(this))
		{
			g.setColor(Color.YELLOW);
		}
		else
		{
			g.setColor(Color.WHITE);
		}
		if(!MouseInput.pressed && MouseInput.MOUSE.intersects(this))
			g.drawRect(x, y, width, height);
		else if(MouseInput.pressed && MouseInput.MOUSE.intersects(this))
			g.fillRect(x, y, width, height);
		else
			g.drawRect(x, y, width, height);
		
		g.setColor(Color.RED);
		g.drawString(text, xx, yy);
	}
}
