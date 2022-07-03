package proj.spiele.pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import lib.Site;
import lib.awt.FensterAnimated;
import lib.awt.form.Kreis;

/**
 * Pacman Hauptprogramm mit Spieloberfläche
 * 
 * @author Freudenthaler Clemens
 * @version 1.0
 * @date 04.06.2019
 */
public class PacmanMain extends FensterAnimated {

	private Vector<Kreis> dots;
	private Vector<Point> fillRects;
	private final int dotRadius = 4;
	private final int fieldSize = 20; // Muss durch 2 teilbar sein
	private final int playgroundBorderX = 20;
	private final int playgroundBorderY = 42;
	private int[] sites;
	private Site tempDirect = Site.STOPP;
	private Font scoreboardFont;
	private int startDelay = 2000;

	private Pacman pm;
	private Vector<Ghost> ghosts;

	/** serial Version UID (ignorieren nur zum fehlerlosen Programmieren) */
	private static final long serialVersionUID = 1L;

	public PacmanMain() {
		this.setTitle("Pacman V1.0");
		dots = new Vector<Kreis>();
		ghosts = new Vector<Ghost>();
		sites = providePlayground();
		this.setSize((sites[0] + 1) * fieldSize + 2 * playgroundBorderX,
				(sites[1] + 1) * fieldSize + 2 * playgroundBorderY - 22);
		scoreboardFont = new Font("CenturyGothicStandart", Font.BOLD, 12);
		
		this.setVisible(true);
	}

	/**
	 * Erstellt den Hintergrund aus Punkten und Rechtecken
	 * 
	 * @return wie viele Punkte und Rechteck erzeugt werden sollen
	 */
	public int[] providePlayground() {
		/* Punkte erstellen aus Datei ANFANG */
		File fdots = new File("./files/pacmanDots.xml");
		FileReader fr = null;
		Vector<String> input = new Vector<String>();
		int anz;
		char buffer[] = new char[10];
		String temp = "";
		int sites[] = { 0, 0 };
		try {
			fr = new FileReader(fdots);
			
			while ((anz = fr.read(buffer)) > 0) {
				for (int i = 0; i < anz; i++) {

					char z = buffer[i];

					if (z == '\n') {
						input.add(temp);
						if (temp.indexOf("end") >= 0)
							break;
						temp = "";
					} else
						temp += z;
				}
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		for (int i = 0; i < input.size()-1; i++) { // Zeilen werden durchlaufen
			String arr[] = input.get(i).split(","); // Zeile wird gesplittet
			for (int j = 0; j < arr.length; j++) { // Spalten werden durchlaufen
				if (arr[j].indexOf("-") >= 0) { //
					String arr2[] = arr[j].split("-");
					if (arr2[arr2.length-1].indexOf(":") >= 0) {
						String arr3[] = arr2[arr2.length-1].split(":");
						if (arr3[1].indexOf("0") >= 0) {
							for (int k = Integer.parseInt(arr2[0]); k <= Integer.parseInt(arr3[0]); k++) {
								dots.add(new Kreis(playgroundBorderX + fieldSize * k, playgroundBorderY + fieldSize * (i + 1),
										dotRadius));
									dots.get(dots.size() - 1).setVisible(false);
								if (k > sites[0])
									sites[0] = k;
							}
						}
							
					}else {
						for (int k = Integer.parseInt(arr2[0]); k <= Integer.parseInt(arr2[1]); k++) {
							dots.add(new Kreis(playgroundBorderX + fieldSize * k,
									playgroundBorderY + fieldSize * (i + 1), dotRadius));
							
							if (k > sites[0])
								sites[0] = k;
						}
					}

				} else if (arr[j].indexOf(":") >= 0) {
					String arr2[] = arr[j].split(":");
					dots.add(new Kreis(playgroundBorderX + fieldSize * Integer.parseInt(arr2[0]),
							playgroundBorderY + fieldSize * (i + 1), dotRadius));
					if (arr2[1].indexOf("0") >= 0)
						dots.get(dots.size() - 1).setVisible(false);
				} else if (arr[j].indexOf("pacman") >= 0) {
					String arr2[] = input.get(++i).split("::");
					pm = new Pacman(Integer.parseInt(arr2[1])*fieldSize+playgroundBorderX, Integer.parseInt(arr2[0])*fieldSize+playgroundBorderY, (int) (fieldSize * 0.4),
							 fieldSize,dots);
				} else if (arr[j].indexOf("ghost") >= 0) {
					String arr2[] = input.get(++i).split("::");
					ghosts.add(new Ghost(Integer.parseInt(arr2[1])*fieldSize+playgroundBorderX, Integer.parseInt(arr2[0])*fieldSize+playgroundBorderY, fieldSize));
				} else {
					dots.add(new Kreis(playgroundBorderX + fieldSize * (Integer.parseInt(arr[j])),
							playgroundBorderY + fieldSize * (i + 1), dotRadius));
					if (Integer.parseInt(arr[j]) > sites[0])
						sites[0] = Integer.parseInt(arr[j]);
				}
			}
		}
		sites[1] = input.size() - 2;
		/* Punkte erstellen ENDE */

		/* Rechtecke in Leerfeldern erzeugen */
		fillRects = new Vector<Point>();
		for(int i = 0; i < sites[0]+2; i++) {
			fillRects.add(new Point(i*fieldSize + playgroundBorderX, playgroundBorderY));
			fillRects.add(new Point(i*fieldSize + playgroundBorderX, playgroundBorderY+fieldSize*sites[1]));
		}
		
		for(int i = 0; i < sites[1]; i++) {
			fillRects.add(new Point(playgroundBorderX,i*fieldSize+playgroundBorderY));
			fillRects.add(new Point(playgroundBorderX+fieldSize*(sites[0]+1),i*fieldSize+playgroundBorderY));
		}	
		
		boolean match = false;
		for (int i = 1; i <= sites[1]; i++) {
			for (int j = 1; j <= sites[0]; j++) {
				match = false;
				for (Kreis k : dots) {
					if (k.getMx() == j * fieldSize + playgroundBorderX
							&& k.getMy() == i * fieldSize + playgroundBorderY) {
						match = true;
					}
				}
				if (!match)
					fillRects.add(new Point(j * fieldSize + playgroundBorderX, i * fieldSize + playgroundBorderY));
			}
		}

		return sites;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP: tempDirect=Site.UP;
			break;
		case KeyEvent.VK_DOWN: tempDirect=Site.DOWN;
			break;
		case KeyEvent.VK_LEFT: tempDirect=Site.LEFT;
			break;
		case KeyEvent.VK_RIGHT: tempDirect=Site.RIGHT;
			break;
		}
		
		
		
	}
	
	public void catchPm() {
		for(Ghost g : ghosts)
			if(Math.abs(g.getMx()-pm.getMx())<g.getR()+pm.getR()&&Math.abs(g.getMy()-pm.getMy())<g.getR()+pm.getR()) {
				for(Ghost g1 : ghosts) {
					g1.setMx(g1.getHomeX());
					g1.setMy(g1.getHomeY());
				}
				pm.setMx(pm.getHomeX());
				pm.setMy(pm.getHomeY());
				pm.setLife(pm.getLife()-1);
			}
	}
	
	
	@Override
	public void paint(Graphics g) {
		startDelay-=20;
		// Allgemeiner Hintergrund
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight()); 
		// Spielflaechen Hintergrund
	//	g.setColor(Color.orange);
	//	g.fillRect(playgroundBorderX+fieldSize/2+1, playgroundBorderY+fieldSize/2+1, sites[0]*fieldSize, sites[1]*fieldSize);
		
		if(pm.getDirection()==Site.STOPP)
			pm.setDirection(tempDirect);

		// Zeichnet alle Felder welche nicht befahren werden koennen
		for (Point p : fillRects) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect((int) (p.getX() - fieldSize / 2), (int) (p.getY() - fieldSize / 2), fieldSize, fieldSize);
		}
		// Zeichnet alle Felder mit Punkten
		for (Kreis k : dots)
			k.paint(g);
		
		// Checkt ob Pacman gefressen wurde
		catchPm();
	
		// Zeichnet Pacman und bewegt ihn
		pm.move(fillRects,tempDirect);
		pm.paint(g);
		
		// Zeichnet die Geister
		for(Ghost gh : ghosts) {
			if(startDelay<=0)
				gh.ki(dots, fillRects, pm);
			gh.paint(g);
		}
		
		//Scoreboard
		g.setColor(Color.BLACK);
		g.setFont(scoreboardFont);
		g.drawString("Score: "+pm.getScore(), playgroundBorderX-fieldSize/2, this.getHeight()-(playgroundBorderY-22)/2-6+fieldSize/2);
		g.setColor(pm.getC());
		for(int i = 0; i<pm.getLife(); i++)
			g.fillOval(this.getWidth()-playgroundBorderX-i*fieldSize,this.getHeight()-(playgroundBorderY-22)/2-pm.getR()*2+fieldSize/2,pm.getR()*2,pm.getR()*2);
//		Toolkit.getDefaultToolkit().sync();
	}

	public static void main(String[] args) {
		new PacmanMain();
	}
}
