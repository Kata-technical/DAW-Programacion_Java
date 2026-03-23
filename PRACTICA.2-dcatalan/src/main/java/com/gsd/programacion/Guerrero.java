package com.gsd.programacion;

public class Guerrero extends Personaje{

	public Guerrero(String nombre, int ataque, int defensa, int nivel, int pociones) throws EstadisticaInvalidaException{
		super(nombre, ataque, defensa, nivel, pociones);

		double[] arrayNivel = super.getArray();
		double saludEspecifica = 120 * arrayNivel[nivel];
		super.setSalud((int)saludEspecifica);
		super.setSaludInicial((int)saludEspecifica);
		
		int defensaEspecifica = getDefensa() + 5;
		super.setDefensa(defensaEspecifica);
		
	}
	
	public void golpeCritico (Personaje enemigo) {
		double random = Math.random();
		if (random > 0.5) {
			double ataque = this.getAtaque() * 2;
			System.out.println(this.getNombre()+" ha hecho un golpe critio a "+enemigo.getNombre()+" causandole "+enemigo.defender((int)ataque)+" de daño");
		} System.out.println(this.getNombre()+" intento hacer golpe critico y no pudo"); 
	}
	
	public void mostrarInfo() {
		System.out.println("INFO DE EL GUERRERO:"+this.getNombre()+" \n"
				+ "DEFENSA: " + this.getDefensa()
				+ "\nATAQUE: " + this.getAtaque()
				+ "\nPOCIONES: " + this.getPociones()
				+ "\nNIVEL: " + this.getNivel()
				+ "\nSALUD: " + this.getSalud());
	}
}