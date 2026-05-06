package com.gsd.programacion;

public abstract class Nave implements Navegable {

	private String nombre;
	private double combustible;
	private int nivelEnergia;
	private Destino ubicacionActual;

	public Nave(String nombre, double combustible, int nivelEnergia, Destino ubicacionActual)
			throws EstadisticaInvalidaException {

		if (combustible < 0 || combustible > 100)
			throw new EstadisticaInvalidaException("ERROR. El combustible no puede ser mayor a 100 o menor a 0");
		if (nivelEnergia < 1 || nivelEnergia > 5)
			throw new EstadisticaInvalidaException("ERROR. El combustible no puede ser mayor a 5 o menor a 1");

		this.nombre = nombre;
		this.combustible = combustible;
		this.nivelEnergia = nivelEnergia;
		this.ubicacionActual = ubicacionActual;
	}

	public String getNombre() {
		return nombre;
	}

	public double getCombustible() {
		return combustible;
	}

	public int getNivelEnergia() {
		return nivelEnergia;
	}

	public Destino getUbicacionActual() {
		return ubicacionActual;
	}
	
	public void setCombustible(double combustible) {
		this.combustible = combustible;
	}

	public void setUbicacionActual(Destino ubicacionActual) {
		this.ubicacionActual = ubicacionActual;
	}

	public void repostar() throws FueraDeSectorException {
		if (this.getUbicacionActual() == null) {
			this.setCombustible(100);
		} else {
			throw new FueraDeSectorException("ERROR. Tienes que estar en base");
		}
	}

	public abstract void mostrarReporte(); // muestra info con streams / landas

}
