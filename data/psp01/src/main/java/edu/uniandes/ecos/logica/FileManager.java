package edu.uniandes.ecos.logica;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;

public class FileManager {

	/**
	 * Ruta del directorio de proyectos
	 */
	private String path;
	
	public FileManager(String path) {
		this.path = path;
	}

	/**
	 * Se encarga de recorrer el directorio identificando los proyectos y
	 * procesar sus archivos.
	 */
	public void processProjects(){
		File root = new File( this.path );
        File[] projectList = root.listFiles();

        HashMap<String, Integer> resultado = new HashMap<String, Integer>(); 
                
        for (File project: projectList){
        	resultado.put(project.getName(),  this.inspectDir(project, 0));
        	System.out.println(project.getName() +"-----"+ resultado.get(project.getName()));
        }
        
	}
	
	/**
	 *  Busca los archivos .java en el directorio para contar sus líneas.
	 *  @param file archivo o directorio para inspeccionar.
	 */
	private int inspectDir(File file, int totalLines) {
	     File[] list = file.listFiles();
        
        if (list != null ){
        	for ( File f : list ) {
                if ( f.isDirectory() ) {
                	totalLines = inspectDir(f, totalLines);
                } else {
                	Counter counter = new Counter();
                    if(f.getName().endsWith(".java")){
                    	try {
                    		totalLines += counter.countLines(Files.readAllLines(f.toPath()));
                		} catch (Exception e) {
                			System.out.println("Error: "  + e.getMessage());
                		}
                   }
                    	
                }
            }	
        }  

         return totalLines;
     }
	
}
