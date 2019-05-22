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
				while ((char a = in.read()) != -1 )
					mes += a;
				System.out.println("Received: " + mes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
