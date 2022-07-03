package example.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {
	
	private static int port = 11111;
	private static ServerSocket serverSocket;
	private static Server server;
	private static Socket client;

 public static void main(String[] args) {
	try {
		serverSocket = new ServerSocket(port);
		server = new Server();
		client = warteAufAnmeldung(serverSocket);
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	while(true) {
		try {
		    server.checkConnection();
		} catch (IOException e) {
		    e.printStackTrace();
		} 
	}
 }
 
 public void checkConnection() throws IOException {
	 //Socket client = warteAufAnmeldung(serverSocket);
	 String nachricht = leseNachricht(client);
	 System.out.println(nachricht);
	 schreibeNachricht(client, nachricht);
 }

 public static Socket warteAufAnmeldung(ServerSocket serverSocket) throws IOException {
	Socket socket = serverSocket.accept(); // blockiert, bis sich ein Client angemeldet hat
	System.out.println(socket.getInetAddress() + " hat sich angemeldet");
	return socket;
 }

 public String leseNachricht(Socket socket) throws IOException {
	BufferedReader bufferedReader = 
	    new BufferedReader(
	 	new InputStreamReader(
		    socket.getInputStream()));
	char[] buffer = new char[200];
	int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
	String nachricht = new String(buffer, 0, anzahlZeichen);
	return nachricht;
 }

 public void schreibeNachricht(Socket socket, String nachricht) throws IOException {
	PrintWriter printWriter =
	    new PrintWriter(
	        new OutputStreamWriter(
	 	    socket.getOutputStream()));
	printWriter.print(nachricht);
	printWriter.flush();
 }
}