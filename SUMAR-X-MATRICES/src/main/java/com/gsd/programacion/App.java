package com.gsd.programacion;

import java.util.Arrays;

//import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;
//import java.util.Scanner;


/**
 * Suma las matrices dinamicamente que tu le digas por argumentos.
 * Para usarlo tienes que meter en argumentos: [dimensiones, {fila1 de array1}{fila1 de array1}{fila1 de array2...}]
 * EJEMPLO: [2x2, 1,3, -5,9, 11,-3, 4,7].
 * EJEMPLO: [3x3, 4,7,-2, 1,5,2, 1,0,3, 5,2,3, -6,-4,1, 8,-8,1, 9,1,5 4,4,4 -2,-1,7 ] suma 3 matrices de 3x3.
 * Mejorable con funciones.
 */

public class App {
	public static void main(String[] args) {

		System.out.println("Argumentos: "+Arrays.toString(args));
		String dimension = args[0];
		System.out.println("Dimensiones: "+dimension); // devuelve 2x2
		String[] numeros_dimension = dimension.split("x"); // devuelve un array con 2 elementos [2,2]
		// System.out.println(Arrays.toString(numeros_dimension));


		int columnas = Integer.parseInt(numeros_dimension[0]);
		int filas = Integer.parseInt(numeros_dimension[1]);
		// int numPosiciones = args.length; // Ignorar

		String[][] sum1 = new String[columnas][filas];
		String[][] sum2 = new String[columnas][filas];
		int[][] sol = new int[columnas][filas];

		
		//DEFINIR LA LONGITUD DE 'ARRAY'
		String elementoArgs;
		int posiciones = 0;
		for (int i = 1; i < args.length; i++) {
			elementoArgs = args[i]; // 2,5 etc
			String[] cadena = elementoArgs.split(",");
			for (int j = 0; j < cadena.length; j++) {
				posiciones++;
			}
		}
		System.out.println("Posiciones de Array: "+posiciones);
		
		
		String elemento;
		String[] array = new String[posiciones];
		int cont = 0;
		
		
		// ARRAY NUEVO PARA GUARDAR LOS ARGS COMO QUIERA

		for (int i = 1; i < args.length; i++) { // hasta 4
			elemento = args[i]; // 2,5 etc
			String[] cadena2 = elemento.split(","); // [2, 5, 11]...
			for (int j = 0; j < cadena2.length; j++) { //longitud de cadena es 3 en este caso
				array[cont] = cadena2[j];
				cont++; //al salir la primera vez aqui, ahora cont seria 3
			}
		}
		
		
		
		System.out.println("El Array lineal: ");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " "); // chequeo de array
		}

		
		// AÑADIR LOS ELEMENTOS EN LAS POSICIONES DE LAS MATRICES
		
		System.out.println(" ");
		System.out.println("Array 'sum1': ");
		
		
		int pos = 0;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (pos % 2 == 0) {
					sum1[i][j] = array[pos];
					pos = pos + 2;
				}
			}
		}

		pos = 1;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (pos % 2 != 0) {
					sum2[i][j] = array[pos];
					pos = pos + 2;
				}
			}
		}

		// CHEQUEO DE SUM1 Y SUM2
		System.out.println("");

		for (int i = 0; i < sum1.length; i++) {
			for (int j = 0; j < sum1.length; j++) {
				System.out.print(sum1[i][j] + " "); // chequeo de array
			}
			System.out.println("");
		}
		System.out.println("");
		for (int i = 0; i < sum2.length; i++) {
			for (int j = 0; j < sum2.length; j++) {
				System.out.print(sum2[i][j] + " "); // chequeo de array
			}
			System.out.println("");
		}

		// SUMAR MATRICES

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				sol[i][j] = Integer.parseInt(sum1[i][j]) + Integer.parseInt(sum2[i][j]);
			}
		}

		System.out.println("");
		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < sol.length; j++) {
				System.out.print(sol[i][j] + " "); // chequeo de array
			}
			System.out.println("");
		}

	}

}