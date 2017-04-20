package com.jonahisadev.fcfront;

public class Main {

	public static void main(String[] args) {
		Client client = new Client("localhost", 33333);
		Window.init(client);
		client.sendData("/c/~Jonah");
	}

}