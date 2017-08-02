package Customizable;

import javax.swing.ImageIcon;

public class Ammunition {
	private int speed;
	private int damage;
	private ImageIcon particle;
	
	public Ammunition(int speed, int damage, ImageIcon particle){
		this.speed = speed;
		this.damage = damage;
		this.particle = particle;
	}
	public int getSpeed(){
		return speed;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public ImageIcon getParticle(){
		return particle;
	}
}
