package Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import libs.Identities;
import controll.Controller;
import core.CoreObject;
import Main.Main;
import entity.Player;
import enums.GameState;

public class KeyboardInput extends KeyAdapter
{
	private Player player;
	public boolean[] keyDown = new boolean[4];	

	public KeyboardInput() 
	{
		for (CoreObject obj : Controller.getObjects())
			if (obj.getId() == Identities.PLAYER)
				player = (Player) obj;
	}
	
	/**
	 * Used to initialize attributes in the <code>KeyInput</code> class
	 */
	@Override
	public void keyPressed (KeyEvent e)
	{
		int key = e.getKeyCode();
		switch(Main.state)
		{
		case Game:
			if (key == KeyEvent.VK_ESCAPE)
				Main.state = GameState.Menu;
			if (key == KeyEvent.VK_W){
				player.setVelY(-10);
				keyDown[0] = true;
			}
			if(key == KeyEvent.VK_S){
				player.setVelY(10);
				keyDown[1] = true;
			}
			if (key == KeyEvent.VK_A){
				player.setVelX(-8);
				keyDown[2] = true;
			}
			if(key == KeyEvent.VK_D){
				player.setVelX(8);
				keyDown[3] = true;
			}
			if(key == KeyEvent.VK_SPACE){
				player.spaceKey = true;
			}

			break;
		case Menu:
			break;
		case Options:
			break;
		case Pause:
			break;
		default:
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		switch(Main.state)
		{
		case Game:
			if (key == KeyEvent.VK_W)
				keyDown[0] = false;
			if(key == KeyEvent.VK_S)
				keyDown[1] = false;
			if (key == KeyEvent.VK_A)
				keyDown[2] = false;
			if(key == KeyEvent.VK_D)
				keyDown[3] = false;
			if(key == KeyEvent.VK_SPACE)
				player.spaceKey = false;
			
			if(keyDown[0] && !keyDown[1])
				player.setVelY(-10);
			if(!keyDown[0] && keyDown[1])
				player.setVelY(10);
			if(!keyDown[0] && !keyDown[1])
				player.setVelY(0);
			
			if(keyDown[2] && !keyDown[3])
				player.setVelX(-8);
			if(!keyDown[2] && keyDown[3])
				player.setVelX(8);
			if(!keyDown[2] && !keyDown[3])
				player.setVelX(0);
			break;
		case Menu:
			break;
		case Options:
			break;
		case Pause:
			break;
		default:
			break;
		}
	}
	
}
