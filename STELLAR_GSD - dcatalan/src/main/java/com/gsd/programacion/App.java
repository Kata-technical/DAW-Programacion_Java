package com.gsd.programacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
	public static void main(String[] args) throws EstadisticaInvalidaException, NaveInvalidaException,
			CombustibleInsuficienteException, FueraDeSectorException {

		/*
		 * EJEMPLO DE ARGS:
		 * "naves:exploradora-Voyager-90-3-NADA,carga-Titan-100-2-400,militar-army-80-4-75;destinos:Marte-0.5,Jupiter-2.4"
		 */

		ArrayList<Nave> listaNaves = new ArrayList<Nave>();
		ArrayList<Destino> listaDestinos = new ArrayList<Destino>();
		ArrayList<Mision> listaMisiones = new ArrayList<Mision>();
		ArrayList<ResultadoMision> resultados = new ArrayList<>();
		HashMap<String, Double> distanciasRecorridas = new HashMap<String, Double>();
		ArrayList<Double> combustibleRestante = new ArrayList<Double>();

		String[] secciones = args[0].split(";");
		String[] naves = secciones[0].split(",");
		String[] destinos = secciones[1].split(",");

		String[] quitarTexto = naves[0].split(":");
		naves[0] = quitarTexto[1];

		String[] quitarTexto2 = destinos[0].split(":");
		destinos[0] = quitarTexto2[1];

		for (int i = 0; i < naves.length; i++) {
			String[] datos = naves[i].split("-");

			double combustible = Double.parseDouble(datos[2]);
			int nivelEnergia = Integer.parseInt(datos[3]);
			try {
				if (datos[0].equalsIgnoreCase("exploradora")) {
					Exploradora exp = new Exploradora(datos[1], combustible, nivelEnergia, null);
					listaNaves.add(exp);
					// System.out.println(exp);
				} else if (datos[0].equalsIgnoreCase("carga")) {
					if (datos.length != 5)
						throw new EstadisticaInvalidaException("ERROR. Te falta la carga");

					double carga = Double.parseDouble(datos[4]);
					Carga car = new Carga(datos[1], combustible, nivelEnergia, null, carga);
					listaNaves.add(car);
					// System.out.println(car);
				} else if (datos[0].equalsIgnoreCase("militar")) {
					if (datos.length != 5)
						throw new EstadisticaInvalidaException("ERROR. Te falta el blindaje");
					
					int blindaje = Integer.parseInt(datos[4]);
					Militar mil = new Militar(datos[1], combustible, nivelEnergia, null, blindaje);
					listaNaves.add(mil);
					// System.out.println(mil);
				} else {
					throw new NaveInvalidaException("ERROR. La nave es invalida");
				}
			} catch (NaveInvalidaException | EstadisticaInvalidaException e) {
				System.out.println(e.getMessage());
			}
		}

		for (int j = 0; j < destinos.length; j++) {
			String[] datos = destinos[j].split("-");
			double distancia = Double.parseDouble(datos[1]);
			Destino des = new Destino(datos[0], distancia);
			listaDestinos.add(des);
		}

		/*
		 * Mision m1 = new Mision("1", listaDestinos.get(1), 0.06); Mision m2 = new
		 * Mision("2", listaDestinos.get(1), 0.19); Mision m3 = new Mision("3",
		 * listaDestinos.get(0), 0.15); Mision m4 = new Mision("4",
		 * listaDestinos.get(0), 0.12); Mision m5 = new Mision("5",
		 * listaDestinos.get(1), 0.05); Mision m6 = new Mision("6",
		 * listaDestinos.get(0), 0.11); Mision m7 = new Mision("7",
		 * listaDestinos.get(0), 0.09); Mision m8 = new Mision("8",
		 * listaDestinos.get(1), 0.16); Mision m9 = new Mision("9",
		 * listaDestinos.get(1), 0.2); Mision m10 = new Mision("10",
		 * listaDestinos.get(1), 0.10); Mision m11 = new Mision("11",
		 * listaDestinos.get(0), 0.03); Mision m12 = new Mision("12",
		 * listaDestinos.get(1), 0.01);
		 * 
		 * listaMisiones.addAll(List.of(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11,
		 * m12));
		 */

		for (int i = 0; i < 12; i++) {
			double numeroAleatorio = Math.random() * 0.20;
			int indice = (int) (Math.random() * listaDestinos.size());

			Mision m0 = new Mision(i + 1, listaDestinos.get(indice), numeroAleatorio);
			listaMisiones.add(m0);

		}

		for (Nave nave : listaNaves) {
			double distanciaRecorrida = 0;
			EstadoMision ultimoEstado = EstadoMision.COMPLETADA;
			for (int j = 0; j < 3; j++) {

				if (ultimoEstado != EstadoMision.COMPLETADA && ultimoEstado != EstadoMision.FALLIDA) {
					break;
				}

				int numeroLista = (int) (Math.random() * listaMisiones.size());
				Mision mision = listaMisiones.get(numeroLista);
				EstadoMision estado;

				try {

					nave.viajar(mision.destino());

					double probabilidadExito = 70;

					if (nave instanceof Exploradora) {
						probabilidadExito += 15;
					}

					if (nave.getNivelEnergia() < 3) {
						probabilidadExito -= 10;
					}

					if (nave instanceof Militar) {
						int random = (int) (Math.random() * 100);
						if (random < 20) {
							System.out.println("Encuentro hostil");
							nave.setCombustible(nave.getCombustible() - 10);
						}
					}

					double riesgo = mision.riesgoAmbiental();

					if (nave instanceof Exploradora) {
						riesgo /= 2;
					}

					probabilidadExito -= (riesgo * 100); // RA funciona asi?

					int random = (int) (Math.random() * 100);

					if (random <= probabilidadExito) {
						System.out.println(nave.getNombre() + " completo la mision");
						estado = EstadoMision.COMPLETADA;
						distanciaRecorrida += mision.destino().distanciaAL();
					} else {
						System.out.println(nave.getNombre() + " fallo la mision");
						estado = EstadoMision.FALLIDA;
					}

				} catch (CombustibleInsuficienteException e) {
					System.out.println(e.getMessage());
					System.out.println(nave.getNombre() + " se quedo en deriva");
					estado = EstadoMision.DERIVA;
				}

				resultados.add(new ResultadoMision(nave, mision, estado)); // no se me ocurre otra forma

				ultimoEstado = estado;
			}

			distanciasRecorridas.put(nave.getNombre(), distanciaRecorrida); // PARA API
			combustibleRestante.add(nave.getCombustible()); // PARA API
			System.out.println("+++++++++REPORTE:++++++++++");
			nave.mostrarReporte();

			boolean puede = true;
			if (ultimoEstado == EstadoMision.DERIVA) {
				puede = false;
			} else {
				double distancia = nave.getUbicacionActual().distanciaAL();
				puede = nave.tieneAutonomia(distancia);
			}
			if (!puede) {
				resultados.add(new ResultadoMision(nave, null, EstadoMision.DERIVA));
				System.out.println(nave.getNombre() + " en deriva");
			} else {
				try {
					nave.setUbicacionActual(null);
					nave.repostar();
					System.out.println(nave.getNombre() + " regreso a base");
				} catch (FueraDeSectorException e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("-------------------------------------------");

		}

		// A partir de aqui esta lo de landa:

		Map<EstadoMision, Long> mapaEstados = resultados.stream()
				.collect(Collectors.groupingBy(ResultadoMision::estado, Collectors.counting()));

		List<String> navesDeriva = resultados.stream().filter(n -> n.estado() == EstadoMision.DERIVA)
				.map(n -> n.nave().getNombre()).distinct().toList();

		System.out.println("Naves en deriva: ");
		navesDeriva.forEach(n -> System.out.println(n));
		System.out.println("---------------------------");

		System.out.println("Cuantas misiones acabaron de tal forma:");
		System.out.println(mapaEstados);

		List<Map.Entry<String, Double>> listaEntry = new ArrayList<>(distanciasRecorridas.entrySet()); // TODO: no creo
		listaEntry.sort(Map.Entry.comparingByValue());

		System.out.println("----------------------------");
		System.out.println(listaEntry);
		System.out.println("La nave con mas distancia recorrida es: " + listaEntry.getLast().getKey());

		double promedio = combustibleRestante.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
		System.out.println("------------------------------");
		System.out.println(combustibleRestante);
		System.out.println("El promedio de combustible restante es: " + promedio);

	}

}