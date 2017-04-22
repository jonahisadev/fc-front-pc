package com.jonahisadev.fcfront;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Client client = new Client("localhost", 33333);
		Login.init();
		//Window.init(client);
	}

}