package proj.fensterAWT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import lib.awt.Ball;
import lib.awt.FensterAnimated;

public class SpringenderBall extends FensterAnimated{

	private static final long serialVersionUID = 1L;
	
	Ball b;
	String fbS = "./img/BasketballIcon.png" ;
	
	public SpringenderBall() {
		this.setTitle("Springender Ball");
		Toolkit tk = getToolkit();
		Image fb = tk.getImage(fbS);
		b = new Ball(20,300,300,0.3,-0.1,Color.RED,fb);	
		this.setVisible(true);
		
	}
	
	@Override
	public void paint(Graphics g) {
		b.calcPhysics(20);
		b.calcReflex(this.getWidth(),this.getHeight());
		b.paint(g);
	}

	public static void main(String[] args) {
		new SpringenderBall();
	}

}
