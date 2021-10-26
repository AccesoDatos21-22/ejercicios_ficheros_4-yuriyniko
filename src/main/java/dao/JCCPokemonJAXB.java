package dao;

import jakarta.xml.bind.*;
import modelo.JCCPokemon;
import modelo.Pokemon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JCCPokemonJAXB implements JCCPokemonDAO {

	private final String ficheroPath = ".\\xml\\PokemonesJAXB.xml";

	@Override
	public JCCPokemon leer() {

		try {
			JAXBContext context = JAXBContext.newInstance(JCCPokemon.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Marshaller marshaller = context.createMarshaller();

			JCCPokemon pokemonAux = (JCCPokemon) unmarshaller.unmarshal(Files.newInputStream(Paths.get(this.ficheroPath)));
			System.out.println("** pokemon cargado desde fichero XML**");

			marshaller.marshal(pokemonAux, System.out);


		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}



		return null;
	}

	@Override
	public boolean guardar(JCCPokemon pokemones) {
		pokemones.setNumCartas(pokemones.getPokemones().size());
		pokemones.setFechaLanzamiento(new Date(System.currentTimeMillis()));
			try {
				JAXBContext context = JAXBContext.newInstance(JCCPokemon.class);
				Marshaller marshaller = context.createMarshaller();

				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(pokemones, Files.newOutputStream(Paths.get(this.ficheroPath)));
			} catch (PropertyException e) {
				e.printStackTrace();
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		return true;
	}

}
