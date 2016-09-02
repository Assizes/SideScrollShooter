package Main;

import entity.Boss;
import entity.Level1Enemy;
import entity.Player;
import enums.GameState;
import gfx.Renderer;
import gfx.Textures;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import controll.Controller;
import libs.Audio;
import libs.Identities;
import libs.Reference;
import Input.KeyboardInput;
import Input.MouseInput;
import screens.Game;
import screens.Menu;
import screens.Options;
import utils.AudioPlayer;
import utils.ResourceLoader;
    
public class Main extends Canvas implements Runnable, ActionListener
{
	public static JFrame frame = new JFrame();
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 16 * 9;
	private static final String TITLE = "Side Scroller";
	private static Main main = new Main();
	public static GameState state = GameState.Menu;
	
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Image cursor = toolkit.getImage(Reference.SPRITE_LOCATION + "cursor.gif");
	public boolean switched = false;
	public static boolean bossDestroed = false;
	
	public boolean running = false;
	private Thread thread;
	private Renderer gfx;
	private Menu menu;
	private Options options;
	private Game game;
	private Controller controller = new Controller();
	
	private Textures tex;
	
	/**
	 * Used to access the Main class <i>non-static members</i>
	 * @return the instance of the main
	 */
	public static Main getInstance()
	{
		return main;
	}
	
	public Menu getMenu()
	{
		return menu;
	}
	
	public Options getOptions()
	{
		return options;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public Controller getController()
	{
		return controller;
	}
	
	/**
	 * Acts as the constructor for the main
	 * This initializes the game objects, resources, listeners, etc
	 */
	private void init()
	{
		ResourceLoader.loadImages();
		ResourceLoader.loadFonts();
		ResourceLoader.loadSounds();
		tex = new Textures();
		menu = new Menu();
		options = new Options();
		game = new Game();
		gfx = new Renderer();
		MouseInput mouse = new MouseInput();
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		
		Controller.addObject(new Player(100, 100, Identities.PLAYER, tex));
		this.addKeyListener(new KeyboardInput());
		
		int x = 1000;
		int y = 50;
		for(int i = 0; i < 10; i++)
		{
			Controller.addObject(new Level1Enemy(x, y, Identities.ENEMY, tex));
			y += 50;
			x += 200;
		}
		Controller.addObject(new Boss(x, 200, Identities.BOSS, tex));
		
		AudioPlayer.playMusic(Audio.MUSIC_BACK);
	}
	
	/**
	 * updates the objects in the game
	 * This also runs the logic in the game
	 */
	private void tick()
	{
		if (state == GameState.Game)
			controller.tick();
	}

	/**
	 * This renders the graphics for the game
	 * This method uses a triple buffering strategy
	 */
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		gfx.renderBackground(g);
		gfx.renderForeground(g);
		
		if (Main.state == GameState.Game && !switched)
		{
			frame.setCursor(toolkit.createCustomCursor(new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ), new Point(), null));
			switched = true;
		}
		else if((Main.state == GameState.Menu || Main.state == GameState.Options) && switched)
		{
			frame.setCursor(toolkit.createCustomCursor(cursor, new Point(0, 0), "cursor"));
			switched = false;
		}
		
		g.dispose();
		bs.show();
	}
	@Override
	public void run() 
	{
		init();
		long lastTime = System.nanoTime();
		final double numTicks = 60.0;
		double n = 1000000000 / numTicks;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		long timer = System.currentTimeMillis();
		
		while(running)
		{
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / n;
			lastTime = currentTime;
			
			if (delta >= 1)
			{
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				frame.setTitle(TITLE + "      Ticks: " + ticks + "      FPS: " + frames);
				ticks = 0;
				frames = 0;
			}
		}
	}
    
	public static void main(String[] args) 
	{	
		Image icon = toolkit.getImage(Reference.SPRITE_LOCATION + "icon.png");      
		frame.add(main);
		frame.setTitle(TITLE);
		frame.setIconImage(icon);
		frame.setCursor(toolkit.createCustomCursor(cursor, new Point(frame.getX(), frame.getY()), "cursor"));
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		main.start();
	}
	
	private synchronized void start()
	{
		if(running)
			return;
		else
			running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running)
			return;
		else
			running = false;
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		
	}
}
