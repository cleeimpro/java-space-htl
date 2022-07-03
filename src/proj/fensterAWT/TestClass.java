package proj.fensterAWT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import lib.awt.AwtButton;
import lib.awt.ColorFader;
import lib.awt.FensterAnimated;

public class TestClass extends FensterAnimated {
	
	private ColorFader cf;
	private AwtButton ab;
	
	public TestClass() {
		this.setSize(500, 600);
		this.setTitle("TestClass");
		cf = new ColorFader(10, 22+10, 200, 15, Color.RED,Color.BLUE);
		ab = new AwtButton(100,100,20,20);
		ab.setBackgroundColor(Color.RED);
		
		
		
		
		this.setVisible(true);
	}
	
	public void mousePressed(MouseEvent e) {
		cf.mousePressed(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		cf.mouseDragged(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		cf.mouseReleased(e);
	}
	
	@Override
	public void paint(Graphics g) {
		cf.paint(g);
		ab.paint(g);
	}

	public static void main (String[] args) {
		new TestClass();
	}
}
