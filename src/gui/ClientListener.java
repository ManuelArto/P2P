package gui;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientListener extends Thread{

	Chat chat;

	BufferedReader in;
	String username;

	public ClientListener(BufferedReader in, String username, Chat chat) throws IOException {
		this.chat = chat;
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
				chat.write(mes.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
