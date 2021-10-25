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

	final static String rutaFarmcia = "FarmaciaXStream.xml"; // localizacion del fichero XML
	final static String rutaMedicamento = ""; // localizacion del fichero XML

	@Override
	public Farmacia leer() {

		try {

			XStream xstream = new XStream();

			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);

			Farmacia listadoTodas = (Farmacia) xstream.fromXML(new FileInputStream("rutaFarmcia"));
			System.out.println("Número de medicamentos: " + listadoTodas.getMedicamentos().size());

			List<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();
			listaMedicamentos = listadoTodas.getMedicamentos();

			for (Medicamento med : listaMedicamentos) {
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

			List<Medicamento> lista = new ArrayList<Medicamento>();

			lista = farmacia.getMedicamentos();

			XStream xstream = new XStream();

			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);

			xstream.toXML(lista, new FileOutputStream("rutaFarmcia"));
			System.out.println("Creado fichero XML....");

		} catch (FileNotFoundException e) {
			e.getStackTrace();
			return false;
		}

		return true;
	}

}