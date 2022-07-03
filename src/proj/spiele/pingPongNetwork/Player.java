package proj.spiele.pingPongNetwork;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import lib.awt.form.Rechteck;

/** Vaterklasse fuer linken und rechten Spieler */
public abstract class Player {
	/** Ball welcher hin und her springt */
	static Ball ball;
	/** Banden Links und Rechts */
	Rechteck bande;
	/** Socket fuer Serververbindung */
	Socket socket;
	/** Score des Players */
	int Score = 0;
	/** Score des Gegners */
	int OpponentScore = 0;
	/** Breite des Bildschirms */
	static int screenWidth;
	/** Hoehe des Bildschirms */
	static int screenHeight;
	/** Wenn der Ball das Spielfeld verlässt */
	boolean ballInRange = true;

	/** Wenn der Ball das Spielfeld verlässt in Richtung Gegner */
	abstract void isOutOfRange();

	/** Wenn der Ball das Spielfeld verlässt in Richtung OUT */
	abstract boolean isLost();

	/** Wenn der Ball eine Bande beruehrt */
	abstract boolean isBande();

	/** Wartet bis der Ball wieder vom Gegner zurueck kommt */
	abstract void isIncoming() throws IOException;

	/** Zeichnet das Spielfeld, den Ball und die Bande */
	abstract void paint(Graphics g);

	/** Bewegt die Bande nach oben bzw. nach unten */
	abstract void moveBande(int dy);

	/**
	 * Nach einer festgelegten Anzahl an Punkten wir eine Endanimation eingespielt
	 */
	abstract void endCard(Graphics g);

	/**
	 * Schreibt an den Server eine Nachricht als String
	 * 
	 * @param socket    client an welchen die Nachricht versendet wird
	 * @param nachricht string in welchem die Nachricht steht
	 * @throws IOException
	 */
	void writeMessage(Socket socket, String nachricht) throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		printWriter.print(nachricht);
		printWriter.flush();
	}

	/**
	 * Liest von einem client eine nachricht und speichert diese als String Uhrzeit
	 * und Absender der Nachricht wird in der Console ausgegeben
	 * 
	 * @param socket von welchem client gelesen werden soll
	 * @return nachricht als String
	 * @throws IOException
	 */
	String readMessage(Socket socket) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		char[] buffer = new char[200];
		int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
		String nachricht = new String(buffer, 0, anzahlZeichen);
		return nachricht;
	}
	
	public static void setScreenWidth(int sW) {
		screenWidth = sW;
		ball.setScreenWidth(sW);
	}
	public static void setScreenHeight(int sH) {
		screenWidth = sH;
		ball.setScreenHeight(sH);
	}
}
