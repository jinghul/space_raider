package Menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Menu extends JPanel implements ActionListener{	
	private int wWidth;
	private int wHeight;
	private Rectangle bounds;
	private static final int SPACING = 25;
	//Parameters
	private static Font windowed = new Font("Agency FB",Font.BOLD,70);
	private static Font fullscreen = new Font("Agency FB",Font.BOLD,140);
	private static ImageIcon wind = new ImageIcon("Resources\\Buttons\\base.png");
	private static ImageIcon full = new ImageIcon("Resources\\Buttons\\Enlarged\\base.png");
	//References
	private Main parent;
	private VolumePanel vPanel;
	//Components
	private JButton start;
	private JButton options;
	private JButton exit;
	private JLabel title;
	private Border border = new LineBorder(Color.WHITE,5);
	private Component titleSpace;
	private Component buttonSpace;
	private Component buttonSpace2;
	
	public Menu(Main parent){
		this.parent = parent;
		vPanel = parent.getOptions().getVPanel();
		init();
	}
	
	//Initializing
	private void init(){
		initBounds(1);
		setBackground(Color.BLACK);
		setOpaque(false);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//initialize components
		title = new JLabel(new ImageIcon("Resources\\Decor\\title.png"));
		title.setBorder(new LineBorder(new Color(175,175,175),10));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		start = new JButton("START",wind);
		start.setForeground(Color.BLACK);
		start.setFont(windowed);
		start.setHorizontalTextPosition(JButton.CENTER);
		start.setVerticalTextPosition(JButton.CENTER);
		start.addActionListener(this);
		start.setBorder(border);
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		options = new JButton("OPTIONS",wind);
		options.setForeground(Color.BLACK);
		options.setFont(windowed);
		options.setHorizontalTextPosition(JButton.CENTER);
		options.setVerticalTextPosition(JButton.CENTER);
		options.addActionListener(this);
		options.setBorder(border);
		options.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		exit = new JButton("EXIT",wind);
		exit.setForeground(Color.BLACK);
		exit.setFont(windowed);
		exit.setHorizontalTextPosition(JButton.CENTER);
		exit.setVerticalTextPosition(JButton.CENTER);
		exit.addActionListener(this);
		exit.setBorder(border);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		titleSpace = Box.createRigidArea(new Dimension(wWidth,60));
		buttonSpace = Box.createRigidArea(new Dimension(wWidth,SPACING));
		buttonSpace2 = Box.createRigidArea(new Dimension(wWidth,SPACING));
		
		addComponents();
	}
	
	private void addComponents(){
		add(title);
		add(titleSpace);
		add(start);
		add(buttonSpace);
		add(options);
		add(buttonSpace2);
		add(exit);
	}
	
	//Bounds
	private void initBounds(int i){
		wWidth = 1600*i; wHeight = 900*i;
		bounds = new Rectangle((parent.getWidth()-wWidth)/2,75*i,wWidth,wHeight);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	//Window Size Changes
	public void windowChange(int i){
		Font f = (i == 1) ? windowed : fullscreen;
		ImageIcon image = (i == 1) ? wind : full;
		
		initBounds(i);
		removeAll();
		
		titleSpace = Box.createRigidArea(new Dimension(wWidth,(int)(60*(3.0/2*i-1))));
		buttonSpace = Box.createRigidArea(new Dimension(wWidth,(int)(SPACING*(3.0/2*i-1))));
		buttonSpace2 = Box.createRigidArea(new Dimension(wWidth,(int)(SPACING*(3.0/2*i-1))));
		
		start.setFont(f);
		options.setFont(f);
		exit.setFont(f);
		
		title.setIcon((i == 1) ? new ImageIcon("Resources\\Decor\\title.png") :
			new ImageIcon("Resources\\Decor\\Enlarged\\title.png"));
		start.setIcon(image);
		options.setIcon(image);
		exit.setIcon(image);
		
		addComponents();
	}
	
	//Button Press Changes
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(start)) startSequence();
		if(e.getSource().equals(options)) optionSequence();
		if(e.getSource().equals(exit)) System.exit(0);
	}
	
	private void startSequence(){
		vPanel.playSound("startSound");
		setVisible(false);
		parent.createGame();
	}
	
	private void optionSequence(){
		vPanel.playSound("buttonSound");
		setVisible(false);
		parent.createOptions();
	}	
}
