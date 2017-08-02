package Sprites;

import javax.swing.ImageIcon;

public class Asteroid extends Sprite implements Harmable{
	private int speed;
	private ImageIcon reg = new ImageIcon("Resources\\Sprites\\asteroid.png");
	private ImageIcon large = new ImageIcon("Resources\\Sprites\\Enlarged\\asteroid.png");
	private int health = 6;
	
	public Asteroid(int x,int y,int i, int speed){
		super(x,y);
		this.speed = speed;
		if(i == 1) loadImage(reg);
		else loadImage(large);
	}
	
	@Override
	protected void getImageDimensions(){
		super.getImageDimensions();
		width = width - 60;
		height = height - 60;
	}
	
	public void takeDamage(int damage){
		health = health - damage;
		if(health <= 0) setVisible(false);
	}
	
	public void move(){
		x+= -speed;
	}
}
