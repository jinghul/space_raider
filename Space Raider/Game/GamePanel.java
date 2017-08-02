package Game;

import Menu.*;
import Sprites.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

//Starting screen is choose a ship;
//while gamePanel is notNew, same ship, set upgrades, when exit, erase all, set notNew = false;
public class GamePanel extends JPanel implements ActionListener{
	private int pWidth;
	private int pHeight;
	private Rectangle bounds;
	//Parameters
	public boolean fullscreen;
	private static final int DELAY = 10;
	public boolean inProgress;
	public boolean newGame = true;
	private int points;
	private Ship ship;
	private Timer timer;
	//References
	private Main parent;
	private VolumePanel vPanel;
	public ControlPanel controls;
	private Options options;
	//Components
	private ChoicePanel cPanel;
	private SpritePanel sPanel;
	private EndPanel ePanel;
	private JLabel choiceFrame;
	private JLabel background;
	private JLabel offscreen;
	private JLayeredPane layeredPane;
	
	private class TAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			controls.keyPressed(e);
		}
		public void keyReleased(KeyEvent e){
			controls.keyReleased(e);
		}
	}
	
	public GamePanel(Main parent){
		this.parent = parent;
		options  = parent.getOptions();
		vPanel  = options.getVPanel();
		controls = options.getCPanel();
		controls.setGPanel(this);
		init();
	}
	
	private void init(){
		setLayout(null);
		//layeredPane = new JLayeredPane();
		//add(layeredPane);
		
		sPanel = new SpritePanel(this);
		cPanel = new ChoicePanel(this);
		ePanel = new EndPanel(this);
		choiceFrame = new JLabel(new ImageIcon("Resources\\Backgrounds\\main.jpg"));
		background = new JLabel(new ImageIcon("Resources\\Backgrounds\\space.jpg"));
		offscreen = new JLabel(new ImageIcon("Resources\\Backgrounds\\space.jpg"));
		
		add(cPanel);
		add(choiceFrame);
		add(ePanel);
		add(sPanel);
		add(background);
		add(offscreen);
		
		ePanel.setVisible(false);
		sPanel.setVisible(false);
		
		initBounds(fullscreen?2:1);
		//initAliens??
		timer = new Timer(DELAY,this);
	}
	
	private void initBounds(int i){
		pWidth = parent.getWidth(); pHeight = parent.getHeight();
		bounds = (i == 1) ? new Rectangle(0,0,pWidth,pHeight) :
			new Rectangle(-3,-3,pWidth,pHeight);
		
		if(i == 1){
			choiceFrame.setBounds(-500, -400,choiceFrame.getIcon().getIconWidth(),
					choiceFrame.getIcon().getIconHeight());
		}else{
			choiceFrame.setBounds(0, 0,choiceFrame.getIcon().getIconWidth(),
					choiceFrame.getIcon().getIconHeight());
		}
		cPanel.setBounds(bounds);
		ePanel.setBounds(bounds);
		sPanel.setBounds(bounds);
		
		background.setBounds(0,0,background.getIcon().getIconWidth(),
				background.getIcon().getIconHeight());
		offscreen.setBounds(offscreen.getIcon().getIconWidth(), 0,
				offscreen.getIcon().getIconWidth(), offscreen.getIcon().getIconHeight());
		
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	//Instance Methods
	public void setShip(ImageIcon image){
		newGame = false;
		inProgress = true;
		
		ship = new Ship(image,100,pHeight/2);
		ship.setSpeed((fullscreen)?10:5);
		
		ImageIcon particle = (fullscreen) ? new ImageIcon("Resources\\Sprites\\Enlarged\\redlaser.png") :
			new ImageIcon("Resources\\Sprites\\smallredlaser.png");
		
		ship.setGun((fullscreen)?12:6,3,particle);
		ship.getGun().setTick(DELAY);
		ship.getGun().setVPanel(vPanel);
		Ship.pWidth = pWidth; Ship.pHeight = pHeight;
		controls.setShip(ship);
		sPanel.setShip(ship);
		
		cPanel.setVisible(false);
		choiceFrame.setVisible(false);
		sPanel.setVisible(true);
		
		revalidate();
		repaint();
		
		timer.start();
	}
	//Get References
	public Main getParent(){
		return parent;
	}
	
	public EndPanel getEPanel(){
		return ePanel;
	}
	
	public VolumePanel getVPanel(){
		return vPanel;
	}
	
	public Options getOptions(){
		return options;
	}
	
	public int getPoints(){
		return points;
	}
	
	public void addPoints(int p){
		points = points + p;
	}
	
	public void reset(){
		timer.stop();
		parent.reset();
	}
	
	//Window Size Changes
	public void windowChange(int i){
		if(i == 2) fullscreen = true;
		else fullscreen = false;
		initBounds(i);
		cPanel.windowChange(i);
	}
		
	private void moveBackground(){
		int speed = -2;
		if(background.getX() == -background.getWidth()) background.setBounds(background.getWidth(), 0, 
				background.getWidth(),background.getHeight());
		if(offscreen.getX() == -offscreen.getWidth()) offscreen.setBounds(offscreen.getWidth(), 0,
				offscreen.getWidth(), offscreen.getHeight());
		background.setBounds(background.getX()+ speed, 0,
				background.getWidth(),background.getHeight());
		offscreen.setBounds(offscreen.getX()+ speed,0,
				offscreen.getWidth(),offscreen.getHeight());
	}
	
	public void actionPerformed(ActionEvent e){
		moveBackground();
		sPanel.updateSprites();
	}
	
	//private void
}
