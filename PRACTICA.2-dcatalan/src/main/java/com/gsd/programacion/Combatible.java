package com.gsd.programacion;

public interface Combatible {
	
	public int atacar(Personaje enemigo);
	public void defender(int daño);
	public boolean estaVivo();
	public boolean autoCurar();
}
