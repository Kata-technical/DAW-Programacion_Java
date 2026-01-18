package com.gsd.programacion;

/**
 * Hello world!
 *
 */

public class App {

	public static void main(String[] args) {


		
		if (args.length != 2) {
			System.err.println("ERROR. Se requieren exactamente 2 argumentos");
			return;
		}

		// VARIABLES PARA PASAR A INT

		int mascaraInt = 0;
		String ipcadena = args[0];
		String mascara = args[1];
		String[] arrayauxIP = ipcadena.split("\\.");
		int[] arrayIP = new int[4];

		// VALIDACIONES

		// COMPROBACIONES DE ARGS
		if (arrayauxIP.length != 4) {
			System.err.println("ERROR. Tiene que haber exactamente 4 octales para que sea una IP válida.");
			return;
		}

		// COMPROBACIONES DE LA IP
		for (int i = 0; i < arrayauxIP.length; i++) {
			try {
				arrayIP[i] = Integer.parseInt(arrayauxIP[i]);
			} catch (Exception e) {
				System.err.println("ERROR: Solo se admiten numeros en la IP");
				return;
			}
		}

		if (arrayIP[0] == 0 || arrayIP[0] == 255) {
			System.err.println("ERROR. El primer octal no debe ser 0 ni 255");
			return;
		}

		for (int i = 0; i < arrayIP.length; i++) {
			if (arrayIP[i] > 255 || arrayIP[i] < 0) {
				System.err.println("ERROR. Un octal no debe ser superior a 255 ni inferior a 0");
				return;
			}
		}

		// COMPROBACIONES DE MASCARA
		try {
			mascaraInt = Integer.parseInt(mascara);
		} catch (Exception e) {
			System.err.println("ERROR: La mascara solo admite numeros enteros");
			return;
		}

		if (mascaraInt > 32 || mascaraInt == 0 || mascaraInt < 0) {
			System.err.println("ERROR. La mascara de red no puede ser superior a 32 ni ser 0 ni ser negativa");
			return;
		}

		// SALIDAS:

		char clase = ' ';
		Boolean Subnetting = false;
		Boolean Supernetting = false;

		if (arrayIP[0] >= 0 && arrayIP[0] <= 127) {
			clase = 'A';
			if (mascaraInt > 8) {
				Subnetting = true;
			} else if (mascaraInt < 8) {
				Supernetting = true;
			}
		} else if (arrayIP[0] >= 128 && arrayIP[0] <= 191) {
			clase = 'B';
			if (mascaraInt > 16) {
				Subnetting = true;
			} else if (mascaraInt < 16) {
				Supernetting = true;
			}
		} else if (arrayIP[0] >= 192 && arrayIP[0] <= 223) {
			clase = 'C';
			if (mascaraInt > 24) {
				Subnetting = true;
			} else if (mascaraInt < 24) {
				Supernetting = true;
			}
		} else if (arrayIP[0] >= 224 && arrayIP[0] < 240) {
			clase = 'D';
		} else if (arrayIP[0] >= 240 && arrayIP[0] < 256) {
			clase = 'E';
		} else {
			System.err.println("ERROR. No se admiten más de 255 o numeros negativos");
			return;
		}
		
		// EL RESULTADO

		System.out.println("NOTACION CIDR: " + arrayIP[0] + "." + arrayIP[1] + "." + arrayIP[2] + "." + arrayIP[3] + "/" + mascaraInt);
		System.out.println("CLASE: " + clase);
		if (Subnetting) {
			System.out.println("Subnetting: " + Subnetting);
		} else if (Supernetting) {
			System.out.println("Supernetting: " + Supernetting);
		} else {
			System.out.println("Subnetting: " + Subnetting);
		}
		
		
	}
}