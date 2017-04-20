package com.jonahisadev.fcfront;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client extends Thread {
	
	public static final String S_VERSION = "0.1";
	public static final int VERSION = 1;
	
	private InetAddress addr;
	private int port;
	private DatagramSocket socket;
	
	public Client(String addr, int port) {
		try {
			this.addr = InetAddress.getByName(addr);
			this.port = port;
			this.socket = new DatagramSocket();
			this.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				byte[] buf = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				String str = new String(buf).trim();
				String[] args = str.split("~");
				
				//
				//	/c/
				//
				if (args[0].equals("/c/")) {
					if (args[1].equals("OK"))
						Window.log("We got a connection!");
				}
				
				//
				//	/m/
				//
				else if (args[0].equals("/m/")) {
					Window.log("[" + args[1] + "] " + args[2]);
				}

				//
				//	UNKNOWN
				//
				else {
					Window.log("Invalid packet type: '" + args[0] + "'");
					System.exit(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(String msg) {
		if (msg.isEmpty())
			return;
		
		if (msg.startsWith("/")) {
			handleCommand(msg.replace("/", ""));
		} else {
			sendData("/m/~" + msg);
		}
	}
	
	public void handleCommand(String cmd) {
		if (cmd.equals("exit")) {
			sendData("/x/~");
		}
	}
	
	public void sendData(String msg) {
		try {
			DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, port);
			socket.send(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getAddress() {
		return addr.getHostAddress();
	}
	
	public int getPort() {
		return port;
	}

}