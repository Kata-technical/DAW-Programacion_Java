package com.gsd.programacion;

//import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;
//import java.util.Scanner;

public class App {
	public static void main(String[] args) {


	/*	int[][] arrayA = 
			
				   { { 1, 0, 1, 0 },
				     { 0, 4, 0, 1 } };
	
		int[][] arrayB = 
			
						 { { 1 },
						   { 5 }, 
						   { 7 }, 
						   { 9 } };*/

        int[][] arrayA = {{1,2},
        				  {0,-1},
        				  {0,1}};
        
        int[][] arrayB = {{0,3,0},
        				  {3,0,3}};
 
		
		int filasA = arrayA.length;
		int columnasA = arrayA[0].length;

		int filasB = arrayB.length;
		int columnasB = arrayB[0].length;

		
		int filasSOL = filasA;
		int columnasSOL = columnasB;

		if (columnasA != filasB) {
			System.err.println("Pon bien las matrices manin");
			System.exit(1);
		}

		int[][] solucion = new int[filasSOL][columnasSOL];
		
		// fila por columna + fila por columna + fila por columna + etcc

		int x = 0;
		int y = 0;

		
			for (int j = 0; j < filasSOL; j++) { 
				for(int i = 0; i < columnasB; i++) { 
					x = 0;
					y = 0;
					for (int k = 0; k < filasB; k++) { 
						x = arrayA[j][k] * arrayB[k][i];
						y = y + x;
						System.out.println("X: "+x);
						System.out.println("Y: "+y);
						
						solucion[j][i] = y;
					}
					System.out.println(" ");
				}
				System.out.println("------");
			}
		

		
		// Printar el resultado
		System.out.println("SOLUCION DE LA MULTIPLICACION: ");
		for (int i = 0; i < filasSOL; i++) {
			for (int j = 0; j < columnasSOL; j++) {
				System.out.print(solucion[i][j] + " "); // chequeo de array
			}
			System.out.println("");
		}
		
		/*	SOLUCION: 
		 * 6 3 6
		  -3 0 -3
		   3 0 3
		 */
		
	}
}