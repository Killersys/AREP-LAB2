package edu.escuelaing.arep.app;

import static spark.Spark.*;


/**
 @author Jairo Pulido
 
 *Clase principal de lectura de archivos y impresion de los datos calculados
 
 */
public class App {
	/**
	 * Encargada de iniciar el servicio usando spark
	 * @param argv parametros al momento de iniciar la aplicación
	 */
    public static void main(String [] argv){

        staticFiles.location("/public");
        port(getPort());
		post("/calculator", (request, response) -> {
			LinkedList list = new LinkedList();
			Double list2[] = new Double[] {};
			String req = request.body(); //String en formato json			
			String[] json = req.replace("\"", "").replace("[", "").replace("]", "").split(",");			
			for (int i = 0; i < json.length; i++) {
				double value = Double.parseDouble(json[i]);
				list.Add(value);}
			
			Calculator cal = new Calculator(list);
				
			return "{\"mean\":" + cal.Mean() + ", \"dev\":" + cal.Dev() + "}";});}
    
	/**
     * Da el puerto solicitado por la aplicación
     * @return puerto
     */
	static int getPort() {
		 if (System.getenv("PORT") != null) {
			 return Integer.parseInt(System.getenv("PORT"));}
		 return 4567;}
}