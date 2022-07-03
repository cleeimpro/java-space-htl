package proj.zeigerdiagramm;

import lib.*;
import lib.awt.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class PointerChart extends FensterAnimated{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int hlh = 22;
	
	private DrawArea drawArea1;
	
	public PointerChart() {
		this.setTitle("Pointer Chart");
		this.setSize(1280, 720 + hlh);
		
		this.drawArea1 = new DrawArea(this, 50, 50 + hlh, this.getWidth() - 100, this.getHeight() - 100 - hlh);
		//drawArea1.addVector(new Vector(new Complex(10,10),10,0));
		
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		drawArea1.paint(g);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		drawArea1.mouseDragged(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		drawArea1.mousePressed(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		drawArea1.mouseMoved(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		drawArea1.mouseReleased(e);
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		drawArea1.mouseWheelMoved(e);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		drawArea1.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		drawArea1.keyReleased(e);
	}

	public static void main(String[] args) {
		new PointerChart();

	}

}
