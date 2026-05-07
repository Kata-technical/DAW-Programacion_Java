package com.gsd.programacion;

public class Carga extends Nave {

	private double capacidadCarga;

	public Carga(String nombre, double combustible, int nivelEnergia, Destino ubicacionActual, double capacidadCarga)
			throws EstadisticaInvalidaException {
		super(nombre, combustible, nivelEnergia, ubicacionActual);
		this.capacidadCarga = capacidadCarga;
	}

	public void viajar(Destino destino) throws CombustibleInsuficienteException {
	
		if (tieneAutonomia(destino.distanciaAL())) {
			this.setUbicacionActual(destino);	
		} else {
			throw new CombustibleInsuficienteException("ERROR. No hay combustible suficiente para llegar");
		}
	}

	public boolean tieneAutonomia(double distancia) {
		double consumoBase = 0.0;
		consumoBase = (distancia * 1.2) * 2.5;
		
		if (this.capacidadCarga > 500) {
			System.out.println("ALERTA. Hay sobrecarga en la nave, esto afecta considerablemente el consumo");
			consumoBase *= 3;
		}
		
		if (consumoBase > this.getCombustible()) {
			return false;
		} else {
			this.setCombustible(this.getCombustible() - consumoBase);
			return true;
		}
	}

	public void mostrarReporte() {
		System.out.println("[ESCANEO] Riesgo ambiental reducido a la mitad."); // rehacer luego
	}

}