package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import modelo.Farmacia;
import modelo.Medicamento;

public class FarmaciaXSTREAM implements FarmaciaDAO {

	final static String rutaFarmcia = "Farmacia.xml"; // localizacion del fichero XML
	final static String rutaMedicamento = ""; // localizacion del fichero XML

	@Override
	public Farmacia leer() {

		try {

			XStream xstream = new XStream();

			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);

			Farmacia listadoTodas = (Farmacia) xstream.fromXML(new FileInputStream("rutaFarmcia"));
			System.out.println("Número de Empleados: " + listadoTodas.getMedicamentos().size());

			List<Medicamento> listaEmpleados = new ArrayList<Medicamento>();
			listaEmpleados = listadoTodas.getMedicamentos();

			for (Medicamento med : listaEmpleados) {

				System.out.println(med.toString());

			}

		} catch (Exception e) {
			e.getStackTrace();

		}

		return null;
	}

	@Override
	public boolean guardar(Farmacia farmacia) {

		try {

			List<Medicamento> Lista;

			Lista = farmacia.getMedicamentos();

			XStream xstream = new XStream();

			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);

			xstream.toXML(Lista, new FileOutputStream("rutaFarmcia"));
			System.out.println("Creado fichero XML....");

		} catch (FileNotFoundException e) {
			e.getStackTrace();
			return false;
		}

		return true;
	}

}
