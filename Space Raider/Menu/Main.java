package Menu;

import Game.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

//From hence on 1 = Windowed, 2 = Fullscreen;
public class Main extends JFrame implements ActionListener{
	public boolean inGame;
	//Bounds
	private int wWidth = 1600;
	private int wHeight = 900;
	private Dimension bounds;
	private boolean fullscreen;
	//Components
	private JLayeredPane layeredPane;
	private JLabel background;
	//References
	private Menu menu;
	private Options options;
	private GamePanel gPanel;
	
	public Main(){
		initUI();
	}
	
	private void initUI(){
		bounds = new Dimension(wWidth,wHeight);
		setSize(bounds);
		setResizable(false);
		setTitle("Space Raiders");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		options = new Options(this);
		background = new JLabel(new ImageIcon("Resources\\Backgrounds\\main.jpg"));
		menu = new Menu(this);
		gPanel = new GamePanel(this);
		
		add(menu,new Integer(1));
		add(background,new Integer(0));
		
		//External Panels
		add(options,new Integer(3),1);	
		add(gPanel,new Integer(2),0);
		options.setVisible(false);
		gPanel.setVisible(false);
		
		initBounds(1);
	}
	
	private void initBounds(int i){
		menu.setBounds(menu.getBounds());
		if(i == 1){
			background.setBounds(-250,-400,background.getIcon().getIconWidth(),
					background.getIcon().getIconHeight());
		}else{
			background.setBounds(0,0,background.getIcon().getIconWidth(),
					background.getIcon().getIconHeight());
		}
		options.setBounds(options.getBounds()); 
		gPanel.setBounds(gPanel.getBounds());
	}
	
	//Get Parameters	
	
	public Menu getMenu(){
		return menu;
	}
	
	public Options getOptions(){
		return options;
	}
	
	public GamePanel getGPanel(){
		return gPanel;
	}
	
	public void reset(){
		remove(gPanel);
		gPanel = new GamePanel(this);
		gPanel.setVisible(false);
		add(gPanel,new Integer(2),0);
		gPanel.setBounds(0,0,wWidth,wHeight);
		gPanel.windowChange(fullscreen?2:1);
	}
	
	//Shows Options
	public void createOptions(){
		options.setVisible(true);
	}
	
	public void createGame(){
		inGame = true;
		gPanel.setVisible(true);
		options.getWPanel().lock();
	}
	
	//Window Size Changes
	public void windowChange(int i){
		wWidth = (int)(wWidth*(3.0/2*i-1));
		wHeight = (int)(wHeight*(3.0/2*i-1));
		bounds = new Dimension(wWidth,wHeight);
		
		if(i == 2){
			fullscreen = true;
			setExtendedState(JFrame.MAXIMIZED_BOTH);
		}else{
			fullscreen = false;
			setSize(bounds);
		}
		
		options.windowChange(i);
		menu.windowChange(i);
		gPanel.windowChange(i);
		initBounds(i);
		options.setBounds(options.getBounds());
		
		revalidate();
		repaint();
	}
	
	//Within LayeredPanel
	public void changePanelPosition(Options c, Integer layer, int position){
		layeredPane.setLayer(c,layer,position);
		revalidate();
		repaint();
	}
	
	//Unused
	public void actionPerformed(ActionEvent e){
	}
	
	//Main Method
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				Main frame = new Main();
				frame.setVisible(true);
			}
		});
	}
}
