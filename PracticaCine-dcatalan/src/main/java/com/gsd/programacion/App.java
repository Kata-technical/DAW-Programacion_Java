package com.gsd.programacion;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
			
			Cine yelmo = new Cine(3);
			yelmo.getSalas()[2].getPelicula().setEdadMinima(53);
			yelmo.getSalas()[1].generarEspectadores();
			yelmo.getSalas()[0].generarEspectadores();
			yelmo.getSalas()[2].generarEspectadores();
			
			int valor = 0;
			for (int i = 0; i < yelmo.getSalas()[valor].getAsientos().length; i++) {
				for (int j = 0; j < yelmo.getSalas()[valor].getAsientos()[0].length; j++) {
					if (yelmo.getSalas()[valor].getAsientos()[i][j].isOcupado()) {
					System.out.println(yelmo.getSalas()[valor].getAsientos()[i][j].getEspectador());
					}
				}
			}
			
			for (int i = 0; i < yelmo.getSalas().length; i++) {
				System.out.println(" ------------------------- ");
			yelmo.getSalas()[i].mostrarPatio();
			}
	}
}