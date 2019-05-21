package p2p;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientListener extends Thread{

	Socket socket;
	Scanner in;
	
	public ClientListener(Socket socket, ClientWriter clientWriter) throws IOException {
		this.socket = socket;
		in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        clientWriter.addSocket(out);
	}
	
	@Override
	public void run() {
		while(true) {
			String mes = in.nextLine();
			System.out.println("Received: " + mes);
		}
	}
	
}
