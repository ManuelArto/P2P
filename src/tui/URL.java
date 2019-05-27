package tui;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class URL {

	private String address;
	private JSONArray ip, port;

	URL() throws IOException {
		try(final DatagramSocket socket = new DatagramSocket()){
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			address = socket.getLocalAddress().getHostAddress();
		}
		read();
	}

	String getAddress(int i) {
		return getIp(i) + ":" + getPort(i);
	}

	int getLength() {
		return ip.length();
	}

	int getPort(int i) {
		return port.getInt(i);
	}

	int newPort() {
		return 8080 + port.length()+1;
	}

	String getIp(int i) {
		return ip.getString(i);
	}

	void read() {
		try {
			// getLock();
			String string = new String(Files.readAllBytes(Paths.get("./src/gui/URL.json")));
			JSONObject json = new JSONObject(string);
			ip = json.getJSONArray("ip");
			port = json.getJSONArray("port");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeOnFile() throws IOException {
		ip.put(ip.length(), address);
		port.put(port.length(), 8080+port.length()+1);

		JSONObject json = new JSONObject();
		json.put("ip", ip);
		json.put("port", port);

		// getLock();
		BufferedWriter writer = new BufferedWriter(new FileWriter("./src/gui/URL.json"));
		writer.write(json.toString(json.length()));
		writer.close();
	}

	// test
	void getLock() {
		try {
			File file = new File("./src/gui/URL.json");
			FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
	        FileLock lock = channel.lock();

	        try {
		        while((lock = channel.tryLock()) != null);
	        } catch (OverlappingFileLockException e) {
	            //Thread.sleep(100);
	        }
	        lock.release();
	        channel.close();

	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
	}

}
