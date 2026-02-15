package com.gsd.programacion;

public class App {
	public static void main(String[] args) {

//guerrero-Thor-15-20-3-3-8 mago-Merlin-45-25-4-5-7

		Personaje[] arrayP = new Personaje[2];

		for (int i = 0; i < args.length; i++) {
			String personaje = args[i];
			String[] campos = personaje.split("-");

			int vida = Integer.parseInt(campos[2]);
			int ataque = Integer.parseInt(campos[3]);
			int defensa = Integer.parseInt(campos[4]);
			int pociones = Integer.parseInt(campos[5]);
			int nivel = Integer.parseInt(campos[6]);
			
			try {
				if (campos[0].equalsIgnoreCase("Guerrero")) {
					Guerrero guerrero = new Guerrero(campos[1], vida, ataque, defensa, pociones, nivel);
					guerrero.mostrarInfo();
					arrayP[i] = guerrero;
					continue;
				} else if (campos[0].equalsIgnoreCase("Mago")) {
					Mago mago = new Mago(campos[1], vida, ataque, defensa, pociones, nivel);
					mago.mostrarInfo();
					arrayP[i] = mago;
					continue;
				} else if (campos[0].equalsIgnoreCase("Arquero")) {
					Arquero arquero = new Arquero(campos[1], vida, ataque, defensa, pociones, nivel);
					arquero.mostrarInfo();
					arrayP[i] = arquero;
					continue;
				} else {
					throw new PersonajeInvalidoException("error");
				}

			} catch (PersonajeInvalidoException | EstadisticaInvalidaException e) {
				System.out.println(e.getMessage());
				return;
			}
		}

		int atacante = 0;
		int defensa = 1;
		int turno = 0;
		int daño = 0;

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
				if (arrayP[atacante].autoCurar() == true) {
					System.out.println(arrayP[atacante].getNombre() + " se ha curado!");
					turno++;
					continue;
				}
				if (random > 0.5) {
					daño = arrayP[atacante].atacar(arrayP[defensa]);
					arrayP[defensa].defender(daño);
					System.out.println(arrayP[atacante].getNombre() + " ha atacado a " + arrayP[defensa].getNombre()
							+ " causandole " + daño + " de daño");
					turno++;
					continue;
				} else {
					if (arrayP[atacante] instanceof Guerrero) {
						daño = ((Guerrero) arrayP[atacante]).golpeCritico(arrayP[defensa]);
						arrayP[defensa].defender(daño);
						System.out.println(arrayP[atacante].getNombre() + " ha causado un golpe critico a "
								+ arrayP[defensa].getNombre() + " causandole " + daño + " de daño");
						turno++;
						continue;
					}
					if (arrayP[atacante] instanceof Mago) {
						daño = ((Mago) arrayP[atacante]).lanzarHechizo(arrayP[defensa]);
						System.out.println(arrayP[atacante].getNombre() + " le ha lanzado un hechizo a "
								+ arrayP[defensa].getNombre() + " causandole " + daño + " de daño");
						turno++;
						continue;
					}
					if (arrayP[atacante] instanceof Arquero) {
						daño = ((Arquero) arrayP[atacante]).disparoPreciso(arrayP[defensa]);
						arrayP[defensa].defender(daño);
						System.out.println(arrayP[atacante].getNombre() + " le ha disparado a "
								+ arrayP[defensa].getNombre() + " causandole " + daño + " de daño");
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