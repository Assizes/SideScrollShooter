package core;
import gfx.Textures;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class CoreObject 
{
	protected int x, y, velX, velY, width, height;
	protected int offsetX;
	protected int offsetY;
	protected int id;
	protected Textures tex;
	/**
	 * Creates a new object
	 * @param x the x coordinate of the object on screen
	 * @param y the y coordinate of the object on screen
	 * @param id the ID of the object
	 */

	public CoreObject(int x, int y, int offsetX, int offsetY, int id, Textures tex, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.id = id;
		this.tex = tex;
		this.width = width;
		this.height = height;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public Rectangle getBounds()
	{
		return new Rectangle(x + offsetX, y + offsetY, width, height);
	}

	/**
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @param velX the velX to set
	 */
	public void setVelX(int velX) {
		this.velX = velX;
	}

	/**
	 * @param velY the velY to set
	 */
	public void setVelY(int velY) {
		this.velY = velY;
	}
}
