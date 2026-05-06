package com.gsd.programacion;

public class Exploradora extends Nave {

	public Exploradora(String nombre, double combustible, int nivelEnergia, Destino ubicacionActual)
			throws EstadisticaInvalidaException {
		super(nombre, combustible, nivelEnergia, ubicacionActual);
	}

	public void viajar(Destino destino) throws CombustibleInsuficienteException {
		double consumo = 0.0;
		consumo = (destino.distanciaAL() * 0.5) * 0.7;

		if (consumo > this.getCombustible()) {
			throw new CombustibleInsuficienteException("ERROR. No hay combustible suficiente para llegar");
		} else {
			this.setUbicacionActual(destino);
			this.setCombustible(this.getCombustible() - consumo);
		}
	}

	public boolean tieneAutonomia(double distancia) {
		// no se a que se refiere exactamente (VERIFICA SI PUEDE LLEGAR AL DESTINO)
		return false;
	}



	public void mostrarReporte() {
		System.out.println("[ESCANEO] Riesgo ambiental reducido a la mitad."); // rehacer luego
	}

}