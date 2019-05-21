package p2p;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class P2P {

	public static void main (String args[]) throws IOException {
		
		URL urls = new URL();
		int n = urls.getLength();
		
		LinkedList<ClientListener> clientListener = new LinkedList<ClientListener>();
		ClientWriter clientWriter = new ClientWriter();
        clientWriter.start();
		
		for (int i = 0; i < n; i++) {
			System.out.println("Connecting to peer: " + i);
			Socket socket = new Socket(urls.getIp(i), urls.getPort(i));
			clientListener.add(new ClientListener(socket, clientWriter));
			clientListener.getLast().start();
		}
		
		System.out.println("Creating Server");
		ServerSocket server = new ServerSocket(urls.newPort());
		urls.writeOnFile();
		for (;;) {
			Socket socket = server.accept();
			System.out.println("\nClient connected");
			clientListener.add(new ClientListener(socket, clientWriter));
			clientListener.getLast().start();
		}
	}
	
}