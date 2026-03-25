package com.gsd.programacion;

public class App {
	public static void main(String[] args) {

//guerrero-Thor-15-20-3-3 mago-Merlin-45-25-4-5
//guerrero-Thor-15-20-3-3 mago-Merlin-45-25-19-5 POCIONES MAL
//cantante-Thor-15-20-3-3 mago-Merlin-45-25-4-5 TIPO INVALIDO

		Personaje[] arrayP = new Personaje[2];

		for (int i = 0; i < args.length; i++) {
			String personaje = args[i];
			String[] campos = personaje.split("-");

			int ataque = Integer.parseInt(campos[2]);
			int defensa = Integer.parseInt(campos[3]);
			int nivel = Integer.parseInt(campos[4]);
			int pociones = Integer.parseInt(campos[5]);

			try {
				if (campos[0].equalsIgnoreCase("Guerrero")) {
					Guerrero guerrero = new Guerrero(campos[1], ataque, defensa, nivel, pociones);
					guerrero.mostrarInfo();
					arrayP[i] = guerrero;
					continue;
				} else if (campos[0].equalsIgnoreCase("Mago")) {
					Mago mago = new Mago(campos[1], ataque, defensa, nivel, pociones);
					mago.mostrarInfo();
					arrayP[i] = mago;
					continue;
				} else if (campos[0].equalsIgnoreCase("Arquero")) {
					Arquero arquero = new Arquero(campos[1], ataque, defensa, nivel, pociones);
					arquero.mostrarInfo();
					arrayP[i] = arquero;
					continue;
				} else {
					throw new PersonajeInvalidoException("ERROR. El tipo de personaje tiene que ser: Arquero, Mago o Guerrero");
				}

			} catch (PersonajeInvalidoException | EstadisticaInvalidaException e) {
				System.out.println(e.getMessage());
				return;
			} catch (Exception e ) {
				System.err.println(e.getMessage());
				return;
			}
		}

		int atacante = 0;
		int defensa = 1;
		int turno = 0;
		
		System.out.println("-----------------------\nBATALLA INICIADA");
		do {
			for (int i = 0; i < 2; i++) {
				if (turno % 2 == 0) {
					atacante = 0;
					defensa = 1;
				} else {
					atacante = 1;
					defensa = 0;
				}
				double random = Math.random();

				if (arrayP[atacante] instanceof Mago && ((Mago) arrayP[atacante]).autoCurarse() == true) {
					turno++;
					continue;
				}

				if (!(arrayP[atacante] instanceof Mago) && arrayP[atacante].autoCurar() == true) {
					turno++;
					continue;
				}
				
				if (random > 0.5) {
					arrayP[atacante].atacar(arrayP[defensa]);
					turno++;
					continue;
				} else {
					if (arrayP[atacante] instanceof Guerrero) {
						((Guerrero) arrayP[atacante]).golpeCritico(arrayP[defensa]);
						turno++;
						continue;
					}
					if (arrayP[atacante] instanceof Mago) {
						((Mago) arrayP[atacante]).lanzarHechizo(arrayP[defensa]);
						turno++;
						continue;
					}
					if (arrayP[atacante] instanceof Arquero) {
						((Arquero) arrayP[atacante]).disparoPreciso(arrayP[defensa]);
						turno++;
						continue;
					}
				}
			}
			System.out.println("--FIN TURNO -" + arrayP[0].getNombre() + ": " + arrayP[0].getSalud() + " -"
					+ arrayP[1].getNombre() + ": " + arrayP[1].getSalud());
		} while (arrayP[0].estaVivo() && arrayP[1].estaVivo());

		if (arrayP[0].estaVivo()) {
			System.out.println("GANADOR: " + arrayP[0].getNombre());
		} else {
			System.out.println("GANADOR: " + arrayP[1].getNombre());
		}

	}
}