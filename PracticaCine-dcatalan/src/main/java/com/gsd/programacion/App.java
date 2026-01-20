package com.gsd.programacion;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
			System.out.println("Hello world!");
			
			Pelicula peli = new Pelicula();
	
			Cine yelmo = new Cine(peli,8.99);
			yelmo.getPelicula().setEdadMinima(66);
			yelmo.generarEspectadores();
			
			System.out.println("En el asiento: ");
			System.out.println(yelmo.getAsientos()[3][4]);
			System.out.println("Está: \n"+yelmo.getAsientos()[3][4].getEspectador());
			System.out.println(peli.toString());
			
			System.out.println();
			
			for (int i = 0; i < yelmo.getAsientos().length; i++) {
				for (int j = 0; j < yelmo.getAsientos()[0].length; j++) {
					if (yelmo.getAsientos()[i][j].isOcupado()) {
					//System.out.println(yelmo.getAsientos()[i][j]);
					System.out.println(yelmo.getAsientos()[i][j].getEspectador());
					}
				}
			}
			
			yelmo.mostrarPatio();
	}
}