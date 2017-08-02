package Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndPanel extends JPanel{
	//Play Again, Upgrades, High Scores, 
	//Exit To Menu - > menu.setVisible(true), inGame = false, newGame = true
	JLabel gameOver;
	JLabel score;
	JButton exitToMenu;
	GamePanel parent;
	
	public EndPanel(GamePanel parent){
		this.parent = parent;
		init();
	}
	
	private void init(){
		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		gameOver = new JLabel("GAME OVER");
		gameOver.setHorizontalTextPosition(JLabel.CENTER);
		gameOver.setForeground(Color.WHITE);
		gameOver.setFont(new Font("Agency FB",Font.BOLD,(parent.fullscreen)?400:200));
		gameOver.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		score = new JLabel("Score: "+ parent.getPoints());
		score.setForeground(Color.WHITE);
		score.setFont(new Font("Agency FB",Font.BOLD,(parent.fullscreen)?200:100));
		score.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		exitToMenu = new JButton((parent.fullscreen)?new ImageIcon("Resources\\Buttons\\Enlarged\\newbase.png"):
			new ImageIcon("Resources\\Buttons\\newbase.png"));
		exitToMenu.setText("EXIT TO MENU");
		exitToMenu.setForeground(Color.BLACK);
		exitToMenu.setFont(new Font("Agency FB",Font.BOLD,50));
		exitToMenu.setHorizontalTextPosition(JButton.CENTER);
		exitToMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				parent.setVisible(false);
				parent.getParent().getMenu().setVisible(true);
				parent.reset();
			}
		});
		exitToMenu.setBorder(new LineBorder(Color.WHITE,5));
		exitToMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(parent.fullscreen?Box.createRigidArea(new Dimension(40,200)): Box.createRigidArea(new Dimension(20,100)));
		add(gameOver);
		add(parent.fullscreen?Box.createRigidArea(new Dimension(40,200)): Box.createRigidArea(new Dimension(20,100)));
		add(score);
		add(parent.fullscreen?Box.createRigidArea(new Dimension(40,200)): Box.createRigidArea(new Dimension(20,100)));
		add(exitToMenu);
	}
	
	public void setVisible(boolean b){
		super.setVisible(b);
		score.setText("Score: "+ parent.getPoints());
	}
}
