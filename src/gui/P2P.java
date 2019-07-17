package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class P2P {

    static Chat chat;
    static Login login;
    static ClientWriter clientWriter;
	static String username;

    public static void main(String[] args) {
        chat = new Chat();
        login = new Login(P2P::startServer, chat);
        Login.createFrame(login.Login);
    }

	static void startServer(){
	    try {
            clientWriter = new ClientWriter(username);
            chat.setClientWriter(clientWriter);

            URL urls = new URL();
            int n = urls.getLength();

            for (int i = 0; i < n; i++) {
                System.out.println("Connecting to peer: " + i);
                Socket socket = new Socket(urls.getIp(i), urls.getPort(i));
                newSocket(socket);
            }

            System.out.println("Creating Server");
            ServerSocket server = new ServerSocket(urls.newPort());
            urls.writeOnFile();

            for (;;) {
                Socket socket = server.accept();
                newSocket(socket);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

	static void newSocket(Socket socket) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
			PrintWriter out = new PrintWriter(osw);
			clientWriter.addSocket(out);

			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			new ClientListener(in, username, chat).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
