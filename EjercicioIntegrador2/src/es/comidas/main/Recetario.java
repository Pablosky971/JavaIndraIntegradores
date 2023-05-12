package es.comidas.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import es.comidas.model.Ingrediente;
import es.comidas.model.Receta;
import es.comidas.model.TipoComida;

public class Recetario {

	public static void main(String[] args) throws IOException {
		escribirFicheroFileJenkins();
	}
	public static void escribirFicheroFileJenkins() throws IOException {
		List<Receta> recetas = getRecetas();
		System.out.println(recetas.size());
		try (
				PrintWriter writer = new PrintWriter(new FileWriter("Jenkinsfile")))
			{
			String contenidoJenkinsFile = 
					"pipeline {\n" +
	                        "    agent any\n" +
	                        "    stages {\n";
	                       
				
				for(Receta receta : recetas) {
					contenidoJenkinsFile +=  "stage(\""+ receta.getNombre()+ "\") {\n" +
	                        "            steps {\n" +
	                        "                script {\n" +
	                        "                    println \"La receta " + receta.getNombre() + ", tiene un tiempo de cocción de: "  + receta.getTiempoCoccionEnMinutos() + " minutos, además, tiene " + receta.getCantidadCalorias() + " calorias." + "\"\n";
					System.out.println(receta.getIngredientes().size());
					for(int i=0; i<=receta.getIngredientes().size() - 1; i++) {
						
						contenidoJenkinsFile += "                    println \"Ingrediente " + receta.getIngredientes().get(i).getNombre() + ", cantidad: "  + receta.getTiempoCoccionEnMinutos() + " minutos, además, tiene " + receta.getCantidadCalorias() + " calorias." + "\"\n";
						if(i==receta.getIngredientes().size() - 1) {
							contenidoJenkinsFile += "                }\n" +
			                        "            }\n" +
			                        "        }\n";
							
						}
						
					}
					
	                        
							
					
				}
				contenidoJenkinsFile += 
						"        }\n" +
						"        }\n";
				writer.write(contenidoJenkinsFile);
				System.out.println("Se ha escrito el fichero");
				writer.close();
			} 
			
			catch (IOException e) {
				
				e.printStackTrace();
			}

		
	}

	public static List<String> leerFicheroComidas() throws IOException {
		List<String> informacionRecetas = new ArrayList<>();
		String ruta = "C:\\Users\\pcallep\\Desktop\\GT3_JAVA_Integradores\\EjercicioIntegrador2\\";
		String nombreArchivo = "comidas.txt";
		String rutaNombreArchivo = ruta + nombreArchivo;
		File archivoRecetas = new File(rutaNombreArchivo);

		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivoRecetas));
			String lineaInformacion = "";
			while ((lineaInformacion = lector.readLine()) != null) {

				informacionRecetas.add(lineaInformacion);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return informacionRecetas;
	}

	public static List<Receta> getRecetas() throws IOException {
		List<Receta> recetas = new ArrayList<>();
		List<String> informacionRecetas = leerFicheroComidas();
		List<Ingrediente> ingredientes = new ArrayList<>();
		for(String info : informacionRecetas) {
			System.out.println(info);
			if(info.contains("Receta")) {
				String[] infoReceta = info.split(":");
				List<Ingrediente> ingredientesAux = new ArrayList<>();
				ingredientesAux.addAll(ingredientes);
				recetas.add(new Receta(infoReceta[1], ingredientesAux, TipoComida.AGRIA));
				ingredientes.clear();
			} else {
				String[] infoIngredientes = info.split("-");
				ingredientes.add(new Ingrediente(infoIngredientes[0], infoIngredientes[1]));
	
			}
			

		}
		
		

	return recetas;
}

}
