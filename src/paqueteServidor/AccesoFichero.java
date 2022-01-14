package paqueteServidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class AccesoFichero {

	private static ArrayList<String> listaDatos;
	private static String rutaFichero = "datos.txt";

	// Método que comprueba si el fichero existe, sino lo crea y alamacena en el la palabra introducida
	public static void escribirFichero(String mensaje) {
		FileWriter fichero = null;
		try {
			fichero =new FileWriter (rutaFichero, true);
		} 
		catch (IOException e){ 
			System.out.println("No se pudo acceder al fichero");
		}
		BufferedWriter buffer = new BufferedWriter(fichero);
		try {
			buffer.write(mensaje);
			buffer.newLine();
		} catch (IOException e) {
			System.out.println("Error al escribir en el fichero");
			System.out.println(e.getMessage());
		}
		try {
			buffer.close();
			fichero.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar el fichero");
			System.out.println(e.getMessage());
		}
	}
	
	// Método que lee el fichero y almacena la información en un array
	public static ArrayList<String> leerFichero() {
		listaDatos = new ArrayList<String>();
		FileReader fichero = null;
		try {
			fichero = new FileReader(rutaFichero);
		} catch (IOException e) {
			System.out.println("No se puede abrir el fichero");
		}
		BufferedReader buffer = new BufferedReader(fichero);
		String linea="";
		try {
			linea = buffer.readLine();
			while (linea!=null) {
			listaDatos.add(linea);
			linea = buffer.readLine();			
			}
		} catch (IOException e) {
			System.out.println("Error al leer el fichero");
		}
		try {
			buffer.close();
			fichero.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar el fichero");
		}
		return listaDatos;
	}
}
