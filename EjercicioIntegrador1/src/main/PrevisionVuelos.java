package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import entidades.EmpresaAeronautica;
import entidades.TipoPasaje;
import entidades.Vuelo;

public class PrevisionVuelos {
	public static void main(String[] args) throws IOException {
		List<Vuelo> vuelos = new ArrayList<>();
		List<EmpresaAeronautica> empresas = new ArrayList<>();
		List<String> informacionVuelos = new ArrayList<>();
		String ruta = "C:\\Users\\pcallep\\Desktop\\GT3_JAVA_Integradores\\EjercicioIntegrador1\\";
		String nombreArchivo = "vuelos.txt";
		String rutaNombreArchivo = ruta + nombreArchivo;
		File archivoVuelos = new File(rutaNombreArchivo);

		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivoVuelos));
			String lineaInformacion = "";
			while ((lineaInformacion = lector.readLine()) != null) {

				informacionVuelos.add(lineaInformacion);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		try {
			empresas = empresas(informacionVuelos);
			vuelos = vuelos(informacionVuelos);

			String nombreArchivoPrevision = "previsionVuelos.txt";
			String rutaPrevisionVuelos = ruta + nombreArchivoPrevision;
			File archivoPrevision = new File(rutaPrevisionVuelos);

			// Manejo de excepciones al manejar archivos.
			if (!archivoPrevision.exists()) {

				archivoPrevision.createNewFile();
				System.out.println("¡Se creo el fichero de previsión de vuelos!");

				FileWriter fw = new FileWriter(archivoPrevision);
				BufferedWriter bufferedWriter = new BufferedWriter(fw);
				int nEmpresas = 1;
				for (EmpresaAeronautica empresa : empresas) {

					bufferedWriter.write("Empresa " + nEmpresas + ": " + empresa.getNombreEmpresa() + "\n");
					bufferedWriter.write("Monto final de empresa " + empresa.getNombreEmpresa() + ": "
							+ montoFinalEmpresa(empresa.getNombreEmpresa(), vuelos) + "\n");
					bufferedWriter.write("\n");
					nEmpresas++;
				}
				int nVuelos = 1;
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
				for (Vuelo vuelo : vuelos) {

					bufferedWriter.write("Vuelo " + nVuelos + ": " + vuelo.getNombreVuelo() + "\n");
					bufferedWriter.write("Empresa a la que pertenece: " + vuelo.getNombreEmpresa() + "\n");
					bufferedWriter.write("Cantidad de pasajeros: " + vuelo.getCantidadPasajeros() + "\n");
					bufferedWriter.write("Tipo de pasaje: " + vuelo.getTipoPasaje() + "\n");
					bufferedWriter.write("Valor unitario del pasaje " + vuelo.getValorUnitarioPasaje() + "\n");
					bufferedWriter.write("Fecha del vuelo " + vuelo.getFechaVuelo().format(dateTimeFormatter) + "\n");
					bufferedWriter.write("Rentabilidad: " + vuelo.getRentabilidad() + "\n");
					bufferedWriter.write("\n");
					nVuelos++;
				}
				bufferedWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<Vuelo> vuelos(List<String> informacion) {
		List<Vuelo> vuelos = new ArrayList<>();
		for (String info : informacion) {
			if (info.contains("Vuelo")) {
				String[] infoVuelo = info.split(",");
				TipoPasaje tp;

				String[] fechaVueloString = infoVuelo[6].split("-");
				int anio = Integer.parseInt(fechaVueloString[2]);
				int mes = Integer.parseInt(fechaVueloString[1]);
				int dia = Integer.parseInt(fechaVueloString[0]);
				LocalDate fechaVuelo = LocalDate.of(anio, mes, dia);
				if (infoVuelo[4].equals("Económico")) {
					tp = TipoPasaje.ECONOMICO;

				} else {
					tp = TipoPasaje.PREMIER;

				}
				vuelos.add(new Vuelo(infoVuelo[1], infoVuelo[2], Integer.parseInt(infoVuelo[3]), tp,
						Double.parseDouble(infoVuelo[5]), fechaVuelo));

			}
		}
		return vuelos;

	}

	public static List<EmpresaAeronautica> empresas(List<String> informacion) {
		List<EmpresaAeronautica> empresas = new ArrayList<>();
		for (String info : informacion) {
			if (info.contains("Empresa")) {
				String[] infoEmpresa = info.split(",");

				// Nos quedamos con el nombre de la empresa.
				empresas.add(new EmpresaAeronautica(infoEmpresa[1]));

			}
		}
		return empresas;
	}

	public static Double montoFinalEmpresa(String nombreEmpresa, List<Vuelo> vuelos) {

		Double montoFinal = 0.0;
		for (Vuelo vuelo : vuelos) {
			Boolean vueloPerteneceEmpresa = vuelo.getNombreEmpresa().equals(nombreEmpresa) ? true : false;
			if (vueloPerteneceEmpresa)
				montoFinal += vuelo.valorFinalVuelo();

		}

		return montoFinal;

	}
}


