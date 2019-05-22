package p2p;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;

import org.json.JSONArray;
import org.json.JSONObject;

public class URL {

	String address;
	JSONArray ip, port;

	URL() throws IOException{
		/*Enumeration<InetAddress> inetAddresses =  NetworkInterface.getByName("enp3s0").getInetAddresses();
		while(inetAddresses.hasMoreElements()) {
	         InetAddress ia = inetAddresses.nextElement();
	         if(!ia.isLinkLocalAddress()) {
	             address = ia.getHostAddress();
	         }
		} */
		address = InetAddress.getLocalHost().getHostAddress();
		read();
	}

	String getAddress(int i) {
		return ip.getString(i) + ":" + port.getInt(i);
	}

	int getLength() {
		return ip.length();
	}

	int getPort(int i) {
		return port.getInt(i);
	}

	int newPort() {
		return 4440+port.length()+1;
	}

	String getIp(int i) {
		return ip.getString(i);
	}

	void read() {
		try {
			getLock();
			String string = new String(Files.readAllBytes(Paths.get("./p2p/URL.json")));
			JSONObject json = new JSONObject(string);
			ip = json.getJSONArray("ip");
			port = json.getJSONArray("port");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeOnFile() throws IOException {
		ip.put(ip.length(), address);
		port.put(port.length(), 4440+port.length()+1);

		JSONObject json = new JSONObject();
		json.put("ip", ip);
		json.put("port", port);

		getLock();
		BufferedWriter writer = new BufferedWriter(new FileWriter("./p2p/URL.json"));
		writer.write(json.toString(json.length()));
		writer.close();
	}

	void getLock() {
		try {
			File file = new File("./p2p/URL.json");
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
