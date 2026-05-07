package com.gsd.programacion;

import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args)
			throws EstadisticaInvalidaException, NaveInvalidaException, CombustibleInsuficienteException {

		/*
		 * EJEMPLO DE ARGS:
		 * "naves:exploradora-Voyager-90-3-NADA,carga-Titan-100-2-400,militar-army-80-4-75;destinos:Marte-0.5,Jupiter-2.4"
		 */
		ArrayList<Nave> listaNaves = new ArrayList<Nave>();
		ArrayList<Destino> listaDestinos = new ArrayList<Destino>();
		ArrayList<Mision> listaMisiones = new ArrayList<Mision>();

		String[] secciones = args[0].split(";");
		String[] naves = secciones[0].split(",");
		String[] destinos = secciones[1].split(",");

		String[] quitarTexto = naves[0].split(":");
		naves[0] = quitarTexto[1];

		String[] quitarTexto2 = destinos[0].split(":");
		destinos[0] = quitarTexto2[1];

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

		Mision m1 = new Mision("1", new Destino("marte", 333.45), 0.2);
		Mision m2 = new Mision("2", new Destino("tierra", 206.45), 0.2);
		Mision m3 = new Mision("3", new Destino("titan", 780.33), 0.2);
		Mision m4 = new Mision("4", new Destino("nirvana", 1078.01), 0.2);
		Mision m5 = new Mision("5", new Destino("guinea", 36.23), 0.2);
		Mision m6 = new Mision("6", new Destino("jupiter", 441.98), 0.2);
		Mision m7 = new Mision("7", new Destino("Tlaxcala", 605.39), 0.2);
		Mision m8 = new Mision("8", new Destino("venturada", 21.08), 0.2);
		Mision m9 = new Mision("9", new Destino("obani gemini", 817.55), 0.2);
		listaMisiones.addAll(List.of(m1, m2, m3, m4, m5, m6, m7, m8, m9));

		for (int i = 0; i < listaNaves.size(); i++) {
			for (int j = 0; j < 3; j++) {
				Nave nave = listaNaves.get(i);
				int numeroLista = (int) (Math.random() * (9 - 1 + 1)) + 1;
				EstadoMision estado = EstadoMision.PLANIFICADA;
				int probabilidadExito = 70;

				if (nave instanceof Exploradora) {
					probabilidadExito += 15;
				}
				
				if (nave.getNivelEnergia()<3) {
					probabilidadExito -= 10;
				}

				Mision mision = listaMisiones.get(numeroLista);

				nave.viajar(mision.destino());
				estado = EstadoMision.EN_CURSO;
				
				int numeroAleatorio = (int) (Math.random() * (100 - 0 + 1)) + 0;
				
				if (numeroAleatorio <= probabilidadExito) {
					estado = EstadoMision.COMPLETADA;	
				} else {
					estado = EstadoMision.FALLIDA;
					
				}

			}

		}

	}
}