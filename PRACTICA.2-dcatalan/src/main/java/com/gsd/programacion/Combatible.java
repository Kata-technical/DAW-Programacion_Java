package com.gsd.programacion;

public interface Combatible {
	
	public void atacar(Personaje enemigo);
	public int defender(int daño);
	public boolean estaVivo();
	public boolean autoCurar();
}
