package Sprites;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class Sprite{
	//Position
	protected int x;
	protected int y;
	//Dimensions
	protected int width;
	protected int height;
	//Instance
	protected boolean visible;
	protected Image image;
	
	public Sprite(int x, int y){
		this.x = x;
		this.y = y;
		visible = true;
	}
	
	protected void loadImage(ImageIcon icon){
		image = icon.getImage();
		getImageDimensions();
	}
	
	protected void getImageDimensions(){
		width =  image.getWidth(null);
		height = image.getHeight(null);
	}
	
	public Image getImage(){
		return image;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setVisible(boolean vis){
		visible = vis;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}
}
