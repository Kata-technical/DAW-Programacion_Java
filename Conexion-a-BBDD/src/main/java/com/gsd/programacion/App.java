package com.gsd.programacion;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class App {
	private static final String URL = "jdbc:mariadb://localhost:3306/taller_mecanico";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "";

	public static void main(String[] args) {
		String tabla = "mecanicos";

		try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
			System.out.println("Conectado con exito");

			Statement start = conn.createStatement();
			ResultSet rs = start.executeQuery("SELECT * FROM " + tabla);

			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();

			int column = meta.getColumnCount();

			for (int i = 1; i <= column; i++) {
				System.out.printf("%-20s", meta.getColumnName(i));
			}
			System.out.println();
			System.out.println("-".repeat(column * 20));

			while (rs.next()) {
				for (int i = 1; i <= column; i++) {
					System.out.printf("%-20s", rs.getString(i));
				}
				System.out.println();
			}

			System.out.println("---------------------------------------------------");
			//start.executeQuery("INSERT INTO mecanicos (NIF, Nombre, Apellido1, Apellido2, Fecha_Nacimiento, Direccion, Provincia, Sueldo) VALUES ('555555B', 'JAVI', 'SANZ', 'MELERO', '1987-06-08', 'Su calle', 'Asturias', 3500.00 )");
			System.out.println("Insertado");

			System.out.println("---------------------------------------------------");
			//start.executeQuery("UPDATE vehiculos SET Color = 'ROSA' WHERE Matricula = '5333AAA'");
			System.out.println("Actualizado");

			System.out.println("---------------------------------------------------");
			//start.executeQuery("ALTER TABLE averias ADD mecanico varchar(48)");
			System.out.println("tabla modificada");
			
			System.out.println("---------------------------------------------------");
			start.execute("DELETE FROM mecanicos WHERE Sueldo < 1500");
			System.out.println("Eliminado");
			
		} catch (SQLException e) {
			System.out.println("Conexion fallida");
		}

	}
}