package proj.spiele.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

import lib.Site;
import lib.awt.form.Kreis;

public class Ghost {
	
	/** X Position */
	private int mx;
	/** Y Position */
	private int my;
	/** HomeX */
	private int homeX;
	/** HomeX */
	private int homeY;
	/** Radius */
	private int r;
	/** Gefaerhlich oder nicht */
	private boolean attention = true;
	/** feldBreite */
	private int f;
	/** Farbe des Geistes */
	private Color c;
	/** Aktuelle Richtung */
	private Site direction = Site.UP;
	/** Richtung vom Geist zum Pacman */
	private Site pmD;
	
	public Ghost(int mx, int my, int f) {
		this.homeX = this.mx = mx;
		this.homeY = this.my = my;
		this.f = f;
		this.r = (int) (f/2*0.8);
		switch((int)(Math.random()*4)+1) {
		case 1: c = Color.RED;
			break;
		case 2: c = Color.GREEN;
			break;
		case 3: c = Color.CYAN;
			break;
		case 4: c = Color.MAGENTA;
			break;
		default: c = Color.BLACK;
			break;
		}
	}
	
	public void ki(Vector<Kreis> dots,Vector<Point> v,Pacman pm) {
		switch (direction) {
		case DOWN:
			my += 4;
			break;
		case LEFT:
			mx -= 4;
			break;
		case RIGHT:
			mx += 4;
			break;
		case UP:
			my -= 4;
			break;
		case STOPP:
			break;
		}
		
		if((direction == Site.UP || direction == Site.DOWN || direction == Site.STOPP) && pm.getMx()>=mx)
			pmD = Site.RIGHT;
		else if((direction == Site.UP || direction == Site.DOWN || direction == Site.STOPP) && pm.getMx()<=mx)
			pmD = Site.LEFT;
		else if((direction == Site.LEFT || direction == Site.RIGHT || direction == Site.STOPP) && pm.getMy()<=my)
			pmD = Site.UP;
		else if((direction == Site.LEFT || direction == Site.RIGHT || direction == Site.STOPP) && pm.getMy()>=my)
			pmD = Site.DOWN;

		for (Point p : v) {
			if (p.getX() > mx && Math.abs(p.getX() - mx) < f && p.getY() == my) { // Rechts
				switch((int)(Math.random()*3+1)) {
				case 1: direction = Site.LEFT; break;
				case 2: direction = Site.UP;	break;
				case 3: direction = Site.DOWN;break;
				}
				mx = (int) p.getX() - f;
			} else if (p.getX() < mx && Math.abs(p.getX() - mx) < f && p.getY() == my) { // Links
				switch((int)(Math.random()*3+1)) {
				case 1: direction = Site.RIGHT;break;
				case 2: direction = Site.UP;break;
				case 3: direction = Site.DOWN;break;
				}
				mx = (int) p.getX() + f;
			} else if (p.getY() > my && Math.abs(p.getY() - my) < f && p.getX() == mx) { // Unten
				switch((int)(Math.random()*3+1)) {
				case 1: direction = Site.LEFT;break;
				case 2: direction = Site.UP;break;
				case 3: direction = Site.RIGHT;break;
				}
				my = (int) p.getY() - f;
			} else if (p.getY() < my && Math.abs(p.getY() - my) < f && p.getX() == mx) { // Oben
				switch((int)(Math.random()*3+1)) {
				case 1: direction = Site.LEFT;break;
				case 2: direction = Site.RIGHT;break;
				case 3: direction = Site.DOWN;break;
				}
				my = (int) p.getY() + f;
			}
		}
		
		for (Kreis k : dots) {
			if(mx+f == k.getMx() && my == k.getMy() && pmD == Site.RIGHT)
				direction = Site.RIGHT;	
			if(mx-f == k.getMx() && my == k.getMy() && pmD == Site.LEFT)
				direction = Site.LEFT;	
			if(mx == k.getMx() && my-f == k.getMy() && pmD == Site.UP)
				direction = Site.UP;	
			if(mx == k.getMx() && my+f == k.getMy() && pmD == Site.DOWN)
				direction = Site.DOWN;	
		}

	}
	
	public void paint(Graphics g) {
		g.setColor(c);
		g.fillRect(mx-r, my-r, r*2,r*2);
	}

	public int getMx() {
		return mx;
	}

	public void setMx(int mx) {
		this.mx = mx;
	}

	public int getMy() {
		return my;
	}

	public void setMy(int my) {
		this.my = my;
	}

	public int getHomeX() {
		return homeX;
	}

	public void setHomeX(int homeX) {
		this.homeX = homeX;
	}

	public int getHomeY() {
		return homeY;
	}

	public void setHomeY(int homeY) {
		this.homeY = homeY;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

}
