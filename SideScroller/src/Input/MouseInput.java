package Input;
import screens.Menu;
import screens.Options;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import libs.Audio;
import utils.AudioPlayer;
import enums.GameState;
import Main.Main;

public class MouseInput extends MouseAdapter 
{
	public static int MOUSE_X, MOUSE_Y;
	public static Rectangle MOUSE = new Rectangle(1,1,1,1);
	
	private Menu menu = Main.getInstance().getMenu();
	private Options options = Main.getInstance().getOptions();
	
	/**
	 * true if the mouse button is pressed
	 */
	public static boolean pressed = false;
	
	@Override
	/**
	 * This method is called when a mouse button is clicked
	 */
	public void mouseClicked(MouseEvent e) 
	{
		int mouse = e.getButton();
		Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1);
		pressed = true;
		
		if(mouse == MouseEvent.BUTTON1)
		{
			switch(Main.state)
			{
			case Game:
				break;
			case Menu:
				if(rect.intersects(menu.play))
				{
					AudioPlayer.playSound(Audio.MAIN_BUTTON);
					Main.state = GameState.Game;
				}
				else if(rect.intersects(menu.option))
				{
					AudioPlayer.getSound(Audio.MAIN_BUTTON).play();
					Main.state = GameState.Options;
				}
				else if(rect.intersects(menu.quit))
				{
					AudioPlayer.getSound(Audio.MAIN_BUTTON).play();
					if(AudioPlayer.getSound(Audio.MAIN_BUTTON).playing())
					{
						try {
							Thread.sleep(350);
							Main.getInstance().stop();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					else
						Main.getInstance().stop();
				}
				break;
			case Options:
				if(rect.intersects(options.back))
				{
					AudioPlayer.playSound(Audio.MAIN_BUTTON);
					Main.state = GameState.Menu;
				}
				break;
			case Pause:
				break;
			default:
				break;			
			}
			pressed = false;
		}	
	}
	@Override
	/**
	 * This called whenever the mouse button held down
	 */
	public void mousePressed(MouseEvent e) 
	{
		pressed = true;
	}
	
	@Override
	/**
	 * This called whenever the mouse button released
	 */
	public void mouseReleased(MouseEvent e) 
	{
		pressed = false;
	}
	
	@Override
	/**
	 * This method called when the mouse is moved
	 */
	public void mouseMoved(MouseEvent e) 
	{
		MOUSE_X = e.getX();
		MOUSE_Y = e.getY();
		MOUSE = new Rectangle(MOUSE_X, MOUSE_Y , 1, 1);
		
		switch(Main.state)
		{
		case Game:
			break;
		case Menu:
			if ((MOUSE.intersects(menu.play)||MOUSE.intersects(menu.option)||MOUSE.intersects(menu.quit))&&!AudioPlayer.hasPlayedHover)
			{
				AudioPlayer.playSound(Audio.MAIN_BUTTON);
				AudioPlayer.hasPlayedHover = true;
			}
			else if (!(MOUSE.intersects(menu.play)||MOUSE.intersects(menu.option)||MOUSE.intersects(menu.quit))&&AudioPlayer.hasPlayedHover)
				AudioPlayer.hasPlayedHover = false;
			break;
		case Options:
			if (MOUSE.intersects(options.back)&&!AudioPlayer.hasPlayedHover)
			{
				AudioPlayer.playSound(Audio.MAIN_BUTTON);
				AudioPlayer.hasPlayedHover = true;
			}
			else if (!MOUSE.intersects(options.back)&&AudioPlayer.hasPlayedHover)
				AudioPlayer.hasPlayedHover = false;
			break;
		case Pause:
			break;
		default:
			break;
		}
	}
}
