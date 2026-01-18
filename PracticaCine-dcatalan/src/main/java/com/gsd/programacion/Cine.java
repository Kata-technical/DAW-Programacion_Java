package com.gsd.programacion;

public class Cine {

	public Butaca[][] getAsientos() {
		return asientos;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public double getPrecio() {
		return precio;
	}

	private Pelicula pelicula;
	private double precio;
	private Butaca[][] asientos;

	public Cine(Pelicula pelicula, double precio) {
		this.pelicula = pelicula;
		this.precio = precio;
		this.asientos = new Butaca[8][9];

		char columna = 'A';
		int almacen = 0;
		int fila = 8;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 9; j++) {
				almacen = j;
				almacen = almacen + 65;
				columna = (char) almacen;

				asientos[i][j] = new Butaca(fila, columna, false);

			}
			fila--;
		}
	}

	public void generarEspectadores() {
		for (int k = 0; k < 8 * 9; k++) {
			boolean continuar = true;
			Espectador espec = new Espectador();
			if (espec.getDinero() >= precio && espec.getEdad() >= pelicula.getEdadMinima()) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 9; j++) {
						if (asientos[i][j].isOcupado() == false) {
							asientos[i][j].setEspectador(espec);
							asientos[i][j].setOcupado(true);
							continuar = false;
							break;
						} else {
							continue;
						}
					}
					if (!continuar) break;
				}
			} else {
				continue;
			}
		}
	}

}
