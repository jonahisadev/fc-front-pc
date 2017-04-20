package com.jonahisadev.fcfront;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client extends Thread {
	
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
				
				if (args[0].equals("/c/")) {
					if (args[1].equals("OK"))
						System.out.println("We got a connection!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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

}