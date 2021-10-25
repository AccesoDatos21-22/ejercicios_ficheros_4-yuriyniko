package modelo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;

@XmlRootElement(name = "pokemon")
@XmlType(propOrder = {"id","nombre","defense","attack","ps"})

public class Pokemon implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private int defense, attack, ps, id;

	public Pokemon(String nombre, int defense, int attack, int ps, int id) {
		super();
		this.nombre = nombre;
		this.defense = defense;
		this.attack = attack;
		this.ps = ps;
		this.id = id;
	}

	public Pokemon() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	@XmlElement(name = "nombre")
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDefense() {
		return defense;
	}

	@XmlElement(name = "defensa")

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getAttack() {
		return attack;
	}

	@XmlElement(name = "ataque")
	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getPs() {
		return ps;
	}

	@XmlElement(name = "vida")
	public void setPs(int ps) {
		this.ps = ps;
	}

	public int getId() {
		return id;
	}

	@XmlElement(name = "id")
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Pokemon [Nombre=" + nombre + ", Defense=" + defense + ", Attack=" + attack + ", PS=" + ps + ", ID=" + id
				+ "]";
	}

}
