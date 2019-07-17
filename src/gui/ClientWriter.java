package gui;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class ClientWriter{

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

	public void send(String mes) {
	    mes = mes.length()+username.length() + "_" + username + mes;
		for (PrintWriter printWriter : out) {
			printWriter.write(mes);
			printWriter.flush();
		}
	}

}
