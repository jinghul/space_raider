package Menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class HelpPanel extends JPanel{
	private int pWidth;
	private int pHeight;
	private Rectangle bounds;
	//Reference
	private Main parent;
	private Options container;
	//Components
	private JLabel title;
	private JLabel ship;
	private JLabel arrows;
	private JButton close;
	
	private JPanel shoot;
	private JLabel shipShoot;
	private JLabel shot1;
	private JLabel shot2;
	private JLabel press;
	private JLabel spacebar;
	
	private JTextArea shipDesc;
	private JTextArea arrowsDesc;
	private JTextArea shootDesc;
	
	public HelpPanel(Main parent, Options container){
		this.parent = parent;
		this.container = container;
		init();
	}
	
	private void init(){
		setBackground(new Color(250,250,250,100));
		setLayout(null);
		
		title = new JLabel("HELP");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Agency FB",Font.BOLD,100));
		title.setHorizontalAlignment(JLabel.CENTER);
		
		ship = new JLabel(new ImageIcon("Resources\\Sprites\\ship1.png"));
		
		arrows = new JLabel(new ImageIcon("Resources\\Decor\\arrows.png"));
		
		close = new JButton(new ImageIcon("Resources\\Buttons\\close.png"));
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				container.getVPanel().playSound("clickSound");
				setVisible(false);
				container.setVisible(true);
			}
		});
		close.setBackground(Color.WHITE);
		
		shoot = new JPanel();
		shoot.setLayout(null);
		shoot.setOpaque(false);
		
		shipShoot = new JLabel(new ImageIcon("Resources\\Sprites\\ship2.png"));
		shot1 = new JLabel(new ImageIcon("Resources\\Sprites\\redlaser.png"));
		shot2 = new JLabel(new ImageIcon("Resources\\Sprites\\redlaser.png"));
		spacebar = new JLabel(new ImageIcon("Resources\\Decor\\spacebar.png"));
		spacebar.setBorder(new LineBorder(Color.BLACK,5));
		
		press = new JLabel("Press");
		press.setForeground(Color.WHITE);
		press.setFont(new Font("Agency FB",Font.BOLD,50));
		
		shipDesc = new JTextArea("This is your spaceship.\nDon't crash it or let it get hit!");
		shipDesc.setBackground(new Color(250,250,250,0));
		shipDesc.setForeground(Color.WHITE);
		shipDesc.setFont(new Font("Agency FB",Font.BOLD,50));
		shipDesc.setHighlighter(null);
		shipDesc.setEditable(false);
		
		arrowsDesc = new JTextArea("Control your ship \nwith the arrow keys!");
		arrowsDesc.setBackground(new Color(250,250,250,0));
		arrowsDesc.setForeground(Color.WHITE);
		arrowsDesc.setFont(new Font("Agency FB",Font.BOLD,50));
		arrowsDesc.setHighlighter(null);
		arrowsDesc.setEditable(false);
		
		shootDesc = new JTextArea("Press SPACE to SHOOT");
		shootDesc.setBackground(new Color(250,250,250,0));
		shootDesc.setForeground(Color.WHITE);
		shootDesc.setFont(new Font("Agency FB",Font.BOLD,50));
		shootDesc.setHighlighter(null);
		shootDesc.setEditable(false);
		
		shoot.add(shipShoot);
		shoot.add(shot1);
		shoot.add(shot2);
		shoot.add(spacebar);
		shoot.add(press);
		
		add(title);
		add(ship);
		add(arrows);
		add(shoot);
		add(shipDesc);
		add(arrowsDesc);
		add(shootDesc);
		add(close);
		
		initBounds(1);
	}
	
	private void initBounds(int i){
		pWidth = 1170*i; pHeight = 700*i;
		bounds = new Rectangle((parent.getWidth()-pWidth)/2,(parent.getHeight()-pHeight)/2-50*i,
				pWidth,pHeight);
		
		title.setBounds(pWidth/2-100*i,0,200*i,100*i);
		ship.setBounds(100*i, 150*i, ship.getIcon().getIconWidth(), ship.getIcon().getIconHeight());
		arrows.setBounds(700*i, 250*i, arrows.getIcon().getIconWidth(), arrows.getIcon().getIconHeight());
		close.setBounds(pWidth-close.getIcon().getIconWidth()-4*i,4*i,
				close.getIcon().getIconWidth(),close.getIcon().getIconHeight());
		shoot.setBounds(100*i,300*i,600*i,400*i);
		
		shipShoot.setBounds(0, 4*i, shipShoot.getIcon().getIconWidth(), shipShoot.getIcon().getIconHeight());
		shot1.setBounds(140*i, 38*i, shot1.getIcon().getIconWidth(), shot1.getIcon().getIconHeight());
		shot2.setBounds(340*i, 38*i, shot2.getIcon().getIconWidth(), shot2.getIcon().getIconHeight());
		spacebar.setBounds(200*i, 155*i,spacebar.getIcon().getIconWidth()-10,
				spacebar.getIcon().getIconHeight()-10);
		press.setBounds(60*i,155*i,200*i,100*i);
		
		shipDesc.setBounds(270*i,150*i,700*i,200*i);
		arrowsDesc.setBounds(730*i,500*i,500*i,200*i);
		shootDesc.setBounds(160*i,575*i,500*i,100*i);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	//Window Size Change
	public void windowChange(int i){
		ship.setIcon((i == 1) ? new ImageIcon("Resources\\Sprites\\ship1.png") :
			new ImageIcon("Resources\\Sprites\\Enlarged\\ship1.png"));
		arrows.setIcon((i == 1) ? new ImageIcon("Resources\\Decor\\arrows.png") :
			new ImageIcon("Resources\\Decor\\Enlarged\\arrows.png"));
		close.setIcon((i == 1) ? new ImageIcon("Resources\\Buttons\\close.png") :
			new ImageIcon("Resources\\Buttons\\Enlarged\\close.png"));
		
		shipShoot.setIcon((i == 1) ? new ImageIcon("Resources\\Sprites\\ship2.png") :
			new ImageIcon("Resources\\Sprites\\Enlarged\\ship2.png"));
		shot1.setIcon((i == 1) ? new ImageIcon("Resources\\Sprites\\redlaser.png") :
			new ImageIcon("Resources\\Sprites\\Enlarged\\redlaser.png"));
		shot2.setIcon((i == 1) ? new ImageIcon("Resources\\Sprites\\redlaser.png") :
			new ImageIcon("Resources\\Sprites\\Enlarged\\redlaser.png"));
		spacebar.setIcon((i == 1) ? new ImageIcon("Resources\\Decor\\spacebar.png") :
			new ImageIcon("Resources\\Decor\\Enlarged\\spacebar.png"));
		
		title.setFont(new Font("Agency FB",Font.BOLD,100*i));
		press.setFont(new Font("Agency FB",Font.BOLD,50*i));
		shipDesc.setFont(new Font("Agency FB",Font.BOLD,50*i));
		arrowsDesc.setFont(new Font("Agency FB",Font.BOLD,50*i));
		shootDesc.setFont(new Font("Agency FB",Font.BOLD,50*i));
		
		initBounds(i);
	}
}
