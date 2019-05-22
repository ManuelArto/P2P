package p2p;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientListener extends Thread{

	BufferedReader in;

	public ClientListener(BufferedReader in) throws IOException {
		this.in = in;
	}

	@Override
	public void run() {
		while(true) {
			try {
				String mes = "";
				char c;
				while((c = (char)in.read()) != '?')
					mes += c;
				System.out.print("\nReceived: " + mes + "\nInsert message: ");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
