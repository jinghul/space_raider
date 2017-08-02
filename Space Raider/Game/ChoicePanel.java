package Game;

import Menu.VolumePanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class ChoicePanel extends JPanel implements ActionListener{
	//Parameters
	private ImageIcon choice;
	//Reference
	private GamePanel parent;
	private VolumePanel vPanel;
	//Components
	private JLabel text;
	private JPanel choicePanel;
	private JButton choice1;
	private JButton choice2;
	private JButton choice3;
	
	public ChoicePanel(GamePanel parent){
		this.parent = parent;
		vPanel = parent.getVPanel();
		init();
	}
	
	private void init(){
		setOpaque(false);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		text = new JLabel(" Choose Your Ship ");
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Agency FB",Font.BOLD,150));
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		choicePanel = new JPanel();
		choicePanel.setLayout(new BoxLayout(choicePanel,BoxLayout.X_AXIS));
		choicePanel.setBackground(Color.WHITE);
		choicePanel.setBorder(new LineBorder(Color.GRAY,15));
		
		choice1 = new JButton(new ImageIcon("Resources\\Buttons\\ship1.png"));
		choice1.addActionListener(this);
		choice1.setBackground(Color.WHITE);
		
		choice2 = new JButton(new ImageIcon("Resources\\Buttons\\ship2.png"));
		choice2.addActionListener(this);
		choice2.setBackground(Color.WHITE);
		
		choice3 = new JButton(new ImageIcon("Resources\\Buttons\\ship3.png"));
		choice3.addActionListener(this);
		choice3.setBackground(Color.WHITE);
		
		choicePanel.add(choice1);
		choicePanel.add(choice2);
		choicePanel.add(choice3);
		
		add(Box.createRigidArea(new Dimension(10,100)));
		add(text);
		add(Box.createRigidArea(new Dimension(10,75)));
		add(choicePanel);
	}
	
	public void windowChange(int i){
		text.setFont(new Font("Agency FB",Font.BOLD,150*i));
		choice1.setIcon((i == 1) ? new ImageIcon("Resources\\Buttons\\ship1.png"):
			new ImageIcon("Resources\\Buttons\\Enlarged\\ship1.png"));
		choice2.setIcon((i == 1) ? new ImageIcon("Resources\\Buttons\\ship2.png"):
			new ImageIcon("Resources\\Buttons\\Enlarged\\ship2.png"));
		choice3.setIcon((i == 1) ? new ImageIcon("Resources\\Buttons\\ship3.png"):
			new ImageIcon("Resources\\Buttons\\Enlarged\\ship3.png"));
		
	}
	
	public void actionPerformed(ActionEvent e){
		vPanel.playSound("buttonSound");
		if(e.getSource().equals(choice1)) choice = (parent.fullscreen) ? new ImageIcon("Resources\\Sprites\\Enlarged\\ship1.png") :
			new ImageIcon("Resources\\Sprites\\ship1.png");
		if(e.getSource().equals(choice2)) choice = (parent.fullscreen) ? new ImageIcon("Resources\\Sprites\\Enlarged\\ship2.png") :
			new ImageIcon("Resources\\Sprites\\ship2.png");
		if(e.getSource().equals(choice3)) choice = (parent.fullscreen) ? new ImageIcon("Resources\\Sprites\\Enlarged\\ship3.png") :
			new ImageIcon("Resources\\Sprites\\ship3.png");
		parent.setShip(choice);
	}
}
