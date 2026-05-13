package com.gsd.programacion;

import java.util.List;

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
			System.out.println("ALERTA DE SOBRECARGA");
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

		List<String> datos = List.of("Nave de carga: " + getNombre(), "Capacidad: " + capacidadCarga,
				"Combustible: " + getCombustible(), "Ubicacion: " + getUbicacionActual().planeta());

		datos.stream().forEach(n -> System.out.println(n));

		if (capacidadCarga > 500) {
			System.out.println("ALERTA DE SOBRECARGA");
		}
	}

}