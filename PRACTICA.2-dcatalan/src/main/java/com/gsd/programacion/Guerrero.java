package com.gsd.programacion;

public class Guerrero extends Personaje{

	public Guerrero(String nombre, int salud, int ataque, int defensa, int pociones, int nivel) throws EstadisticaInvalidaException{
		super(nombre, salud, ataque, defensa, pociones, nivel);

		double[] arrayNivel = super.getArray();
		double saludEspecifica = 120 * arrayNivel[nivel];
		super.setSaludInicial((int)saludEspecifica);
		
		int defensaEspecifica = getDefensa() + 5;
		super.setDefensa(defensaEspecifica);
		
	}
	
	public int golpeCritico (Personaje enemigo) {
		double random = Math.random();
		if (random > 0.5) {
			double ataque = this.getAtaque() * 2;
			int salud = enemigo.getSalud() - (int)ataque;
			enemigo.setSalud(salud);
			return (int)ataque;
		} return 0;
	}
	
	public void mostrarInfo() {
		System.out.println("INFO DE EL GUERRERO: \n"
				+ "DEFENSA: " + this.getDefensa()
				+ "\nATAQUE: " + this.getAtaque()
				+ "\nPOCIONES: " + this.getPociones()
				+ "\nNIVEL: " + this.getNivel()
				+ "\nSALUD: " + this.getSaludInicial());
	}
}