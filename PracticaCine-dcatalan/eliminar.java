package com.gsd.programacion;

/**
 * Hello world!
 *
 */
public class App 
{
	static final int PEPITO = 6;
    public static void main( String[] args )
    {
		int numero,a,b,c,d;
        numero = 2;
	System.out.println(numero); //esto es un comentario
	a=0;
	b=numero;
	c=a;
	d=a+b+c+numero;
	
	float p =1.78f; // float siempre tiene que ir acompañado de una f al final del num
	
	double grande;
	grande = p;
	
	char letra; // esta asociado a la tabla ASCII (si pones a en tipo int te da 97) 
	letra = 'a'; // no pongas doble comilla ahi
	letra++; // te pondria b o 98 dependiendo si es char o int
	
	boolean si;
	si = true;
	
	int prueba = 5;
	
	System.out.println(prueba++); 	//5	(print con ln hace salto de linea, sin él no)
	System.out.println(prueba);	//6
	System.out.println(++prueba);	//7
	System.out.println(prueba);	//7
	
	String cadena = "Hola";
	String cadena2 = "Hola";
	
	
	System.out.println(cadena==cadena2); //sale true
	System.out.println(cadena.equals(cadena2)); //sale true
        System.out.println( "Hello World!" );
    }
}
