package Customizable;

import Sprites.Ship;
import Sprites.Laser;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Menu.VolumePanel;

public class Gun{
	private int tick;
	private int delay;
	private VolumePanel vPanel;
	//private int spread;
	private int fireSpeed;
	private boolean locked;
	private Ship wielder;
	private Ammunition ammo;
	private ArrayList<Laser> projectiles;
	
	public Gun(Ship wielder, int speed){
		this.wielder = wielder;
		//spread = 1;
		fireSpeed = speed;
		projectiles = new ArrayList<Laser>();
	}
	
	public void setVPanel(VolumePanel vp){
		vPanel = vp;
	}
	
	public void setAmmunition(int speed, int damage, ImageIcon particle){
		ammo = new Ammunition(speed,damage,particle);
	}
	
	public void setFireSpeed(int speed){
		fireSpeed  = speed;
	}
	
	private void lock(){
		locked = true;
	}
	
	private void unlock(){
		locked = false;
	}
	
	public void fire(){
		if(!locked){
			vPanel.playSound("laserSound");
			projectiles.add(new Laser(wielder.getX()+wielder.getWidth(),
					wielder.getY()+wielder.getHeight()/2-ammo.getParticle().getIconHeight()/2,ammo));
			lock();
		}
	}
	
	public void fireDelay(){
		if(locked){
			delay++;
			if(delay == fireSpeed/tick){
				unlock();
				delay = 0;
			}
		}
	}
	
	public void setTick(int i){
		tick = i;
	}
	
	public ArrayList<Laser> getProjectiles(){
		return projectiles;
	}
}
