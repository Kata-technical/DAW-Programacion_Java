package com.gsd.programacion;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws EstadisticaInvalidaException, NaveInvalidaException {

		ArrayList<Nave> listaNaves = new ArrayList<Nave>();
		ArrayList<Destino> listaDestinos = new ArrayList<Destino>();

		String[] secciones = args[0].split(";");
		String[] naves = secciones[0].split(",");
		String[] destinos = secciones[1].split(",");

		for (int i = 0; i < naves.length; i++) {
			String[] datos = naves[i].split("-");

			double combustible = Double.parseDouble(datos[1]);
			int nivelEnergia = Integer.parseInt(datos[2]);

			if (datos[0].equalsIgnoreCase("exploradora")) {
				Exploradora exp = new Exploradora(datos[1], combustible, nivelEnergia, null);
				listaNaves.add(exp);
			} else if (datos[0].equalsIgnoreCase("carga")) {

				double carga = Double.parseDouble(datos[4]);
				Carga car = new Carga(datos[1], combustible, nivelEnergia, null, carga);
				listaNaves.add(car);
			} else if (datos[0].equalsIgnoreCase("militar")) {

				int blindaje = Integer.parseInt(datos[4]);
				Militar mil = new Militar(datos[1], combustible, nivelEnergia, null, blindaje);
				listaNaves.add(mil);
			} else {
				throw new NaveInvalidaException("ERROR. La nave es invalida");
			}
		}

		for (int j = 0; j < destinos.length; j++) {
			String[] datos = destinos[j].split("-");
			double distancia = Double.parseDouble(datos[1]);
			Destino des = new Destino(datos[0], distancia);
			listaDestinos.add(des);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}
}