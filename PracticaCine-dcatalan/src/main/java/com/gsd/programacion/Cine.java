package com.gsd.programacion;

public class Cine {

	private Pelicula pelicula;
	private double precio;
	private Butaca[][] asientos;
	
	public Cine (Pelicula pelicula, double precio) {
		this.pelicula = pelicula;
		this.precio = precio;
		this.asientos = new Butaca[8][9];
		
		char caracter = 'A';
		int almacen = 0;
		
		for (int i = 1; i <= 8; i++) {
			for (int j = 0; j < 9; j++) {
				almacen = j;
				almacen = almacen + 65;
				caracter = (char)almacen;
				
				asientos[i][j] = new Butaca(i, caracter, false);
				System.out.println(asientos[i][j].toString());
				
			}
		}
	}
	
	
}
