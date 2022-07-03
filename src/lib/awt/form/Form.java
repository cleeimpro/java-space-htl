package lib.awt.form;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Form {
	
	public Color color;
	
	public abstract void move(double dx, double dy);
	
	public abstract void paint(Graphics g);
	
	public abstract void setColor(Color c);

	
}
