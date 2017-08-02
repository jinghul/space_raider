package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Options extends JPanel{
	//Parameters
	public static final int LEFT_BORDER = 50;
	public static final int TOP_BORDER = 80;
	private static Font windowed = new Font("Agency FB",Font.BOLD,70);
	private static Font fullscreen = new Font("Agency FB",Font.BOLD,140);
	private static ImageIcon wind = new ImageIcon("Resources\\Buttons\\newbase.png");
	private static ImageIcon full = new ImageIcon("Resources\\Buttons\\Enlarged\\newbase.png");
	
	//Bounds;
	private int pWidth;
	private int pHeight;
	private Rectangle bounds;
	//Reference
	private Main parent;
	//Components
	private Border border;
	private JButton close;
	private JButton controls;
	private JButton help;
	private JButton exitToMenu;
	private JLabel background;
	//Panels;
	private DifficultyPanel dPanel;
	private HelpPanel hPanel;
	private VolumePanel vPanel;
	private WindowPanel wPanel;
	private ControlPanel cPanel;
	
	public Options(Main parent){
		this.parent = parent;
		init();
	}
	
	private void init(){
		setLayout(null);
		border = new LineBorder(Color.WHITE,5);
		
		//Button Initializing
		close = new JButton(new ImageIcon("Resources\\Buttons\\close.png"));
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				if(!parent.inGame) parent.getMenu().setVisible(true);
				else {
					parent.getGPanel().inProgress = true;
					parent.getGPanel().setVisible(true);
				}
			}
		});
		close.setBackground(Color.WHITE);
		close.setBorder(new LineBorder(Color.BLACK,5));
		
		controls = new JButton("CONTROLS",wind);
		controls.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				vPanel.playSound("buttonSound");
				cPanel.setVisible(true);
				setVisible(false);
			}
		});
		controls.setForeground(Color.BLACK);
		controls.setFont(windowed);
		controls.setHorizontalTextPosition(JButton.CENTER);
		controls.setBorder(new LineBorder(Color.WHITE,5));
		
		help = new JButton("HELP",wind);
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				vPanel.playSound("buttonSound");
				setVisible(false);
				hPanel.setVisible(true);
			}
		});
		help.setForeground(Color.BLACK);
		help.setFont(windowed);
		help.setHorizontalTextPosition(JButton.CENTER);
		help.setBorder(new LineBorder(Color.WHITE,5));
		
		exitToMenu = new JButton("EXIT TO MENU", wind);
		exitToMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				vPanel.playSound("buttonSound");
				setVisible(false);
				parent.getMenu().setVisible(true);
				if(parent.inGame){
					parent.inGame = false;
					parent.getGPanel().newGame = true;
					wPanel.unlock();
				}
			}
		});
		exitToMenu.setForeground(Color.BLACK);
		exitToMenu.setFont(windowed);
		exitToMenu.setHorizontalTextPosition(JButton.CENTER);
		exitToMenu.setBorder(new LineBorder(Color.WHITE,5));
		
		//Panel Initializing
		vPanel = new VolumePanel();
		wPanel = new WindowPanel(vPanel,parent);
		dPanel = new DifficultyPanel(vPanel);
		
		cPanel = new ControlPanel(this);
		cPanel.setVisible(false);
		hPanel = new HelpPanel(parent,this);
		hPanel.setVisible(false);
		
		background = new JLabel(new ImageIcon("Resources\\Backgrounds\\optionsPanel.jpg"));
		background.setBorder(border);
		
		//Add Components
		add(close);
		add(controls);
		add(help);
		add(exitToMenu);
		add(vPanel);
		add(wPanel);
		add(dPanel);
		parent.add(cPanel);
		parent.add(hPanel,new Integer(3),0);
		add(background);
		
		//Set Bounds
		initBounds(1);
	}
	
	private void initBounds(int i){
		pWidth = 1100*i; pHeight = 650*i;
		bounds = new Rectangle(300*i,75*i,pWidth,pHeight);
		
		vPanel.setBounds(vPanel.getBounds());
		wPanel.setBounds(wPanel.getBounds());
		cPanel.setBounds(cPanel.getBounds());
		dPanel.setBounds(dPanel.getBounds());
		hPanel.setBounds(hPanel.getBounds());
		
		close.setBounds(pWidth-close.getIcon().getIconWidth()-5,5,
				close.getIcon().getIconWidth(),close.getIcon().getIconHeight());
		controls.setBounds((vPanel.getX()+vPanel.getWidth())+35,
				vPanel.getY(),controls.getIcon().getIconWidth(),
				controls.getIcon().getIconHeight());
		help.setBounds(wPanel.getX()+wPanel.getWidth()+35,wPanel.getY(),
				help.getIcon().getIconWidth(), help.getIcon().getIconHeight());
		exitToMenu.setBounds(dPanel.getX()+dPanel.getWidth()+35,dPanel.getY(),
				exitToMenu.getIcon().getIconWidth(),exitToMenu.getIcon().getIconHeight());
		
		background.setSize(pWidth,pHeight);
	}
	
	//Get Parameters
	public Rectangle getBounds(){
		return bounds;
	}
	
	public HelpPanel getHPanel(){
		return hPanel;
	}
	
	public WindowPanel getWPanel(){
		return wPanel;
	}
	
	public DifficultyPanel getDPanel(){
		return dPanel;
	}
	
	public ControlPanel getCPanel(){
		return cPanel;
	}
	
	public VolumePanel getVPanel(){
		return vPanel;
	}
	
	@Override
	public void setVisible(boolean b){
		super.setVisible(b);
	}
	//Window Size Changes
	public void windowChange(int i){
		Font f = (i == 1) ? windowed : fullscreen;
		ImageIcon image = (i == 1) ? wind : full;
		
		close.setIcon((i==1) ? new ImageIcon("Resources\\Buttons\\close.png") :
			new ImageIcon("Resources\\Buttons\\Enlarged\\close.png"));
		controls.setIcon(image);
		help.setIcon(image);
		exitToMenu.setIcon(image);
		
		controls.setFont(f);
		help.setFont(f);
		exitToMenu.setFont(f);
		
		vPanel.windowChange(i);
		wPanel.windowChange(i);
		dPanel.windowChange(i);
		hPanel.windowChange(i);
		cPanel.windowChange(i);
		initBounds(i);
	}
}
