package com.gsd.programacion;

public class Sala {

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

	public Sala(Pelicula pelicula, double precio, int filas, int columnas) {
		this.pelicula = pelicula;
		this.precio = precio;
		this.asientos = new Butaca[filas][columnas];

		char columna = 'A';
		int almacen = 0;
		int fila = asientos.length;

		for (int i = 0; i < asientos.length; i++) {
			for (int j = 0; j < asientos[0].length; j++) {
				almacen = j;
				almacen = almacen + 65;
				columna = (char) almacen;

				asientos[i][j] = new Butaca(fila, columna, false);

			}
			fila--;
		}
	}
	
	public void generarEspectadores() {
		for (int i = 0; i < asientos.length; i++) {
			for (int j = 0; j < asientos[0].length; j++) {
			Espectador espec = new Espectador();
				if (espec.getDinero() >= precio && espec.getEdad() >= pelicula.getEdadMinima()) {
					double restante = espec.getDinero() - precio;
					restante = (restante*100.00)/100.00;
					asientos[i][j].setEspectador(espec);
					asientos[i][j].setOcupado(true);
					asientos[i][j].getEspectador().setDinero(restante);
					}
				}
		}
	}
	
	public void mostrarPatio () {
		for (int i = 0; i < asientos.length; i++) {
			for (int j = 0; j < asientos[0].length; j++) {
				if (asientos[i][j].isOcupado()==true) {
					System.out.print("❎");
				} else {
					System.out.print("🟥");
				}
			} System.out.println("");
		}
	}
/*
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
	*/

}
