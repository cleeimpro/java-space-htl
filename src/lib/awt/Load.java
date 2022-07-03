package lib.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import lib.awt.form.Kreis;

public class Load extends FensterAnimated{
	
	private int r;
	private double rot;
	
	public Load(int width, int height, int r) {
		this.setSize(width, height);
		this.r = r;
		rot = 0;
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		rot += (2*Math.PI)/255*3;
		Graphics2D g2 = (Graphics2D) g;
		
		g2.translate(this.getWidth()/2, this.getHeight()/2);
		g2.rotate(rot);
		for(int i = 0; i<=255; i++) {
			Kreis k = new Kreis(Math.cos((2*Math.PI)/255*i)*r, Math.sin((2*Math.PI)/255*i)*r,r/20);
			k.setColor(new Color(i,i,i));
			k.paint(g);
		}
		g2.rotate(-rot);
		g2.translate(-this.getWidth()/2, -this.getHeight()/2);
	}
	
	public static void main(String[] args) {
		new Load(500,500,50);
	}

}
