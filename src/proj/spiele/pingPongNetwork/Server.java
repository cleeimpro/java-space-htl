package proj.spiele.pingPongNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	
	/** Port des Servers, dieser wird vordefiniert */
	private static int port = 11111;
	/** IP Adresse des Servers/PCs, diese wird vom Netzwerk zugeteilt*/
	private static InetAddress ip = null;
	private static ServerSocket serverSocket;
	private static Server server;
	private static Socket client1;
	private static Socket client2;

 public static void main(String[] args) throws IOException {
	try {
		serverSocket = new ServerSocket(port);
		server = new Server();
	} catch (IOException e1) {e1.printStackTrace();}
	
	try {
		ip = InetAddress.getLocalHost();
	} catch (UnknownHostException e2) {e2.printStackTrace();}
	
	System.out.println("IP-Adresse des Servers: " + ip.getHostAddress() +":"+serverSocket.getLocalPort());	
	
	System.out.println("Warte auf ersten Spieler");
	try {
		client1 = warteAufAnmeldung(serverSocket); // Wait for First Player
	} catch (IOException e1) {e1.printStackTrace();}
	writeMessage(client1, "s:c1");
	
	System.out.println("Warte auf zweiten Spieler");
	try {
		client2 = warteAufAnmeldung(serverSocket); // Wait for Second Player
	} catch (IOException e1) {e1.printStackTrace();}
	
	
	
	while(true) {
		try {
		    server.checkConnection();
		} catch (IOException e) {
		    e.printStackTrace();
		} 
	}
 }
 
 /** 
  * Leitet die Nachrichten abwechslent von client1 zu client2 und zurück
  * @throws IOException
  */
 public void checkConnection() throws IOException {
	 String nachricht = readMessage(client1);
	 writeMessage(client2, nachricht);

	 nachricht = readMessage(client2);
	 writeMessage(client1, nachricht);

 }

 /**
  * Wird aufgerufen um einen neuen Client am Server anzumelden
  * @param serverSocket über welchen Socket verbunden wird
  * @return new client
  * @throws IOException
  */
 public static Socket warteAufAnmeldung(ServerSocket serverSocket) throws IOException {
	Socket socket = serverSocket.accept(); // blockiert, bis sich ein Client angemeldet hat
	System.out.println(socket.getInetAddress() + " hat sich angemeldet");
	return socket;
 }

 /**
  * Liest von einem client eine nachricht und speichert diese als String
  * Uhrzeit und Absender der Nachricht wird in der Console ausgegeben
  * @param socket von welchem client gelesen werden soll
  * @return nachricht als String
  * @throws IOException
  */
 public String readMessage(Socket socket) throws IOException {
	SimpleDateFormat date=new SimpleDateFormat("HH:mm:ss");
    String uhrzeit=date.format(new Date());
    int anzahlZeichen=0;
     
	BufferedReader bufferedReader = 
	    new BufferedReader(
	 	new InputStreamReader(
		    socket.getInputStream()));
	char[] buffer = new char[200];
	anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
	String nachricht = new String(buffer, 0, anzahlZeichen);
	
	System.out.println(uhrzeit+" | "+socket.getInetAddress()+" > "+nachricht);
	return nachricht;
	
 }

	/**
	 * Schreibt an den Server eine Nachricht als String
	 * 
	 * @param socket    client an welchen die Nachricht versendet wird
	 * @param nachricht string in welchem die Nachricht steht
	 * @throws IOException
	 */
 public static void writeMessage(Socket socket, String nachricht) throws IOException {

		PrintWriter printWriter =
		    new PrintWriter(
		        new OutputStreamWriter(
		 	    socket.getOutputStream()));
		printWriter.print(nachricht);
		printWriter.flush();
		
 }
}