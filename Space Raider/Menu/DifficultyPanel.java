package Menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class DifficultyPanel extends JPanel implements ActionListener{
	private int pWidth;
	private int pHeight;
	private Rectangle bounds;
	//Parameters
	private static int border = 1;
	private static int dLevel = 2;
	private int full;
	private String[] difficulties;
	//Reference
	private VolumePanel vPanel;
	//Components
	private JButton up;
	private JButton down;
	private JLabel difficulty;
	private JPanel buttons;
	private JPanel stars;
	//private GamePanel gamePanel;
	
	public DifficultyPanel(VolumePanel vPanel){
		this.vPanel = vPanel;
		init();
	}
	
	private void init(){
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.WHITE,5));
		setLayout(null);
		
		//Buttons
		up = new JButton(new ImageIcon("Resources\\Buttons\\up.png"));
		up.addActionListener(this);
		up.setBackground(Color.WHITE);
		up.setBorder(new LineBorder(Color.BLACK,2));
		down = new JButton(new ImageIcon("Resources\\Buttons\\down.png"));
		down.addActionListener(this);
		down.setBackground(Color.WHITE);
		down.setBorder(new LineBorder(Color.BLACK,2));
		
		buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		buttons.setLayout(null);
		
		//Stars
		stars = new JPanel();
		stars.setBackground(Color.WHITE);
		stars.setLayout(new BoxLayout(stars,BoxLayout.X_AXIS));
		
		//Text
		difficulties = new String[]{"Easy","Normal","Intermediate","Hard","Impossible"};
		difficulty = new JLabel("Normal",SwingConstants.CENTER);
		difficulty.setBackground(Color.WHITE);
		difficulty.setForeground(Color.BLACK);
		difficulty.setFont(new Font("Agency FB",Font.BOLD,70));
		
		//Add Components
		buttons.add(up);
		buttons.add(down);
		add(buttons); 
		for(int i = 0; i < dLevel; i++) stars.add(new Star(full));
		add(stars);
		add(difficulty);
		
		//Bounds
		initBounds(1);
	}
	
	private void initBounds(int i){
		pWidth = 500*i; pHeight = 130*i;
		bounds = new Rectangle(Options.LEFT_BORDER*i,(Options.TOP_BORDER+128*2+50*2)*i,pWidth,pHeight);
		up.setBounds(border*i,border*i,up.getIcon().getIconWidth(),up.getIcon().getIconHeight());
		down.setBounds(border*i,(64+border)*i,up.getIcon().getIconWidth(),
				down.getIcon().getIconHeight());
		buttons.setBounds(0,0,(64+border)*i,pHeight);
		stars.setBounds(125*i,0,pWidth-100*i,64*i);
		difficulty.setBounds(100*i, 60*i, pWidth-125*i,pHeight-60*i);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	//Window Size Change
	public void windowChange(int i){
		full = i - 1;
		stars.removeAll();
		
		up.setIcon((i == 1) ? new ImageIcon("Resources\\Buttons\\up.png") :
			new ImageIcon("Resources\\Buttons\\Enlarged\\up.png"));
		down.setIcon((i == 1) ? new ImageIcon("Resources\\Buttons\\down.png") :
			new ImageIcon("Resources\\Buttons\\Enlarged\\down.png"));
		difficulty.setFont(new Font("Agency FB",Font.BOLD,(int)(70*i)));
		
		for(int j = 0; j < dLevel; j++) stars.add(new Star(full));
		
		initBounds(i);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(up)){
			if(dLevel == 5){
				dLevel = 1;
				stars.removeAll();
			}
			else{
				dLevel++;
			}
			stars.add(new Star(full));
		}
		if(e.getSource().equals(down)){
			if(dLevel == 1){
				dLevel = 5;
				for(int i = 1; i < dLevel;i++)stars.add(new Star(full));
			}
			else {
				dLevel--;
				stars.remove(dLevel);
			}
		}
		vPanel.playSound("clickSound");
		difficulty.setText(difficulties[dLevel-1]);
		revalidate();
		repaint();
	}
}
