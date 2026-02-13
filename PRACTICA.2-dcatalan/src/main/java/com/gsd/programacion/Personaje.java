package com.gsd.programacion;

public abstract class Personaje implements Combatible {

	public String getNombre() {
		return nombre;
	}

	public int getSalud() {
		return this.salud;
	}
	
	public int getSaludInicial() {
		return this.saludInicial;
	}
	
	public int setSaludInicial(int saludInicial) {
		return this.saludInicial = saludInicial;
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
	private int salud;
	private int ataque;
	private int defensa;
	private int pociones;
	private int nivel;
	private double[] array = {
		1.1,1.3,1.5,1.7,2.0,2.2,2.4,2.6,2.9,3.2	
	};
	
	public abstract void mostrarInfo();
	
	public Personaje(String nombre,int salud, int ataque, int defensa, int pociones, int nivel) {
		if (ataque < 0 || defensa < 0)
			return;
		if (pociones < 0 || pociones > 5)
			return;
		if (nivel < 0 || nivel > 10)
			return;
		
		this.nombre = nombre;
		this.saludInicial = 100;
		this.salud = this.saludInicial;
		this.ataque = ataque;
		this.defensa = defensa;
		this.pociones = pociones;
		this.nivel = nivel;
	}

	public void defender (int daño) {
		daño = this.defensa - daño;
		if (daño < 0)
			daño = 0;
		this.salud = this.salud - daño;
		if (this.salud == 0) {
			System.out.println("muelto");
		}
	}
	
	
	public boolean estaVivo() {
		if (this.salud > 0)
			return true;
		else
			return false;
	}
	
	public void autoCurar() {
		if (this.salud < (this.saludInicial / 2)) {
			this.pociones --;
			double random = (Math.random() * (1.25 - 1.15 + 1) + 1.15);
			this.salud *=random;
		}
	};
	
}
