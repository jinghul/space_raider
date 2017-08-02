package Sprites;

import Customizable.Ammunition;

public class Laser extends Sprite{
	public static int pWidth;
	public static int pHeight;
	private Ammunition ammo;
	private int dx;
	private int dy;
	
	public Laser(int x,int y, Ammunition ammo){
		super(x,y);
		pWidth = Ship.pWidth;
		pHeight = Ship.pHeight;
		setAmmunition(ammo);
	}
	
	public void setAmmunition(Ammunition ammo){
		this.ammo = ammo;
		loadImage(ammo.getParticle());
		dx = ammo.getSpeed();	
	}
	
	public int getDamage(){
		return ammo.getDamage();
	}
	
	public void move(){
		x = x + dx;
		if(x > pWidth) visible = false;
	}
}
