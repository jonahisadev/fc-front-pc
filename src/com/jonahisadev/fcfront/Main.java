package com.jonahisadev.fcfront;

public class Main {

	public static void main(String[] args) {
		Client client = new Client("localhost", 12098);
		client.sendData("/c/~Jonah");
	}

}