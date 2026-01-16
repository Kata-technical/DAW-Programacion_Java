package com.gsd.programacion;
import java.util.random.*;

public class Espectador {
	
	private String nombre;
	private int edad;
	private double dinero;

	public Espectador () {
		
		String[] nombres = {"Jose", "Laura" , "Miguel" ,"Lucas" , "Maria" , "Alejandro" ,"David","Elena","Chris","Pablo"};
		int randomStr = (int)(Math.random()*10);
		this.nombre = nombres[randomStr];
		
		double randomPr = (Math.random()*50);
		this.dinero = randomPr;
		
		int random = (int)(Math.random()*85);
		this.edad = random;
	}
	
	
	
}
