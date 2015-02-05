package edu.uniandes.ecos.logica;

import java.util.List;

/**
 * Clase contador encargada de validar las líneas físicas y contarlas.
 */
public class Counter {

	/**
	 * Dada una lista de líneas las tiene en cuenta si son válidas.
	 * @param lines listado de lineas obtenidas de un archivo.
	 * @return retorna el número de líneas válidas.
	 */
	public int countLines(List<String> lines){
		int counter = 0;
		
		for (String line: lines){
			if (isValidLine(line)){
				counter++;	
			}
		}
		 
		return counter;
	}
	
	/**
	 * Valida que una línea sea o no tenida en cuenta en el conteo
	 * @param line Línea para ser validada.
	 * @return Bandera que indica si la línea ingresada es válida.
	 */
	private boolean isValidLine(String line){
		boolean isValid = true;	
		
		if (line.isEmpty()) {
			isValid = false;
		} else if (line.trim().startsWith("/*") ||  line.trim().startsWith("//") || line.trim().startsWith("*")){
			isValid = false;
		} else if (line.trim().equals("}")) {
			isValid = false;
		}
		
		return isValid;
	}
}
