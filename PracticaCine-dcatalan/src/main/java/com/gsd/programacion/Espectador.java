package com.gsd.programacion;

public class Espectador {
	
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public double getDinero() {
		return dinero;
	}

	@Override
	public String toString() {
		return String.format("Nombre: %s Edad: %d Dinero: %.2f",nombre,edad,dinero);
	}

	private String nombre;
	private int edad;
	private double dinero;

	public Espectador () {
		
		String[] nombres = {"Jose", "Laura" , "Miguel" ,"Lucas" , "Maria" , "Alejandro" ,"David","Elena","Chris","Pablo"};
		int randomStr = (int)(Math.random()*10);
		this.nombre = nombres[randomStr];
		
		double randomDin = (Math.random()*50);
		this.dinero = Math.round(randomDin*100)/100.0;
		
		int random = (int)(Math.random()*85);
		this.edad = random;
	}
	
	
	
}
