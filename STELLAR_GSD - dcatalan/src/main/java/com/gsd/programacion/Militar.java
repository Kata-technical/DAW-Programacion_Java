package com.gsd.programacion;

import java.util.List;

public class Militar extends Nave {

	private int blindaje;

	public Militar(String nombre, double combustible, int nivelEnergia, Destino ubicacionActual, int blindaje)
			throws EstadisticaInvalidaException {
		super(nombre, combustible, nivelEnergia, ubicacionActual);

		if (blindaje < 1 || blindaje > 100)
			throw new EstadisticaInvalidaException("ERROR. El blindaje no puede ser superior a 100 ni menor a 1");
		this.blindaje = blindaje;
	}

	public int getBlindaje() {
		return blindaje;
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
		consumoBase = (distancia * 0.8) * 1.5;

		double random = Math.random();

		if (random > 0 && random < 0.2) {
			System.out.println("Encuentro hostil");
			this.setCombustible(this.getCombustible() - 10);
		}

		if (consumoBase > this.getCombustible()) {
			return false;
		} else {
			this.setCombustible(this.getCombustible() - consumoBase);
			return true;
		}

	}

	public void mostrarReporte() {

		List<String> datos = List.of("Nave militar: " + getNombre(), "Blindaje: " + blindaje,
				"Combustible: " + getCombustible(),
				"Ubicacion; " + (getUbicacionActual() == null ? "Base" : getUbicacionActual().planeta()));
		datos.stream().forEach(n -> System.out.println(n));

	}

}
