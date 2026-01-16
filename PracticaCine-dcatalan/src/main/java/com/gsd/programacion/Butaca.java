package com.gsd.programacion;

public class Butaca {

	@Override
	public String toString() {
		return "Butaca [fila=" + fila + ", columna=" + columna + ", ocupado=" + ocupado + "]";
	}

	private int fila;
	private char columna;
	private boolean ocupado;
	
	public Butaca (int fila, char columna, boolean ocupado) {
		this.fila = fila;
		this.columna = columna;
		this.ocupado = ocupado;
	}
}
