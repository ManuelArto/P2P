package gui;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

class ClientWriter{

	LinkedList<PrintWriter> out;
	Scanner scanner;
	String username;

	ClientWriter(String username) {
		this.username = username;
		out = new LinkedList<>();
		scanner = new Scanner(System.in);
	}

	void addSocket(PrintWriter out) {
		this.out.add(out);
	}

	void send(String mes) {
	    mes = mes.length()+username.length() + "_" + username + mes;
		for (PrintWriter printWriter : out) {
			printWriter.write(mes);
			printWriter.flush();
		}
	}

}
