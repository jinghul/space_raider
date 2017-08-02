package Menu;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;

public class WindowPanel extends JPanel {
	private int pWidth;
	private int pHeight;
	private Rectangle bounds;
	//References
	private Main parent;
	private VolumePanel vPanel;
	//Instance
	private boolean locked;
	//Components
	private ImageIcon full;
	private ImageIcon windowd;
	private JButton fullscreen;
	private JLabel text;
	private Component rigidArea;
	
	public WindowPanel(VolumePanel vPanel,Main parent){
		this.parent = parent;
		this.vPanel = vPanel;
		init();
	}
	
	private void init(){
		pWidth = 500; pHeight  = 128;
		bounds = new Rectangle(Options.LEFT_BORDER,Options.TOP_BORDER+128+50,pWidth,pHeight);
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.WHITE,5));
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		//Buttons
		full = new ImageIcon("Resources\\Buttons\\fullscreen.png");
		windowd = new ImageIcon("Resources\\Buttons\\Enlarged\\windowed.png");
		fullscreen = new JButton(full);
		fullscreen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				vPanel.playSound("buttonSound");
				if(!locked){
					fullscreen.setIcon(fullscreen.getIcon()==full?windowd:full);
					if(fullscreen.getIcon()==windowd){
						text.setText("Windowed");
						SwingUtilities.invokeLater(new Runnable(){
							public void run(){
								parent.windowChange(2);
							}
						});
					}
					else{
						text.setText("Fullscreen");
						SwingUtilities.invokeLater(new Runnable(){
							public void run(){
								parent.windowChange(1);
							}
						});
					}
				}
			}
		});
		fullscreen.setBorder(new LineBorder(Color.BLACK,5));
		fullscreen.setBackground(Color.WHITE);
		
		//Text
		text = new JLabel("Fullscreen");
		text.setForeground(Color.BLACK);
		text.setFont(new Font("Agency FB",Font.BOLD,70));
		text.setHorizontalTextPosition(JLabel.CENTER);
		
		//Rigid Area Between 
		rigidArea = Box.createRigidArea(new Dimension(50,128));
		
		//Add Components
		addComponents();
	}
	
	private void addComponents(){
		add(fullscreen);
		add(rigidArea);
		add(text);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public void lock(){
		locked = true;
	}
	
	public void unlock(){
		locked = false;
	}
	
	//Window Size Changes
	public void windowChange(int i){
		pWidth = (int)(pWidth*(3.0/2*i-1));
		pHeight = (int)(pHeight*(3.0/2*i-1));
		bounds = new Rectangle((int)(getX()*(3.0/2*i-1)),(int)(getY()*(3.0/2*i-1)),pWidth,pHeight);
		
		removeAll();
		
		rigidArea =  Box.createRigidArea(new Dimension((int)(50*(3.0/2*i-1)), 128));
		text.setFont(new Font("Agency FB",Font.BOLD,(int)(70*i)));
		
		addComponents();
	}
}
