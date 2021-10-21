package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import modelo.Farmacia;
import modelo.Medicamento;

public class FarmaciaDOM {

	public boolean leer(Path farmaciaXML) {

		try {

			// Creo una instancia de DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// Creo un documentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Obtengo el documento, a partir del XML
			Document documento = builder.parse(new File(farmaciaXML.toString()));

			// Cojo todas las etiquetas coche del documento
			NodeList listaNodos = ((org.w3c.dom.Document) documento).getElementsByTagName("medicamento");

			// Recorro las etiquetas
			for (int i = 0; i < listaNodos.getLength(); i++) {

				// Cojo el nodo actual
				Node nodo = listaNodos.item(i);

				// Compruebo si el nodo es un elemento
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {

					// Lo transformo a Element
					Node e = nodo;

					// Obtengo sus hijos
					NodeList hijos = ((Node) e).getChildNodes();

					// Recorro sus hijos
					for (int j = 0; j < hijos.getLength(); j++) {

						// Obtengo al hijo actual
						Node hijo = hijos.item(j);

						// Compruebo si es un nodo
						if (hijo.getNodeType() == Node.ELEMENT_NODE) {

							// Muestro el contenido
							System.out
									.println(hijo.getNodeName() + ": "+ hijo.getTextContent());
						}

					}
					System.out.println("");
				}

			}

		} catch (ParserConfigurationException | SAXException | IOException ex) {
			System.out.println(ex.getMessage());
			return false;
		}

		return true;

	}

	public boolean guardar(Farmacia farmacia) {

		List<Medicamento> lista;

		lista = farmacia.getMedicamentos();

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			DOMImplementation imple = docBuilder.getDOMImplementation();

			org.w3c.dom.Document doc = imple.createDocument(null, "farmacia", null);

			doc.setXmlVersion("1.0");

			org.w3c.dom.Element Medicamentos = doc.createElement("medicamentos");

			for (Medicamento med : lista) {

				org.w3c.dom.Element Medicamento = doc.createElement("medicamento");

				org.w3c.dom.Element Nombre = doc.createElement("nombre");
				Text TextNombre = doc.createTextNode(med.getNombre());
				Nombre.appendChild(TextNombre);
				Medicamento.appendChild(Nombre);

				org.w3c.dom.Element Precio = doc.createElement("precio");
				String txt = String.valueOf(med.getPrecio());
				Text TextPrecio = doc.createTextNode(txt);
				Precio.appendChild(TextPrecio);
				Medicamento.appendChild(Precio);

				org.w3c.dom.Element Stock = doc.createElement("stock");
				String txt1 = String.valueOf(med.getStock());
				Text TextStock = doc.createTextNode(txt1);
				Stock.appendChild(TextStock);
				Medicamento.appendChild(Stock);

				org.w3c.dom.Element CodProveedor = doc.createElement("codProveedor");
				String txt2 = String.valueOf(med.getCodProveedor());
				Text TextCodProveedor = doc.createTextNode(txt2);
				CodProveedor.appendChild(TextCodProveedor);
				Medicamento.appendChild(CodProveedor);

				Medicamentos.appendChild(Medicamento);

			}

			doc.getDocumentElement().appendChild(Medicamentos);

			Source source = new DOMSource(doc);

			Result result = new StreamResult(new File("prueba.xml"));

			Transformer transformer = TransformerFactory.newInstance().newTransformer();

			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerException tfe) {

			tfe.printStackTrace();
			return false;
		}

		return true;

	}

}
