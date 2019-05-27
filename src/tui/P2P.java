package tui;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class P2P {

	static ClientWriter clientWriter;
	static String username;
	static Scanner scanner;

	public static void main(String[] args) throws IOException {
		scanner = new Scanner(System.in);

		System.out.print("Insert your username: ");
		username = scanner.nextLine() + ": ";

		clientWriter = new ClientWriter(username);

		// URL urls = new URL();
		// int n = urls.getLength();

		while (true) {
			System.out.println("Insert the ip:port of the server, or insert end");
			String answer = scanner.nextLine();
			if (answer.equals("end"))
				break;
			System.out.println("Connecting to peer: " + answer);
			String address[] = answer.split(":");
			Socket socket = new Socket(address[0], Integer.parseInt(address[1]));
			newSocket(socket);
		}

		System.out.print("Creating Server, insert port: ");
		int port = scanner.nextInt();
		ServerSocket server = new ServerSocket(port);
		clientWriter.start();
		//urls.writeOnFile();

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
