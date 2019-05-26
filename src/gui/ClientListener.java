package gui;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientListener extends Thread{

	BufferedReader in;
	String username;

	public ClientListener(BufferedReader in, String username) throws IOException {
		this.in = in;
		this.username = username;
	}

	@Override
	public void run() {
		while(true) {
			try {
				StringBuilder mes = new StringBuilder();
				char c;
				while((c = (char)in.read()) != '#')
					mes.append(c);
				System.out.print("\n" + mes + "\n" + username);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
