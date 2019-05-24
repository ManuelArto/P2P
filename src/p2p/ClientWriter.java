package p2p;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class ClientWriter extends Thread{

	LinkedList<PrintWriter> out;
	Scanner scanner;

	public ClientWriter() throws IOException {
		out = new LinkedList<PrintWriter>();
		scanner = new Scanner(System.in);
	}

	public void addSocket(PrintWriter out) {
		this.out.add(out);
	}

	@Override
	public void run() {
		while (true) {
			System.out.print("Insert the message: ");
			String mes = scanner.nextLine();
			for (PrintWriter printWriter : out) {
<<<<<<< HEAD
				printWriter.write(mes);
=======
				printWriter.write(mes + "?");
>>>>>>> cc3adf277fac117dc502b5cd93e029279674d302
				printWriter.flush();
			}
		}
	}

}
