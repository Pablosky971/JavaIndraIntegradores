package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidades.EmpresaAeronautica;
import entidades.TipoPasaje;
import entidades.Vuelo;

public class PrevisionVuelos {
	public static void main(String[] args) throws IOException {
		List<Vuelo> vuelos = new ArrayList<>();
		List<String> informacionVuelos = new ArrayList<>();
		String ruta = "C:\\Users\\pcallep\\Desktop\\GT3_JAVA_Integradores\\EjercicioIntegrador1\\";
		String nombreArchivo = "vuelos.txt";
		String rutaNombreArchivo = ruta + nombreArchivo;
		File archivoVuelos = new File(rutaNombreArchivo);
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivoVuelos));
			String lineaInformacion= "";
			while((lineaInformacion=lector.readLine())!=null) {
				
				informacionVuelos.add(lineaInformacion);
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		String nombreArchivoPrevision = "previsionVuelos.txt";
		String rutaPrevisionVuelos= ruta + nombreArchivo;
		File archivoPrevision = new File(rutaPrevisionVuelos);
		
		// Manejo de excepciones al manejar archivos.
				if(!archivoPrevision.exists()) {
					
				
					archivoPrevision.createNewFile();
					System.out.println("¡Se creo el fichero!");
				
				FileWriter fw = new FileWriter(archivoVuelos);
				}
				
				
		
 	}
	

	public static List<Vuelo> vuelos(List<String> informacion) {
		List<Vuelo> vuelos = new ArrayList<>();
		for(String info : informacion) {
			if(info.matches("Vuelo")) {
				String[] infoVuelo = info.split(",");
				TipoPasaje tp;
				
				
				String[] fechaVueloString = infoVuelo[6].split("-");
				int anio = Integer.parseInt(fechaVueloString[0]);
				int mes = Integer.parseInt(fechaVueloString[1]);
				int dia = Integer.parseInt(fechaVueloString[2]);
				LocalDate fechaVuelo = LocalDate.of(anio, mes, dia);
				if(infoVuelo[4].equals("Ecnonómico")) {
					tp = TipoPasaje.ECONOMICO;
					
				} else {
					tp = TipoPasaje.PREMIER;
					
				}
				vuelos.add(new Vuelo(infoVuelo[1], infoVuelo[2], Integer.parseInt(infoVuelo[3]),
						tp, Double.parseDouble(infoVuelo[5]), fechaVuelo));
				
				
			}
		}
		return vuelos;
		
	}
	public static List<EmpresaAeronautica> empresas (List<String> informacion) {
		List<EmpresaAeronautica> empresas= new ArrayList<>();
		for(String info : informacion) {
			if(info.matches("Empresa")) {
				String[] infoEmpresa = info.split(",");
				
				// Nos quedamos con el nombre de la empresa.
				empresas.add(new EmpresaAeronautica(infoEmpresa[1]));
				
			}
		}
		return empresas;
	}

}
	


