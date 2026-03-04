package com.gsd.programacion;

public abstract class Personaje implements Combatible {

	public abstract void mostrarInfo();
	
	public Personaje(String nombre, int ataque, int defensa, int nivel, int pociones) throws EstadisticaInvalidaException {

	    if (ataque < 0) throw new EstadisticaInvalidaException("ERROR. No puede haber ataque negativo");
	    if (defensa < 0) throw new EstadisticaInvalidaException("ERROR. No puede haber defensa negativa");
	    if (pociones < 0 || pociones > 5) throw new EstadisticaInvalidaException("ERROR. No puede haber pociones negativas ni mas de 5");
	    if (nivel < 0 || nivel > 10) throw new EstadisticaInvalidaException("ERROR. No puedes tener un nivel inferior a 0 o superior a 10");
		
		this.nombre = nombre;
		this.saludInicial = 100;
		this.ataque = ataque;
		this.defensa = defensa;
		this.pociones = pociones;
		this.nivel = nivel;
	}
	
	public int getSaludInicial() {
		return saludInicial;
	}

	public void setSaludInicial(int saludInicial) {
		this.saludInicial = saludInicial;
	}

	public String getNombre() {
		return nombre;
	}

	public int getSalud() {
		return this.salud;
	}
	
	public void setSalud (int salud) {
		this.salud = salud;
	}

	public int getAtaque() {
		return ataque;
	}
	
	public int setAtaque(int ataque) {
		return this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public int setDefensa (int defensa) {
		return this.defensa = defensa;
	}
	
	public int getPociones() {
		return pociones;
	}
	
	public int setPociones(int pociones) {
		return this.pociones = pociones;
	}

	public int getNivel() {
		return nivel;
	}
	
	public double[] getArray() {
		return array;
	}

	private String nombre;
	private int saludInicial;
	private int salud = 100;
	private int ataque;
	private int defensa;
	private int pociones;
	private int nivel;
	private double[] array = {
		1.1,1.3,1.5,1.7,2.0,2.2,2.4,2.6,2.9,3.2	
	};
	
	

	public int defender (int daño) {
		daño = daño - this.defensa;
		if (daño < 0)
			daño = 0;
		this.salud = this.salud - daño;
		return daño;
	}
	
	public void atacar(Personaje enemigo) {
		int dañoTotal = enemigo.defender(this.ataque);
		System.out.println(this.getNombre()+" ha atacado a "+enemigo.getNombre()+" causandole "+dañoTotal+" de daño");
	}	
	
	public boolean estaVivo() {
		if (this.salud > 0)
			return true;
		else
			return false;
	}
	
	public boolean autoCurar() {
		if (this.salud < (this.saludInicial / 2) && this.pociones > 0) {
			this.pociones --;
			double random = (Math.random() * (1.25 - 1.15 + 1) + 1.15);
			this.salud *=random;
			System.out.println(this.getNombre() + " se ha curado!");
			return true;
		} return false;
	}
}
