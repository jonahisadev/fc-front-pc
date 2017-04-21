package com.jonahisadev.fcfront;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Client client = new Client("localhost", 33333);
		System.out.print("Username: ");
		String name = new Scanner(System.in).nextLine();
		Window.init(client);

		client.sendData("/c/~" + name);
	}

}