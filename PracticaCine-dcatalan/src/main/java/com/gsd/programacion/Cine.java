package com.gsd.programacion;

public class Cine {

	public Sala[] getSalas() {
		return salas;
	}

	private Sala[] salas;

	public Cine(int numSalas) {
		this.salas = new Sala[numSalas];

		// FIXME
		int cont = 0;
		for (int i = 0; i < numSalas; i++) {
			Pelicula peli = new Pelicula();
			Sala sala = new Sala(peli, 8.99, 8, 12);
			
			if (i == 0) {
				salas[i] = sala;
				continue;
			}
			if (seRepite(salas, sala, cont)) {
				i--;
				cont++;
			} else {
				salas[i] = sala;
				cont++;
			}

		}
	}

	private boolean seRepite(Sala[] salas, Sala sala, int contador) {
		for (int i = 0; i < contador; i++) {
			if (salas[i].getPelicula().getTitulo() == sala.getPelicula().getTitulo())
				return true;
		}
		return false;

	}

}
