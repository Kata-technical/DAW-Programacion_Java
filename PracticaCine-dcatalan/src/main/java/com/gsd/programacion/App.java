package com.gsd.programacion;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
			System.out.println("Hello world!");
			
			Pelicula peli = new Pelicula("los wakala",2,18,"Arthur Morgan");
			
			Cine yelmo = new Cine(peli,3.99);
			
			//System.out.println(yelmo.asientos[3][2]);
			
	}
}