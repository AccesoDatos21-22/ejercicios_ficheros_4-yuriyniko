package modelo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.Objects;

@XStreamAlias("Medicamento")
public class Medicamento implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static float IVA = 0.04f;
	
	public static final int TAM_NOMBRE=40;
	public static final int TAM_REGISTRO=68;

	private String nombre; // 40 BYTES
	private double precio; // 8 bytes
	private int cod; // 4 bytes
	private int stock; // 4 bytes
	private int stockMaximo; // 4 bytes
	private int stockMinimo; // 4 bytes
	private int codProveedor; // 4 bytes
	private static int contador = 1;

	public Medicamento(String nombre, double precio, int stock, int stockMaximo, int stockMinimo, int codProveedor) {
		super();
		this.nombre = nombre;
		this.precio = precio + (precio * IVA);
		this.cod = contador;
		this.stock = stock;
		this.stockMaximo = stockMaximo;
		this.stockMinimo = stockMinimo;
		this.codProveedor = codProveedor;
		contador++;
	}

	public Medicamento(String nombre, int cod, double precio, int stock, int stockMaximo, int stockMinimo, int codProveedor) {
		super();
		this.nombre = nombre;
		this.precio = precio + (precio * IVA);
		this.cod = cod;
		this.stock = stock;
		this.stockMaximo = stockMaximo;
		this.stockMinimo = stockMinimo;
		this.codProveedor = codProveedor;
	}

	public Medicamento() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = contador;
		contador++;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockMaximo() {
		return stockMaximo;
	}

	public void setStockMaximo(int stockMaximo) {
		this.stockMaximo = stockMaximo;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public int getCodProveedor() {
		return codProveedor;
	}

	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		return cod == other.cod;
	}

	@Override
	public String toString() {
		return "Codigo:" + cod + "\n Nombre: " + nombre + "\n Precio:" + (Math.round(precio * 100d) / 100d) + "€" + "\n Stock:" + stock
				+ "\n Proveedor:" + codProveedor;
	}

}
