package edu.uniandes.ecos.logica;

import java.io.*;
import java.util.Properties;

public class DataSource {

	/**
	 * Arreglo de properties ledias del archivo
	 */
	private String[] datos;

	/**
	 * Carga archivo properties con los datos para el cálculo
	 * @param rutaArchivo path del archivo a cargar
	 */
	public void cargarArchivo(String rutaArchivo) {
		
		try {
			FileInputStream fis = new FileInputStream(new File(rutaArchivo));
			Properties properties = new Properties();
			properties.load(fis);
			int cantidad = Integer.parseInt(properties.getProperty("datasets.cantidad"));
			this.datos = new String[cantidad];
			
			for (int i = 0; i < cantidad; i++) {
				String datos = properties.getProperty("dataset" + (i+1) + ".datos");
				this.datos[i] = datos;
			}
			
		} catch (Exception e) {
			System.out.println("Error: "  + e.getMessage());
		}

	}
	/**
	 * Retorna el arreglo de properties cargados 
	 * @return Arreglo de strings con datos cargados.
	 */
	public String[] getDatos() {
		return datos;
	}
}
