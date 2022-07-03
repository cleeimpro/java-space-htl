package proj.spiele;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

import lib.awt.FensterAnimated;
import lib.awt.form.Kreis;

public class DropKreis extends FensterAnimated {
	
	private static final long serialVersionUID = -649063582032153050L;
	
	private int ANZAHL = 0;
	private int RUNDEN = 0;

	/** Laufende Kreise */
	Vector<Kreis> laufer;
	
	public DropKreis() {
		super();
		fillVector();
		this.setVisible(true);
	}
	
	public void fillVector() {
		laufer = new Vector<Kreis>();
		Random rand = new Random();
		ANZAHL = rand.nextInt(7)+1;
		
		int y = this.getHeight()/ANZAHL;
		
		laufer.add(new Kreis(this.getWidth()/2, 22, 10));
		laufer.get(0).setHintergrund(new Color
					(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
		for(int i = 1; i<ANZAHL;i++) {
			laufer.add(new Kreis(0,y*i,40));
			laufer.get(i).setHintergrund(new Color
					(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
			laufer.get(i).setSpeed((int)(6*(1+Math.random())));
		}
	}
	
	@Override
	public void paint(Graphics g) {		
		if(crashNot()) {
			for (Kreis k : laufer) {
				k.move(k.getSpeed(),0);
				if(k.getMx()>this.getWidth()) k.setMx(0);
		        k.paint(g);    
		    }	
			g.setColor(Color.BLACK);
			g.drawString("Runden: "+RUNDEN, 20, 60);
		}else {
			FensterAnimated.RUN = false;
			g.drawString("Du hast verloren! Looser!\n"+"Du hast "+RUNDEN+" Runde(n) geschafft!",this.getWidth()/2,this.getHeight()/2);
		}
	}
	
	public boolean crashNot() {
		for(int i = 1; i<laufer.size(); i++) {
			if((laufer.get(0).getMx()-laufer.get(i).getMx())*(laufer.get(0).getMx()-laufer.get(i).getMx())+
			(laufer.get(0).getMy()-laufer.get(i).getMy())*(laufer.get(0).getMy()-laufer.get(i).getMy()) <=
			(laufer.get(0).getR()+laufer.get(i).getR())*(laufer.get(0).getR()+laufer.get(i).getR())) 
				return false;
		}
		return true;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_DOWN: laufer.get(0).move(0,15); if(laufer.get(0).getMy()> this.getHeight()) {RUNDEN++; laufer.get(0).setMy(20);} break;
			case KeyEvent.VK_UP: laufer.get(0).move(0,-15); if(laufer.get(0).getMy()< 20) {laufer.get(0).setMy(20);} break;
			case KeyEvent.VK_ESCAPE: System.exit(0);break;
			case KeyEvent.VK_R: laufer.get(0).setMy(20); RUNDEN=0; fillVector(); FensterAnimated.RUN = true; break;
		}
		
	}
	
	public static void main(String[] args) {
		new DropKreis();
		
	}
	
	

}
