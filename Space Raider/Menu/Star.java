package Menu;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Star extends JLabel{
	private static ImageIcon normal = new ImageIcon("Resources\\Decor\\star.png");
	private static ImageIcon enlarged = new ImageIcon("Resources\\Decor\\Enlarged\\star.png");	
	
	public Star(int i){
		if(i == 1) setIcon(enlarged);
		else setIcon(normal);
		setBackground(Color.WHITE);
	}
}
