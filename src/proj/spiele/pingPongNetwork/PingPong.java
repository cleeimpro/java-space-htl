package proj.spiele.pingPongNetwork;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import lib.awt.FensterAnimated;

/** Erstellt ein neues PingPongSpiel */
public class PingPong extends FensterAnimated {

	/** Serial Version ID */
	private static final long serialVersionUID = -7471982068438108750L;

	/** Socket fuer Serververbindung */
	private Socket socket;

	/** Spieler (fuer Oberflache) */
	private Player player;

	/**
	 * Konstruktor
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public PingPong() throws UnknownHostException, IOException {
		super();
		String ip = JOptionPane.showInputDialog("ServerIP"); // Abfrage der ServerIP
		int port = 11111; // Vordefinierter Port
		socket = new Socket(ip, port); // Socket wird erstellt
		String firstConv[] = readMessage(socket).split(":"); // Wartet auf erste Nachricht

		if (firstConv[0].equals("s")) { // wenn die erste Nachricht vom Server kommt
			writeMessage(socket, "c1:c2"); // Nachricht fuer zweiten Client
			player = new LeftPlayer(this.getWidth(), this.getHeight(), socket); // Linker Spieler wird erstellt

		} else if (firstConv[0].equals("c1") && firstConv[1].equals("c2")) { // wenn die erste Nachricht vom ersten
																				// Client kommt
			writeMessage(socket, "c1:go"); // Nachricht fuer ersten Client zum Starten des Spiels
			player = new RightPlayer(this.getWidth(), this.getHeight(), socket); // Rechter Spieler wird erstellt
		}
		this.setVisible(true);// AWT-Fenster wird sichtbar gemacht
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			player.moveBande(-40);
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			player.moveBande(40);
			break;

		}
	}

//	@Override
//	public void componentResized(ComponentEvent e) {
//		//Player.setScreenWidth(this.getWidth());
//		//Player.setScreenHeight(this.getHeight());
//	}
	
	/**
	 * Liest von einem client eine nachricht und speichert diese als String Uhrzeit
	 * und Absender der Nachricht wird in der Console ausgegeben
	 * 
	 * @param socket von welchem client gelesen werden soll
	 * @return nachricht als String
	 * @throws IOException
	 */
	public void writeMessage(Socket socket, String nachricht) throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		printWriter.print(nachricht);
		printWriter.flush();
	}

	/**
	 * Schreibt an den Server eine Nachricht als String
	 * 
	 * @param socket    client an welchen die Nachricht versendet wird
	 * @param nachricht string in welchem die Nachricht steht
	 * @throws IOException
	 */
	public String readMessage(Socket socket) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		char[] buffer = new char[200];
		int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
		String nachricht = new String(buffer, 0, anzahlZeichen);
		return nachricht;
	}

	/** Zeichnet das Fenster und den Spieler neu */
	public void paint(Graphics g) {
		player.paint(g);
	}

	/**
	 * Startet das Fenster bzw. das Spiel
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		new PingPong();
	}

}
