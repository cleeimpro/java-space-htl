package proj.spiele.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

import lib.Site;
import lib.awt.form.Kreis;

public class Pacman {

	private int f;
	private int mx;
	private int my;
	private int r;
	/** HomeX */
	private int homeX;
	/** HomeX */
	private int homeY;
	private Site direction;
	private Vector<Kreis> dots;
	private int score = 0;
	private int life = 3;
	private Color c = Color.yellow;

	public Pacman(int mx, int my, int r, int f, Vector<Kreis> dots) {
		homeX = this.mx = mx;
		homeY = this.my = my;
		this.r = r;
		this.f = f;
		this.dots = dots;
		this.direction = Site.STOPP;
	}

	public void move(Vector<Point> v, Site d) {
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

		for (Point p : v) {
			if (p.getX() > mx && Math.abs(p.getX() - mx) < f && p.getY() == my) { // Rechts
				direction = Site.STOPP;
				mx = (int) p.getX() - f;
			} else if (p.getX() < mx && Math.abs(p.getX() - mx) < f && p.getY() == my) { // Links
				direction = Site.STOPP;
				mx = (int) p.getX() + f;
			} else if (p.getY() > my && Math.abs(p.getY() - my) < f && p.getX() == mx) { // Unten
				direction = Site.STOPP;
				my = (int) p.getY() - f;
			} else if (p.getY() < my && Math.abs(p.getY() - my) < f && p.getX() == mx) { // Oben
				direction = Site.STOPP;
				my = (int) p.getY() + f;
			}
		}
		
		for (Kreis k : dots) {
			if(mx+f == k.getMx() && my == k.getMy() && d == Site.RIGHT)
				direction = Site.RIGHT;	
			if(mx-f == k.getMx() && my == k.getMy() && d == Site.LEFT)
				direction = Site.LEFT;	
			if(mx == k.getMx() && my-f == k.getMy() && d == Site.UP)
				direction = Site.UP;	
			if(mx == k.getMx() && my+f == k.getMy() && d == Site.DOWN)
				direction = Site.DOWN;	
		}

	}

	public void eatDots() {
		for(Kreis k:dots) {
			if(mx+r >= k.getMx()+k.getR()&&mx-r <= k.getMx()-k.getR()&&my == k.getMy()&&k.isVisible()) {
				k.setVisible(false);
				score++;
			}
		}
	}

	public void paint(Graphics g) {
		eatDots();
		g.setColor(c);
		g.fillOval(mx - r, my - r, r*2, r*2);
	}

	public Site getDirection() {
		return direction;
	}

	public void setDirection(Site direction) {
		this.direction = direction;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
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
}
