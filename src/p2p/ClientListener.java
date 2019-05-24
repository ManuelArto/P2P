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
			System.out.println("Listening");
			String mes = "";
			try {
				mes = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Received: " + mes);
		}
	}
	
}
