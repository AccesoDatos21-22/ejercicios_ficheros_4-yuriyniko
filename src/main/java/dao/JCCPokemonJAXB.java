package dao;

import jakarta.xml.bind.*;
import modelo.JCCPokemon;
import modelo.Pokemon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JCCPokemonJAXB implements JCCPokemonDAO {

	@Override
	public JCCPokemon leer() {

		try {
			JAXBContext context = JAXBContext.newInstance(Pokemon.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Marshaller marshaller = context.createMarshaller();

			Pokemon pokemonAux = (Pokemon) unmarshaller.unmarshal(Files.newInputStream(Paths.get(".\\xml\\PokemonesJAXB.xml")));
			System.out.println("** pokemon cargado desde fichero XML**");

			marshaller.marshal(pokemonAux, System.out);


		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}



		return null;
	}

	@Override
	public boolean guardar(JCCPokemon pokemones) {
		List<Pokemon> lista;
		lista = pokemones.getPokemones();

		for(Pokemon pokemon:lista) {
			try {
				JAXBContext context = JAXBContext.newInstance(Pokemon.class);
				Marshaller marshaller = context.createMarshaller();

				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(pokemon, Files.newOutputStream(Paths.get("??????")));
			} catch (PropertyException e) {
				e.printStackTrace();
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
