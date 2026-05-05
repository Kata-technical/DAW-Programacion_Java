package com.gsd.programacion;

public interface Navegable {
	
	void viajar (Destino destino) throws CombustibleInsuficienteException;
	boolean tieneAutonomia(double distancia);
	void repostar();

}
