package Game;

import Sprites.*;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Menu.ControlPanel;

import java.util.ArrayList;

public class SpritePanel extends JPanel{
	private Ship ship;
	private GamePanel parent;
	private ControlPanel controls;
	private ArrayList<Asteroid> asteroids;
	private final static int POPULATION = 100;
	
	public SpritePanel(GamePanel parent){
		this.parent = parent;
		controls = parent.controls;
		init();
	}
	
	private class TAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			controls.keyPressed(e);
		}
		public void keyReleased(KeyEvent e){
			controls.keyReleased(e);
		}
	}
	
	private void init(){
		addKeyListener(new TAdapter());
		setFocusable(true);
		setOpaque(false);
		initAsteroids(POPULATION,(parent.fullscreen)?2:1);
	}
	
	private void initAsteroids(int size, int full){
		asteroids = new ArrayList<Asteroid>();
		int rx; int ry; int speed;
		for(int i = 0; i < size; i ++){
			rx = (int)(Math.random()*(60*full))*50+3000*full;
			ry = (int)(Math.random()*(900*full));
			speed = (int)(Math.random()*4)+2;
			asteroids.add(new Asteroid(rx,ry,full,speed));
		}
	}
	public void setShip(Ship s){
		ship = s;
	}
	
	//Draw
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(parent.inProgress)drawObjects(g);
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void initEnd(){
		parent.getEPanel().setVisible(true);
		parent.inProgress = false;
	}
	
	private void drawObjects(Graphics g){
		if(ship.isVisible()){
			g.drawImage(ship.getImage(), ship.getX(), ship.getY(), this);
		}else{
			initEnd();
		}
		
		ArrayList<Laser> projectilesToDraw = ship.getGun().getProjectiles();
		for(Laser l : projectilesToDraw){
			if(l.isVisible()){
				g.drawImage(l.getImage(), l.getX(), l.getY(), this);
			}
		}
		ArrayList<Asteroid> asts = asteroids;
		for(Asteroid a: asteroids){
			if(a.isVisible()){
				g.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
		}
		
		//for enemies...
		g.setColor(Color.WHITE);
		g.setFont(new Font("Agency FB", Font.BOLD,(parent.fullscreen)?100:50));
		g.drawString("Score: " + parent.getPoints(),0,(parent.fullscreen)?120:60);
	}
	
	public void updateSprites(){
		parent.addPoints(3);
		ship.getGun().fireDelay();	
		
		updateShip();
		updateLasers();		
		updateAsteroids();
		
		checkCollisions();
		repaint();
	}
	
	private void updateShip(){
		if(ship.isVisible()) ship.move();
	}
	
	private void updateLasers(){
		ArrayList<Laser> projectiles = ship.getGun().getProjectiles();
		for(int i = 0; i < projectiles.size(); i++){
			Laser l = projectiles.get(i);
			if(l.isVisible()) l.move();
			else{
				projectiles.remove(i);			
				i--;
			}
		}
	}
	
	private void updateAsteroids(){
		for(int i = 0; i < asteroids.size(); i++){
			Asteroid a = asteroids.get(i);
			if(a.isVisible()){
				a.move();
			}else{
				asteroids.remove(i);
				i--;
			}
		}
	}
	
	private void checkCollisions(){
		Rectangle shipBounds = ship.getBounds();
		for(Asteroid a : asteroids){
			Rectangle aBound = a.getBounds();
			if(shipBounds.intersects(aBound)){
				ship.setVisible(false);
				a.setVisible(false);
			}
		}
		
		ArrayList<Laser> projectiles = ship.getGun().getProjectiles();
		for(Laser l: projectiles){
			Rectangle lBound = l.getBounds();
			for(Asteroid a : asteroids){
				Rectangle aBound = a.getBounds();
				if(lBound.intersects(aBound)){
					parent.getVPanel().playSound("blastSound");
					parent.addPoints(100);
					a.takeDamage(l.getDamage());
					l.setVisible(false);
				}
			}
		}
	}
	
	public void setVisible(boolean b){
		super.setVisible(b);
		if(b) requestFocus();
	}

}
