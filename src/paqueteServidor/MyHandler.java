package paqueteServidor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class MyHandler implements HttpHandler{
	
	private static ArrayList<String> listaDatos;
	private static URI uri = null;
	private static OutputStream os = null;
	private static String tipoMensaje = "";
	private static String mensajeUsuario = "";
	private static String respuesta = "";
	private static String mensajeLimpio = "";
	private static int contador = 0;
	final static int CODIGO_RESPUESTA = 200;
	
	@Override
	public void handle(HttpExchange t) throws IOException {
		uri =t.getRequestURI();		
		mensajeUsuario = uri.getQuery();
		tipoMensaje = uri.getPath();
		listaDatos = new ArrayList<String>();
		
		identificarMensaje(tipoMensaje);

		t.sendResponseHeaders(CODIGO_RESPUESTA, respuesta.length());
		os = t.getResponseBody();
		os.write(respuesta.getBytes());
		os.close();
	}
	
	// Método que identifica el tipo de acción indicada por el usuario y llama a los métodos que realiza la acción apropiada
	public static String identificarMensaje (String tipoMensaje) {

		if(mensajeUsuario!=null) {		
			if (tipoMensaje.equals("/almacena")) {			
				// Llamamos al método que guarda en fichero el mensaje introducido por el usuario
				AccesoFichero.escribirFichero(mensajeUsuario);
				// Indicamos la respuesta que le daremos al usuario
				respuesta = "Mensaje \"" + mensajeUsuario + "\" almacenado" + " -> ["+ CODIGO_RESPUESTA+"OK]";
				
			} else if (tipoMensaje.equals("/consulta")) {
				// Trasformamos el mensaje introducido por el usuario a minusculas y eliminamos las tildes
				mensajeLimpio = limpiarDato(mensajeUsuario);
				// Guardamos en un array los datos que leemos del fichero
				listaDatos = AccesoFichero.leerFichero();
				// Llamamos al método que contabiliza las veces que encontramos en el fichero el mensaje introducido por el usuario
				comrpobarMensaje (listaDatos);
				// Indicamos la respuesta que le daremos al usuario
				respuesta = "Mensaje \"" + mensajeUsuario + "\" -> ["+ CODIGO_RESPUESTA+"OK] "+ contador + " coincidencias";
			}
		} else {
			respuesta = "La estructura del mensaje es incorrecta, debe introducir: \"/almacena?palabra\" o \"/consulta?palabra\"";
		}
		System.out.println(respuesta);
		return respuesta;
	}
	
	// Método que contabiliza las coincidencias de la palabra introducida con las guardadas en el fichero
	public static void comrpobarMensaje (ArrayList<String> listaDatos) {
		String datoLimpio = "";
		contador = 0;
		for (String datoUsuario : listaDatos) {
			datoLimpio=limpiarDato(datoUsuario);	
			if (datoLimpio.equals(mensajeLimpio)) {
				contador++;
			}
		}
	}
	
	// Método que pasa las letras a minúsculas y llama al método que elimina Tildes
	public static String limpiarDato(String dato) {
		String datoLimpio = borrarTilde(dato);
		datoLimpio=datoLimpio.toLowerCase();		
		return datoLimpio;		
	}
	
	// Método para eliminar las tildes
    public static String borrarTilde(String palabraOrigen) {
        String conTilde = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿĀāĂăĄąĆćĈĉĊċČčĎďĐđĒēĔĕĖėĘęĚěĜĝĞğĠġȑȒȓȔȕȗȘș";
        String sinTilde = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuybyAaAaAaCcCcCcCcDdDdEeEeEeEeEeGgGgGgrRrUuuSs";
        if (conTilde.length() != sinTilde.length()) {
            throw new RuntimeException("Revise las cadenas para la sustituciÃ³n de acentos, longitudes diferentes");
        }
        int ejemplares = conTilde.length();
        String palabraRetorno = palabraOrigen;
        for (int i = 0; i < ejemplares; i++) {
        	palabraRetorno = palabraRetorno.replace(conTilde.charAt(i), sinTilde.charAt(i));
        }
        return palabraRetorno;
    }
}
