package Menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BoxLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;

public class VolumePanel extends JPanel{
	private int pWidth;
	private int pHeight;
	private Rectangle bounds;
	//Variables
	private boolean sound = true;
	private double volumeChange;
	//Components
	private ImageIcon muted = new ImageIcon("Resources\\Buttons\\mute.png");
	private ImageIcon unmuted = new ImageIcon("Resources\\Buttons\\unmute.png");
	private JButton mute;
	private JSlider volumeMixer;
	
	public VolumePanel(){
		init();
	}
	
	public void init(){
		pWidth = 500; pHeight = 128;
		bounds = new Rectangle(Options.LEFT_BORDER, Options.TOP_BORDER, pWidth, pHeight);
		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		setBorder(new LineBorder(Color.WHITE,5));
		
		mute = new JButton(unmuted);
		mute.setBorder(new LineBorder(Color.BLACK,5));
		mute.setBackground(Color.WHITE);
		mute.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mute.setIcon(mute.getIcon() == unmuted ? muted : unmuted);
				if(mute.getIcon()==unmuted) sound = true;
				else sound = false;
			}
		});
		add(mute);
		
		volumeMixer = new JSlider(JSlider.HORIZONTAL,0,20,20);
		volumeMixer.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				volumeChange = 20 - volumeMixer.getValue();
			}
		});
		volumeMixer.setMajorTickSpacing(5);
		volumeMixer.setPaintTicks(true);
		volumeMixer.setBackground(Color.BLACK);
		add(volumeMixer);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	//Window Size Changes
	public void windowChange(int i){
		ImageIcon newMuted = (i == 1) ? new ImageIcon("Resources\\Buttons\\mute.png") :
			new ImageIcon("Resources\\Buttons\\Enlarged\\mute.png");
		ImageIcon newUnmuted = (i == 1) ? new ImageIcon("Resources\\Buttons\\unmute.png") :
			new ImageIcon("Resources\\Buttons\\Enlarged\\unmute.png");
		muted = newMuted;
		unmuted = newUnmuted;
		mute.setIcon((sound)?unmuted:muted);
		
		pWidth = (int)(pWidth*(3.0/2*i-1));
		pHeight = (int)(pHeight*(3.0/2*i-1));
		bounds = new Rectangle((int)(getX()*(3.0/2*i-1)),(int)(getY()*(3.0/2*i-1)),pWidth,pHeight);
	}
	
	//Play Sound
	public void playSound(String choice){
		String url = "";
		if(choice.equals("startSound")) url = "Resources\\Sound\\start.wav";
		if(choice.equals("buttonSound")) url = "Resources\\Sound\\button.wav";
		if(choice.equals("clickSound")) url = "Resources\\Sound\\click.wav";
		if(choice.equals("laserSound")) url = "Resources\\Sound\\laserSound.wav";
		if(choice.equals("blastSound")) url = "Resources\\Sound\\blastSound.wav";
		
		if(sound){
			try{
				AudioInputStream aIS = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(aIS);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-(float)volumeChange);
				clip.start();
			}catch (Exception ex){
				System.out.println("Sound Error");
				ex.printStackTrace();
			}
		}
	}
}
