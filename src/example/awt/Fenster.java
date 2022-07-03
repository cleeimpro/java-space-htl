package example.awt;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Fenster extends Frame implements WindowListener, ComponentListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Fenster() {
		this.setTitle("Erstes Programm");
		this.setSize(800,600);
		this.setVisible(true);
		this.addWindowListener(this);
		this.addComponentListener(this);
	}
	
	public static void main(String[] args) {
		/*Scanner sc = new Scanner(System.in);
		
		System.out.print("Fenster (1)\nFenster (2)\nFenster (3)\nEingabe : ");
		int choose = sc.nextInt();
		switch(choose) {
			case 1: new Fenster(); break;
			case 2: new Fenster2();break;
			case 3: new Fenster3();break;
			default: System.out.println("ERROR");
		}
		
		sc.close();*/
		
		new Fenster4();
	}
	
	@Override
	public void paint(Graphics g) {
		int breite = this.getWidth();
		int hoehe = this.getHeight();
		for(int i = 1; i<10;i++) {
			g.drawLine(0, (hoehe-30)/10*i+30, breite, (hoehe-30)/10*i+30);
			g.drawLine(breite/10*i, 0, breite/10*i, hoehe);
		}
	}

	
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		this.repaint();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
