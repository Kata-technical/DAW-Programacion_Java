package com.gsd.programacion;

public class Pelicula {

	public String getTitulo() {
		return titulo;
	}

	public double getDuracion() {
		return duracion;
	}

	public int getEdadMinima() {
		return edadMinima;
	}

	public String getDirector() {
		return director;
	}

	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", duracion=" + duracion + ", edadMinima=" + edadMinima + ", director="
				+ director + "]";
	}

	private String titulo;
	private double duracion;
	private int edadMinima;
	private String director;
	
	public Pelicula(String titulo, double duracion, int edadMinima, String director) {
		this.titulo = titulo;
		this.duracion = duracion;
		this.edadMinima = edadMinima;
		this.director = director;
	}
	
	public Pelicula () {
		
		String[] titulos = {"Avatar","Terminator 2","Interestelar","Openheimer","Barbie","Bladerunner2049","Torrente"};
		int randomStr = (int)(Math.random()*7);
		this.titulo = titulos[randomStr];
		
		double randomDur = (1+Math.random()*(3-1));
		this.duracion = Math.round(randomDur*100)/100.0;
		
		int random = (int)(Math.random()*19);
		this.edadMinima = random;
		
		String[] directores = {"Steven","Tarantino","Martin","Christopher","Scorsese","Stanley","Francis"};
		int randomStr2 = (int)(Math.random()*7);
		this.director = directores[randomStr2];
	}

}
