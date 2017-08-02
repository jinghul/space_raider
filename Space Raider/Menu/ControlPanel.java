package Menu;

import Sprites.Ship;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.border.LineBorder;

import Game.GamePanel;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements ActionListener{
	private int pWidth;
	private int pHeight;
	private Rectangle bounds;
	//Reference
	private Options parent;
	private GamePanel gPanel;
	private Ship ship;
	//Controls
	private static final int[] keyBoard = new int[]{KeyEvent.VK_W,KeyEvent.VK_A,KeyEvent.VK_S,
			KeyEvent.VK_D,KeyEvent.VK_SPACE,KeyEvent.VK_ESCAPE};
	private static final int[] arrows = new int[]{KeyEvent.VK_UP,KeyEvent.VK_LEFT,KeyEvent.VK_DOWN,
			KeyEvent.VK_RIGHT,KeyEvent.VK_SPACE,KeyEvent.VK_ESCAPE};
	private int[] choice;
	//Components
	private JLabel title;
	private JButton close;
	private JPanel choices;
	private JButton wasd;
	private JButton directions;
	
	public ControlPanel(Options parent){
		this.parent = parent;
		init();
	}
	
	private void init(){
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.GRAY,10));
		setLayout(null);
		
		choice = arrows;
		
		title = new JLabel("CONTROLS");
		title.setBackground(Color.WHITE);
		title.setForeground(Color.BLACK);
		title.setFont(new Font("Agency FB",Font.BOLD,100));
		title.setHorizontalTextPosition(JLabel.CENTER);
		
		close = new JButton(new ImageIcon("Resources\\Buttons\\close.png"));
		close.addActionListener(this);
		close.setBackground(Color.WHITE);
		close.setBorder(new LineBorder(Color.BLACK,3));
		
		choices = new JPanel();
		choices.setLayout(new BoxLayout(choices,BoxLayout.X_AXIS));
		choices.setBorder(new LineBorder(Color.BLACK,5));
		
		wasd = new JButton("WASD",new ImageIcon("Resources\\Buttons\\wasdchoice.png"));
		wasd.setBackground(Color.WHITE);
		wasd.setFont(new Font("Agency FB",Font.BOLD,25));
		wasd.setHorizontalTextPosition(JButton.CENTER);
		wasd.setVerticalTextPosition(JButton.BOTTOM);
		
		directions = new JButton("Arrow Keys", new ImageIcon("Resources\\Buttons\\arrowschoice.png"));
		directions.setBackground(Color.WHITE);
		directions.setFont(new Font("Agency FB",Font.BOLD,25));
		directions.setHorizontalTextPosition(JButton.CENTER);
		directions.setVerticalTextPosition(JButton.BOTTOM);
		
		add(title);
		add(close);
		add(choices);
		
		choices.add(wasd);
		choices.add(directions);
		
		initBounds(1);
	}
	
	private void initBounds(int i){
		pWidth = 880*i;
		pHeight = 450*i;
		bounds =  new Rectangle(360*i,200*i,pWidth,pHeight);
		
		title.setBounds(260*i, 25*i, 500*i, 100*i);
		close.setBounds(pWidth-close.getIcon().getIconWidth()-16*i,16*i,
				close.getIcon().getIconWidth(),close.getIcon().getIconHeight());
		int j = (i == 1) ? 678 : 640;
		choices.setBounds(109*i,150*i,j*i,250*i);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public void setShip(Ship s){
		ship = s;
	}
	
	public void setGPanel(GamePanel g){
		gPanel = g;
	}
	
	//Window Size Change
	public void windowChange(int i){
		close.setIcon((i == 1) ? new ImageIcon("Resources\\Buttons\\close.png") :
			new ImageIcon("Resources\\Buttons\\Enlarged\\close.png"));
		wasd.setIcon((i == 1) ? new ImageIcon("Resources\\Buttons\\wasdchoice.png") :
			new ImageIcon("Resources\\Buttons\\Enlarged\\wasdchoice.png"));
		directions.setIcon((i == 1) ? new ImageIcon("Resources\\Buttons\\arrowschoice.png") :
			new ImageIcon("Resources\\Buttons\\Enlarged\\arrowschoice.png"));
		
		title.setFont(new Font("Agency FB",Font.BOLD, 100*i));
		wasd.setFont(new Font("Agency FB",Font.BOLD,25*i));
		directions.setFont(new Font("Agency FB",Font.BOLD,25*i));
		initBounds(i);
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == choice[0]) ship.actionPressed("up");
		if(key == choice[1]) ship.actionPressed("left");
		if(key == choice[2]) ship.actionPressed("down");
		if(key == choice[3]) ship.actionPressed("right");
		if(key == choice[4]) ship.actionPressed("fire");
		if(key == choice[5]) {
			parent.setVisible(true);
			gPanel.inProgress = false;
			gPanel.setVisible(false);
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == choice[0]) ship.actionReleased("up");
		if(key == choice[1]) ship.actionReleased("left");
		if(key == choice[2]) ship.actionReleased("down");
		if(key == choice[3]) ship.actionReleased("right");
		if(key == choice[4]) ship.actionReleased("fire");
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(close)){
			setVisible(false);
			parent.setVisible(true);
		}
		if(e.getSource().equals(directions)) choice = arrows;
		else choice = keyBoard;
	}
	
}
