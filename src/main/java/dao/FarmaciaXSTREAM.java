package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import modelo.Farmacia;
import modelo.Medicamento;

public class FarmaciaXSTREAM implements FarmaciaDAO {

	final static String rutaFarmacia = ".\\xml\\FarmaciaXstream.xml"; // localizacion del fichero XML

	@Override
	public Farmacia leer() {
		Farmacia listadoTodas = new Farmacia();
		try {

			XStream xstream = new XStream();

			Class<?>[] clases = new Class[] { Farmacia.class, Medicamento.class };
			xstream.allowTypes(clases);

			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);
			xstream.addImplicitCollection(Farmacia.class,"medicamentos");

			listadoTodas = (Farmacia) xstream.fromXML(new FileInputStream(rutaFarmacia));
			System.out.println("Número de medicamentos: " + listadoTodas.getMedicamentos().size());

			List<Medicamento> listaMedicamentos;
			listaMedicamentos = listadoTodas.getMedicamentos();

			for (Medicamento med : listaMedicamentos) {
				System.out.println(med.toString());
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

		return listadoTodas;
	}

	@Override
	public boolean guardar(Farmacia farmacia) {

		try {
			XStream xstream = new XStream(new DomDriver());

			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);
			xstream.addImplicitCollection(Farmacia.class,"medicamentos");

			xstream.toXML(farmacia, new FileOutputStream(rutaFarmacia));
			System.out.println("Creado fichero XML....");

		} catch (FileNotFoundException e) {
			e.getStackTrace();
			return false;
		}

		return true;
	}

}