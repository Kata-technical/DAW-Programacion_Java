package com.gsd.programacion;

public class Mago extends Personaje{
	public Mago(String nombre, int salud, int ataque, int defensa, int pociones, int nivel) throws EstadisticaInvalidaException {
		super(nombre, salud, ataque, defensa, pociones, nivel);
		
		double saludEspecifica = salud + 80;
		super.setSalud((int)saludEspecifica);
		super.setSaludInicial((int)saludEspecifica);
		
		int ataqueEspecifica = getAtaque() + 10;
		double[] array = super.getArray();
		super.setAtaque((int)ataqueEspecifica*(int)array[nivel]);
				
	}
	
	public void lanzarHechizo (Personaje enemigo) {
		int salud = enemigo.getSalud() - this.getAtaque();
		enemigo.setSalud(salud);
		System.out.println(this.getNombre()+" le lanzó un hechizo a "+enemigo.getNombre()+" causandole "+this.getAtaque()+" de daño");
	}
	
	public boolean autoCurarse() {
		if (this.getSalud() < (this.getSaludInicial() / 2)) {
			int pociones = this.getPociones();
			this.setPociones(pociones--);
			double random = Math.random();
			if (random > 0.0 || random < 0.70) {
				double salud = this.getSalud()*1.3;
				this.setSalud((int)salud);
				System.out.println(this.getNombre()+" se ha curado con POTENCIADOR");
				return true;
			} System.out.println(this.getNombre()+" intento curarse y no pudo"); return false;
		} System.out.println(this.getNombre()+" intento curarse y no pudo"); return false;
	}
	
	public void mostrarInfo() {
		System.out.println("INFO DE EL GUERRERO: \n"
				+ "DEFENSA: " + this.getDefensa()
				+ "\nATAQUE: " + this.getAtaque()
				+ "\nPOCIONES: " + this.getPociones()
				+ "\nNIVEL: " + this.getNivel()
				+ "\nSALUD: " + this.getSalud());
	}
		
}