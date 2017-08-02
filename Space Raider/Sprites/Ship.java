package Sprites;

import Customizable.*;
import javax.swing.ImageIcon;

public class Ship extends Sprite implements Harmable{
	public static int pWidth;
	public static int pHeight;
	//Harmable Interface
	private int health;
	//Sprite
	private Gun gun;
	private int speed = 2;
	private int dx;
	private int dy;
	
	public Ship(ImageIcon image,int x, int y){
		super(x, y);
		loadImage(image);
	}
	
	public void takeDamage(int damage){
		health = health - damage;
		if(health <= 0) setVisible(false);
	}
	
	public void setGun(int speed, int damage, ImageIcon particle){
		gun = new Gun(this,500);
		gun.setAmmunition(speed, damage, particle);
	}
	
	public Gun getGun(){
		return gun;
	}
	
	public void move(){
		x += dx;
		y += dy;
		if(x < 1) x = 1;
		if(x > pWidth - 100) x = pWidth - 100;
		if(y < 1) y = 1;
		if(y > pHeight - 100) y = pHeight - 100;
	}
	public void setSpeed(int i){
		speed = i;
	}
	
	public void actionPressed(String event){
		if(event.equals("up")) dy = -speed;
		if(event.equals("down")) dy = speed;
		if(event.equals("left")) dx = -speed;
		if(event.equals("right")) dx = speed;
		if(event.equals("fire")) gun.fire();
	}
	
	public void actionReleased(String event){
		if(event.equals("up")) dy = 0;
		if(event.equals("down")) dy = 0;
		if(event.equals("left")) dx = 0;
		if(event.equals("right")) dx = 0;
	}
}
