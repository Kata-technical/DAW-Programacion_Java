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
	private static final String URL = "jdbc:mariadb://localhost:3306/taller_mecanico"; //la URL de conexion
	private static final String USUARIO = "root"; // nombre de usuario de la base de datos
	private static final String PASSWORD = ""; // su clave

	public static void main(String[] args) {
		String tabla = "mecanicos"; 

		try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD)) { 
			System.out.println("Conectado con exito"); //este bloque crea la conexion automaticamente y la cierra al terminar
			// siempre y cuando no haya fallado la conexion claro

			Statement start = conn.createStatement(); //aqui crea un objeto Statement vinculado a la conexion para ejecutar SQL
			ResultSet rs = start.executeQuery("SELECT * FROM " + tabla); //ejecuta un SELECT sobre la tabla mecanicos y guarda los resultados en rs

			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData(); //te da los metadatos de rs

			int column = meta.getColumnCount(); //el numero total de columnas que devolvio la consulta

			for (int i = 1; i <= column; i++) {
				System.out.printf("%-20s", meta.getColumnName(i));
			} //imprime el nombre de cada columna con formato de 20 caracteres alineado a la izquierda
			System.out.println();
			System.out.println("-".repeat(column * 20)); //imprime una linea separadora de guiones

			while (rs.next()) { //mientras pueda avanzar al siguiente registro pues:
				for (int i = 1; i <= column; i++) {
					System.out.printf("%-20s", rs.getString(i)); // imprime el valor de la celda como String con 20 caracteres de ancho
				}
				System.out.println();
			}

			System.out.println("---------------------------------------------------");
			start.executeQuery("INSERT INTO mecanicos (NIF, Nombre, Apellido1, Apellido2, Fecha_Nacimiento, Direccion, Provincia, Sueldo) VALUES ('555555B', 'JAVI', 'SANZ', 'MELERO', '1987-06-08', 'Su calle', 'Asturias', 3500.00 )");
			System.out.println("Insertado");
			//aqui inserto un nuevo mecanico en la tabla mecanicos

			System.out.println("---------------------------------------------------");
			start.executeQuery("UPDATE vehiculos SET Color = 'ROSA' WHERE Matricula = '5333AAA'");
			System.out.println("Actualizado");
			//aqui actualizo un atributo (el color del coche)

			System.out.println("---------------------------------------------------");
			start.executeQuery("ALTER TABLE averias ADD mecanico varchar(48)");
			System.out.println("tabla modificada");
			//aqui añado un nuevo atributo a la tabla de averias
			
			System.out.println("---------------------------------------------------");
			start.execute("DELETE FROM mecanicos WHERE Sueldo < 1500");
			System.out.println("Eliminado");
			//aqui pues borro todos los mecanicos con sueldo menor a 1500
			
		} catch (SQLException e) {
			System.out.println("Conexion fallida");
		} // si falla la conexion pues se mete aqui

	}
}