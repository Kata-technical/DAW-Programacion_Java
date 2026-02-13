package com.gsd.programacion;

public class Arquero extends Personaje{
	public Arquero(String nombre, int salud, int ataque, int defensa, int pociones, int nivel) {
		super(nombre, salud, ataque, defensa, pociones, nivel);
		
		double[] array = super.getArray();
		int defensaEspecifica = (getDefensa() + 3)*(int)array[nivel];
		super.setDefensa(defensaEspecifica);
		
		int ataqueEspecifico = (getAtaque() + 3)*(int)array[nivel];
		super.setAtaque(ataqueEspecifico);
	}
	
	public void disparoPreciso (Personaje enemigo) {
		double random = Math.random();
		if (random > 0.75) {
			double ataque = this.getAtaque() * 2;
			int salud = enemigo.getSalud() - (int)ataque;
			enemigo.setSalud(salud);
		}
	}
	
	public void mostrarInfo() {
		System.out.println("INFO DE EL GUERRERO: \n"
				+ "DEFENSA: " + this.getDefensa()
				+ "\nATAQUE: " + this.getAtaque()
				+ "\nPOCIONES: " + this.getPociones()
				+ "\nNIVEL: " + this.getNivel()
				+ "\nSALUD: " + this.getSalud());
	}
	
	public void atacar(Personaje enemigo) {
		int salud = enemigo.getSalud() - this.getAtaque();
		enemigo.setSalud(salud);
	}	
}