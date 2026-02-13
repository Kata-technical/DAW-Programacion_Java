package com.gsd.programacion;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		for (int i = 0; i < args.length; i++) {
			String personaje = args[0];
			String[] campos = personaje.split("-");
			
			if (campos[0].equals("Guerrero")) {
				Guerrero mago = new Guerrero();
			} else if (campos[0].equals("Mago")) {
				Mago mago = new Mago();
			} else if (campos[0].equals("Arquero")) {
				Arquero arquero = new Arquero();
			}
			
			
		}

		
		
	}
}