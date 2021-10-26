package modelo;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "JCCPokemon")
@XmlType(propOrder = {"fechaLanzamiento", "numCartas", "pokemones"})

public class JCCPokemon implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	private List<Pokemon> pokemones = new ArrayList<>();
	private Date fechaLanzamiento;
	private int numCartas;

	public JCCPokemon() {
		super();
	}

	public List<Pokemon> getPokemones() {
		return pokemones;
	}

	public boolean guardar(Pokemon pokemon){
		pokemones.add(pokemon);
		return true;
	}

	@XmlElement(name = "pokemones")
	public void setPokemones(List<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	@XmlAttribute(name = "fechaLanzamiento")
	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public int getNumCartas() {
		return numCartas;
	}

	@XmlAttribute(name = "numCartas")
	public void setNumCartas(int numCartas) {
		this.numCartas = numCartas;
	}



}
