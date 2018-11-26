package application;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
	public static final String SERVERNAME = "localhost";
	public static final int SERVERPORT = 8888;

	public String send(String location, String mobileNo, String pin, String reason) throws SocketException {
		DatagramSocket dsock = new DatagramSocket();
		try {
			InetAddress address = InetAddress.getLocalHost();
			String s1 = location + ":" + mobileNo + ":" + pin + ":" + reason;
			ServerValidation sv = new ServerValidation();
			sv.load();
			String update = sv.validate(s1);
			if(update.endsWith("Success")) {
				byte arr[] = s1.getBytes();
				DatagramPacket dpack = new DatagramPacket(arr, arr.length, address, 2000);
				dsock.send(dpack);
			}
			return update;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
		  dsock.close();
		}
		return "Error Processing";
	}

	public static void main(String[] args) throws Exception {

	}
}
