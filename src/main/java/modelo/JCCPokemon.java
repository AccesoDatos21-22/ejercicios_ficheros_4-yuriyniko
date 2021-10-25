package modelo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "JCCPokemon")
@XmlType(propOrder = {"fechaLanzamiento", "numCartas"})

public class JCCPokemon implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	private List<Pokemon> pokemones;
	private Date fechaLanzamiento;
	private int numCartas;

	public JCCPokemon() {
		super();
	}

	public List<Pokemon> getPokemones() {
		return pokemones;
	}

	public void setPokemones(List<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	@XmlElement
	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public int getNumCartas() {
		return numCartas;
	}

	@XmlElement
	public void setNumCartas(int numCartas) {
		this.numCartas = numCartas;
	}



}
