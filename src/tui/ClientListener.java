package tui;

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
				char c;
				StringBuilder lengthS = new StringBuilder();
				while ((c = (char)in.read()) != '_')
					lengthS.append(c);

				int lengthI = Integer.parseInt(lengthS.toString());
				StringBuilder mes = new StringBuilder();
				for (int i = 0; i < lengthI; i++){
					c = (char)in.read();
					mes.append(c);
				}
				System.out.print("\n" + mes + "\n" + username);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
