package controll;

import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;
import core.CoreObject;
import screens.Game;

public class Controller 
{
	Game game = new Game();
	
	private static CopyOnWriteArrayList<CoreObject> objects = new CopyOnWriteArrayList<CoreObject>();
	
	/**
	 * Runs the ticking operations for all the objects in the <code>ArrayList</code>
	 */
	public void tick()
	{
		for (CoreObject obj : objects)
			obj.tick();
	}
	
	/**
	 * Runs the rendering operations for all the objects in the <code>ArrayList</code>
	 */
	public void render (Graphics g)
	{
		for(CoreObject obj : objects)
			obj.render(g);
	}
	
	/**
	 * Adds a new <code>CoreObject</code> to the <code>ArrayList</code>
	 * @param instance a <code>CoreObject</code> instance
	 */
	public static void addObject(CoreObject instance)
	{
		objects.add(instance);
	}
	
	public static void removeObject(CoreObject instance)
	{
		objects.remove(instance);
	}
	
	public static CopyOnWriteArrayList<CoreObject> getObjects()
	{
		return objects;
	}
}
