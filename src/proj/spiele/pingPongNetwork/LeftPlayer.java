package proj.spiele.pingPongNetwork;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.Socket;

import lib.awt.form.Rechteck;

public class LeftPlayer extends Player {

	/**
	 * Konstruktor fuer linken Spieler
	 * @param sW Fensterbreite
	 * @param sH Fensterhoehe
	 * @param socket fuer Serververbindung
	 * @throws IOException
	 */
	public LeftPlayer(int sW, int sH, Socket socket) throws IOException {
		super();
		this.socket = socket;
		screenWidth = sW;
		screenHeight = sH;
		bande = new Rechteck(10, (sH - 22) / 2 + 22, 20, 100);
		ball = new Ball(20, sH / 2, sW, sH);
		String message[] = readMessage(socket).split(":");
	}

	/**
	 * Bewegt die Bande um den Ball abzufangen
	 */
	@Override
	public void moveBande(int dy) {
		bande.setMy(bande.getMy() + dy);
		if (bande.getMy() + bande.getSeiteB() / 2 > screenHeight) {
			bande.setMy(screenHeight - bande.getSeiteB() / 2);
		} else if (bande.getMy() - bande.getSeiteB() / 2 < 0) {
			bande.setMy(bande.getSeiteB() / 2);
		}
		;
	}

	/**
	 * checkt ob der Ball auf die Gegnerseite hinaus gerollt ist
	 */
	@Override
	void isOutOfRange() {
		if (ball.getMx() > screenWidth) {
			ballInRange = false;
			try {
				writeMessage(socket, "c1:0:" + ball.getMy() + ":" + ball.getAngle() + ":" + OpponentScore + ":\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			isIncoming();
		}
	}

	/**
	 * Wartet bis der andere Spieler den Ball zurueckschickt
	 */
	@Override
	void isIncoming() {
		try {
			String message[] = readMessage(socket).split(":");
			ball = new Ball(screenWidth, (int) (Double.parseDouble(message[2])), screenWidth, screenHeight);
			ball.setAngle(Integer.parseInt(message[3]));
			Score = Integer.parseInt(message[4]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ballInRange = true;
	}

	/**
	 * checkt ob der Ball ins OUT gerollt ist
	 */
	@Override
	boolean isLost() {
		if (ball.getMx() < bande.getSeiteA()) {
			OpponentScore++;
			ball = new Ball((int)bande.getMx(), (int)bande.getMy(), screenWidth, screenHeight);
			int x = (int) (Math.random() * 100);
			ball.setAngle(x < 50 ? x : 440 - x);
			return true;
		}
		return false;
	}

	/**
	 * checkt ob der Ball die Bande beruehrt
	 */
	@Override
	boolean isBande() {
		if (ball.getMx()-ball.getR()<=bande.getMx()+bande.getSeiteA()/2&&
				ball.getMy()>bande.getMy()-bande.getSeiteB()/2&&
				ball.getMy()<bande.getMy()+bande.getSeiteB()/2) {
			int x = (int) (Math.random() * 100);
			ball.setAngle(x < 50 ? x : 440 - x);
			return true;
		}
		return false;
	}

	/**
	 * Zeichnet das Spielfenster, den Ball, die Scoreanzeige und die Bande
	 */
	@Override
	void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(0, 22);
		g.setColor(Color.RED);
		g.fillRect(0, 0, 20, screenHeight);
		g.setColor(Color.BLACK);
		g.drawRect(screenWidth / 2 - 50, 0, 100, 30);
		g.drawString(Score + "\t|\t" + OpponentScore, screenWidth / 2 - 20, 20);
		if (ballInRange)
			ball.moveBall();
		if (!isBande())
			isLost();

		isOutOfRange();

		bande.paint(g);
		if (ballInRange)
			ball.paint(g);
	}

	/**
	 * Endanimation wird nach dem Spiel aufgerufen
	 */
	@Override
	void endCard(Graphics g) {
		// TODO Auto-generated method stub

	}

}
