package com.gsd.programacion;

public class Militar extends Nave {

	private int blindaje;

	public Militar(String nombre, double combustible, int nivelEnergia, Destino ubicacionActual, int blindaje) throws EstadisticaInvalidaException {
		super(nombre, combustible, nivelEnergia, ubicacionActual);

		if (blindaje < 1 || blindaje > 100) throw new EstadisticaInvalidaException("ERROR. El blindaje no puede ser superior a 100 ni menor a 1");
		this.blindaje = blindaje;
	}

	public int getBlindaje() {
		return blindaje;
	}
	
	public void viajar(Destino destino) throws CombustibleInsuficienteException {
		double consumoBase = 0.0;
		consumoBase = (destino.distanciaAL() * 0.8) * 1.5;

		if (consumoBase > this.getCombustible()) {
			throw new CombustibleInsuficienteException("ERROR. No hay combustible suficiente para llegar");
		}
		
		double random = Math.random();
		if (random > 0 && random < 0.2) {
			System.out.println("Encuentro hostil");
			this.setCombustible(this.getCombustible() - 10);
		}
		
		this.setUbicacionActual(destino);
		this.setCombustible(this.getCombustible() - consumoBase);

	}

	public boolean tieneAutonomia(double distancia) {
		
		return false;
	}
	

	public void mostrarReporte() {
		System.out.println("[ESCANEO] Riesgo ambiental reducido a la mitad."); // rehacer luego
	}
	
}
