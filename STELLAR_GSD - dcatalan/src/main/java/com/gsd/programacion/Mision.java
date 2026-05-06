package com.gsd.programacion;

public record Mision(String id, Destino destino, double riesgoAmbiental) {

	public EstadoMision calculoExito () {
		return null;
	}
	
}
