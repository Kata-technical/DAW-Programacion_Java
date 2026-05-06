package com.gsd.programacion;

public class Carga extends Nave {

	private double capacidadCarga;

	public Carga(String nombre, double combustible, int nivelEnergia, Destino ubicacionActual, double capacidadCarga)
			throws EstadisticaInvalidaException {
		super(nombre, combustible, nivelEnergia, ubicacionActual);
		this.capacidadCarga = capacidadCarga;
	}

	public void viajar(Destino destino) throws CombustibleInsuficienteException {
		double consumoBase = 0.0;
		consumoBase = (destino.distanciaAL() * 1.2) * 2.5;
		
		if (this.capacidadCarga > 500) {
			System.out.println("ALERTA. Hay sobrecarga en la nave, esto afecta considerablemente el consumo");
			consumoBase *= 3;
		}
		
		if (consumoBase > this.getCombustible()) {
			throw new CombustibleInsuficienteException("ERROR. No hay combustible suficiente para llegar");
		}
		
		this.setUbicacionActual(destino);
		this.setCombustible(this.getCombustible() - consumoBase);

	}

	public boolean tieneAutonomia(double distancia) {
		// no se a que se refiere exactamente (VERIFICA SI PUEDE LLEGAR AL DESTINO)
		
		return false;
	}

	public void mostrarReporte() {
		System.out.println("[ESCANEO] Riesgo ambiental reducido a la mitad."); // rehacer luego
	}

}