package edu.uniandes.ecos.presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import edu.uniandes.ecos.logica.FileManager;

/**
 * Clase principal del programa
 * */
public class App extends HttpServlet {
	
	private Map<String, Integer> projects;
	
	
	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {

	    if (req.getRequestURI().endsWith("/")) {
	      showHome(req,resp);
	    } else {
	    	showNotFound(req, resp);
	    }
	  }
	  
	  private void showHome(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		  	this.countLoc("./data/");
		  	PrintWriter pw = resp.getWriter();
		  	
		  	pw.write("<html>");
		  	pw.write("<head></head>");
		  	pw.write("<body>");
		  	pw.println("<h3> Contador de líneas </h3>");
		  	pw.write("<table style=\"border: 1px solid black;\">");
		  	pw.write("<tr><th>Projecto</th><th>Número de líneas</th></tr>");
		  	
		  	for (Map.Entry<String, Integer>project: this.projects.entrySet()) {
		  		pw.write("<tr>");
		  		pw.write("<th>"+project.getKey()+ "</th>");
		  		pw.write("<td style=\"text-align: center;\">"+project.getValue()+ "</td>");
		  		pw.write("</tr>");
		  		System.out.println("-------------------------------");
		  		System.out.println("Projecto: " + project.getKey()  + "  Líneas: " + project.getValue());
			}
		  	
		  	pw.write("</table>");
		  	pw.write("</body>");
		  	pw.write("</html>");
	  }
	  
	  private void showNotFound(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    resp.getWriter().print("Error: 404 Página no encontrada!");
	  }

	/**
	 * Encargado de generar el conteo de las líneas de código
	 * encontradas en cada proyecto de la ruta especificada.
	 * @param path Ruta al directorio que contiene los proyectos.
	 */
	private void countLoc(String path){
		FileManager fm = new FileManager(path);
		this.projects = fm.processProjects();
	}
	
    public static void main( String[] args ) throws Exception   {
    	Server server = new Server(Integer.valueOf(System.getenv("PORT")));
    	ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    	context.setContextPath("/");
    	server.setHandler(context);
    	context.addServlet(new ServletHolder(new App()),"/*");
    	server.start();
    	server.join();
    	
    }
}
