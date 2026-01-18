package com.gsd.programacion;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
			System.out.println("Hello world!");
			
			Pelicula peli = new Pelicula();
	
			Cine yelmo = new Cine(peli,3.99);
			yelmo.generarEspectadores();
			
			System.out.println("En el asiento: ");
			System.out.println(yelmo.getAsientos()[3][4]);
			System.out.println("Está: \n"+yelmo.getAsientos()[3][4].getEspectador());
			System.out.println("\n"+peli.toString());
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.println(yelmo.getAsientos()[i][j]);
					System.out.println(yelmo.getAsientos()[i][j].getEspectador());
				}
			}
			
	}
}