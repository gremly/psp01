package edu.uniandes.ecos.presentacion;

import edu.uniandes.ecos.logica.FileManager;

/**
 * Clase principal del programa
 * */
public class App 
{
	/**
	 * Encargado de generar el conteo de las líneas de código
	 * encontradas en cada proyecto de la ruta especificada.
	 * @param path Ruta al directorio que contiene los proyectos.
	 */
	public void countLoc(String path){
		FileManager fm = new FileManager(path);
		fm.processProjects();
	}
	
    public static void main( String[] args )
    {
        App app = new App();
        app.countLoc("./data/");
    }
}
