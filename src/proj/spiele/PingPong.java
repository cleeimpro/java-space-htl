package proj.spiele;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import lib.awt.FensterAnimated;
import lib.awt.form.Kreis;
import lib.awt.form.Rechteck;

public class PingPong extends FensterAnimated{

	/** Serial Version ID */
	private static final long serialVersionUID = -7471982068438108750L;
	
	public static int leftScore = 0;
	public static int rightScore = 0;
	
	/** Ball welcher hin und her springt */
	Kreis ball;
	/** Banden Links und Rechts */
	Rechteck bande1, bande2;
	
	
	/** Konstruktor */
	public PingPong() {
		super();
		ball = new Kreis(this.getWidth()/2,(this.getHeight()-22)/2+22,10);
		ball.setAngle((int) (Math.random()*100 - 50));
		bande1 = new Rechteck(5,(this.getHeight()-22)/2+22,10,100);
		bande2 = new Rechteck(this.getWidth()-5,(this.getHeight()-22)/2+22,10,100);
		this.setVisible(true);
	}
	
	/** Bewegt den Ball im Richtigen Winkel */
	public void moveBall(int d) {
		ball.move((int)(d*Math.cos(Math.toRadians(ball.getAngle()))),-(int)(d*Math.sin(Math.toRadians(ball.getAngle()))));
	}
	
	public boolean touchTheBorder() {
		if(ball.getMy()<22+ball.getR()||ball.getMy()>this.getHeight()-ball.getR()) {
			ball.setAngle(-ball.getAngle());
			return true;
		}
		return false;
	}
	
	public boolean touchTheBande() {
		if(	ball.getR()+bande1.getSeiteA()/2>=Math.abs(ball.getMx()-bande1.getPosition().getX())&&
			ball.getR()+bande1.getSeiteB()/2>=Math.abs(ball.getMy()-bande1.getPosition().getY())){
			ball.setAngle((int) (Math.random()*100 - 50));
			return true;
		}
		else if(ball.getR()+bande2.getSeiteA()/2>=Math.abs(-ball.getMx()+bande2.getPosition().getX())&&
				ball.getR()+bande2.getSeiteB()/2>=Math.abs(ball.getMy()-bande2.getPosition().getY())) {
			ball.setAngle((int) (Math.random()*100 + 130));
			return true;
		}
		return false;
	}
	
	public boolean touchTheOut() {
		if(ball.getMx()<0) {
			rightScore++;
			ball.setAngle((int) (Math.random()*100 - 110));
			ball.setPoint(bande1.getPosition());
			return true;
		}
		else if(ball.getMx()>this.getWidth()) {
			leftScore++;
			ball.setAngle((int) (Math.random()*100 + 130));
			ball.setPoint(bande2.getPosition());
			return true;
		}
		
		
		return false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE: System.exit(0); break;
			
			case KeyEvent.VK_W: bande1.move(0,-20); 		if(bande1.getPosition().getY()<22+bande1.getSeiteB()/2) bande1.setMy(22+bande1.getSeiteB()/2); break;
			case KeyEvent.VK_S: bande1.move(0,20);		if(bande1.getPosition().getY()>this.getHeight()-bande1.getSeiteB()/2) bande1.setMy(this.getHeight()-bande1.getSeiteB()/2);break;
			case KeyEvent.VK_UP: bande2.move(0,-20);		if(bande2.getPosition().getY()<22+bande2.getSeiteB()/2) bande2.setMy(22+bande2.getSeiteB()/2); break;
			case KeyEvent.VK_DOWN: bande2.move(0,20);	if(bande2.getPosition().getY()>this.getHeight()-bande2.getSeiteB()/2) bande2.setMy(this.getHeight()-bande2.getSeiteB()/2);break;
		
		}
	}
	
	/** Zeichnet das Fenster neu*/
	public void paint(Graphics g) {
		g.drawLine(this.getWidth()/2, 0, this.getWidth()/2,this.getHeight());
		g.drawString(leftScore+"", this.getWidth()/3, 22+50);
		g.drawString(rightScore+"", this.getWidth()/3*2, 22+50);
		moveBall(8);
		
		boolean outOrNot;
		
		outOrNot=!touchTheBorder();
		outOrNot=!touchTheBande();
		if(outOrNot)
			if(touchTheOut())
				;
		ball.paint(g);
		bande1.paint(g);
		bande2.paint(g);
	}
	
	/** Startet das Fenster*/
	public static void main(String[] args) {
		new PingPong();	
	}
	
	

}
