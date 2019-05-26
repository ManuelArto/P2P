package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class P2P {

	static ClientWriter clientWriter;
	static String username;

	public static void main(String[] args) throws IOException {

		System.out.print("Insert your username: ");
		username = new Scanner(System.in).nextLine() + ": ";
		clientWriter = new ClientWriter(username);

		URL urls = new URL();
		int n = urls.getLength();

		for (int i = 0; i < n; i++) {
			System.out.println("Connecting to peer: " + i);
			Socket socket = new Socket(urls.getIp(i), urls.getPort(i));
			newSocket(socket);
		}

		clientWriter.start();
		System.out.println("Creating Server");
		ServerSocket server = new ServerSocket(urls.newPort());
		urls.writeOnFile();

		for (;;) {
			Socket socket = server.accept();
			newSocket(socket);
		}

	}

	public static void newSocket(Socket socket) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter out = new PrintWriter(osw);
			clientWriter.addSocket(out);

			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			new ClientListener(in, username).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
