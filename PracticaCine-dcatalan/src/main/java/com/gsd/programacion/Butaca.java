package com.gsd.programacion;

public class Butaca {

	@Override
	public String toString() {
		return "Butaca [fila=" + fila + ", columna=" + columna + ", ocupado=" + ocupado + "]";
	}

	public int getFila() {
		return fila;
	}

	public char getColumna() {
		return columna;
	}

	public boolean isOcupado() {
		return ocupado;
	}
	
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	public void setEspectador(Espectador espectador) {
		this.espectador = espectador;
	}
	
	public Espectador getEspectador() {
		return espectador;
	}

	private int fila;
	private char columna;
	private boolean ocupado;
	private Espectador espectador;

	
	public Butaca (int fila, char columna, boolean ocupado) {
		this.fila = fila;
		this.columna = columna;
		this.ocupado = ocupado;
		this.espectador = null;
	}
}
