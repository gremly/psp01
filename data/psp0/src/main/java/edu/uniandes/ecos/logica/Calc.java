package edu.uniandes.ecos.logica;

import java.util.LinkedList;

/**
 *  Clase para generar el cálculo de la desviación estandar y la média aritmética. 
 */
public class Calc {

	/**
	 * Atributos
	 */
	 private LinkedList<Double> data;
	 
	/**
	 *  Constructor de la clase
	 *  @param data Lista enlazada con datos para el cálculo.
	 */
	public Calc (LinkedList<Double> data) {
		this.data = data;
	}
	 
	/**
	 *  Métodos 
	 */
	/**
	 * Calcula la media aritmética tomado como fuente data
	 * @return double con la desviación estandar.
	 */
	public double calcularMedia(){
		double media = 0;
		
		for (Double num :  this.data) {
			media += num;
		}
		
		media = media / this.data.size();
		return media;
	}
	
	/**
	 * Calcula la desviación estándar tomado como fuente data
	 * @return double con la desviación estandar.
	 */
	public double calcularDesviacion(){
		double desviacion = 0;
		double divisor = 0;
		double media = this.calcularMedia();
		
		for (Double num : this.data) {
			divisor += Math.pow((num - media), 2);
		}
		
		desviacion = Math.sqrt(divisor / (this.data.size() -1) ); 
		return desviacion;
	}
}
