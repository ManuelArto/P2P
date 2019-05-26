package gui;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class ClientWriter extends Thread{

	LinkedList<PrintWriter> out;
	Scanner scanner;
	String username;

	public ClientWriter(String username) {
		this.username = username;
		out = new LinkedList<>();
		scanner = new Scanner(System.in);
	}

	public void addSocket(PrintWriter out) {
		this.out.add(out);
	}

	@Override
	public void run() {
		while (true) {
			System.out.print(username);
			String mes = scanner.nextLine();
			for (PrintWriter printWriter : out) {
				printWriter.write(username + mes + "#");
				printWriter.flush();
			}
		}
	}

}
