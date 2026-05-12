package com.gsd.programacion;

import java.util.List;

public class Exploradora extends Nave {

	public Exploradora(String nombre, double combustible, int nivelEnergia, Destino ubicacionActual)
			throws EstadisticaInvalidaException {
		super(nombre, combustible, nivelEnergia, ubicacionActual);
	}

	public void viajar(Destino destino) throws CombustibleInsuficienteException {

		if (tieneAutonomia(destino.distanciaAL())) {
			this.setUbicacionActual(destino);
		} else {
			throw new CombustibleInsuficienteException("ERROR. No hay combustible suficiente para llegar");
		}

	}

	public boolean tieneAutonomia(double distancia) {
		double consumo = 0.0;
		consumo = (distancia * 0.5) * 0.7;

		if (consumo > this.getCombustible()) {
			return false;
		} else {
			this.setCombustible(this.getCombustible() - consumo);
			return true;
		}
	}

	public void mostrarReporte() {

		List<String> datos = List.of("Nombre: " + getNombre(), "Combustible: " + getCombustible(), "Energia: " + getNivelEnergia(),
				"Ubicacion: " + (getUbicacionActual() == null ? "Base" : getUbicacionActual().planeta()));

		datos.stream().forEach(System.out::println);

		if (getUbicacionActual() != null) {
			System.out.println("Escaneo ambiental completado.");
		}
	}
}