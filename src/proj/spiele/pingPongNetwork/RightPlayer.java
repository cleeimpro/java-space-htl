package proj.spiele.pingPongNetwork;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.Socket;

import lib.awt.form.Rechteck;

public class RightPlayer extends Player {

	public RightPlayer(int sW, int sH, Socket socket) {
		super();
		this.socket = socket;
		screenWidth = sW;
		screenHeight = sH;
		bande = new Rechteck(sW - 10, sH / 2, 20, 100);
		ball = null;
		isIncoming();
	}

	@Override
	void isOutOfRange() {
		if (ball.getMx() < 0) {
			ballInRange = false;
			try {
				writeMessage(socket, "c2:0:" + ball.getMy() + ":" + ball.getAngle() + ":" + OpponentScore);
			} catch (IOException e) {
				e.printStackTrace();
			}
			isIncoming();
		}
	}

	@Override
	void isIncoming() {
		try {
			String message[] = readMessage(socket).split(":");
			ball = new Ball(0, (int) (Double.parseDouble(message[2])), screenWidth, screenHeight);
			ball.setAngle(Integer.parseInt(message[3]));
			Score = Integer.parseInt(message[4]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ballInRange = true;
	}

	@Override
	boolean isLost() {
		if (ball.getMx() > screenWidth-bande.getSeiteA()) {
			OpponentScore++;
			ball = new Ball((int)bande.getMx(), (int)bande.getMy(), screenWidth, screenHeight);
			ball.setAngle((int) (Math.random() * 100 + 130));
			return true;
		}
		return false;
	}

	@Override
	boolean isBande() {
		if (ball.getMx()+ball.getR()>=bande.getMx()-bande.getSeiteA()/2&&
				ball.getMy()>bande.getMy()-bande.getSeiteB()/2&&
				ball.getMy()<bande.getMy()+bande.getSeiteB()/2) {
			ball.setAngle((int) (Math.random() * 100 + 130));
			return true;
		}
		return false;
	}

	@Override
	protected void moveBande(int dy) {
		bande.setMy(bande.getMy() + dy);
		if (bande.getMy() + bande.getSeiteB() / 2 >= screenHeight) {
			bande.setMy(screenHeight - bande.getSeiteB() / 2);
		} else if (bande.getMy() - bande.getSeiteB() / 2 < 0) {
			bande.setMy(bande.getSeiteB() / 2);
		}
		;
	}

	@Override
	void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(0, 22);
		g.setColor(Color.RED);
		g.fillRect(screenWidth - 20, 0, 20, screenHeight);
		g.setColor(Color.BLACK);
		g.drawRect(screenWidth / 2 - 50, 0, 100, 30);
		g.drawString(OpponentScore + "\t|\t" + Score, screenWidth / 2 - 20, 20);
			if (ballInRange)
				ball.moveBall();

			isOutOfRange();
			if (!isBande())
				isLost();

			bande.paint(g);
			if (ballInRange)
				ball.paint(g);
	}

	@Override
	void endCard(Graphics g) {
		// TODO Auto-generated method stub

	}

}
