package dao;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import modelo.Medicamento;

public interface MedicamentoDAO {

	// Operaciones CRUD

	public static final String fichero = "medicamentos.bin";

	public static boolean guardar(Medicamento medicamento) {
		try (RandomAccessFile ficheroM = new RandomAccessFile(fichero, "rw")) {
			ficheroM.seek(medicamento.getCod() * Medicamento.TAM_REGISTRO);
			StringBuilder nombre = new StringBuilder(medicamento.getNombre());
			nombre.setLength(Medicamento.TAM_NOMBRE);
			ficheroM.writeUTF(medicamento.getNombre());
			ficheroM.writeDouble(medicamento.getPrecio());
			ficheroM.writeInt(medicamento.getStock());
			ficheroM.writeInt(medicamento.getStockMaximo());
			ficheroM.writeInt(medicamento.getStockMinimo());
			ficheroM.writeInt(medicamento.getCodProveedor());
		} catch (IOException exception) {
			return false;
		}
		return true;
	}

	public static Medicamento buscar(String nombre) {

		Medicamento md = new Medicamento();

		try (RandomAccessFile ficheroM = new RandomAccessFile(fichero, "r")) {

			double precio;
			int Stock, stockMax, stockMin, codProveedor, cod;
			char nom[] = new char[Medicamento.TAM_NOMBRE], aux;

			do {

				cod = ficheroM.readInt();

				for (int i = 0; i < Medicamento.TAM_NOMBRE; i++) {
					aux = ficheroM.readChar();
					if ((int) aux != 0) {
						nom[i] = aux;
					} else {
						nom[i] = ' ';
					}
				}

				String nombreM = new String(nom);

				md.setNombre(nombreM);
				md.setPrecio(ficheroM.readDouble());
				md.setStock(ficheroM.readInt());
				md.setStockMaximo(ficheroM.readInt());
				md.setStockMinimo(ficheroM.readInt());
				md.setCodProveedor(ficheroM.readInt());
				md.setCod(ficheroM.readInt());

				System.out.println(md.toString());

			} while (ficheroM.getFilePointer() <= ficheroM.length());

		} catch (Exception e) {

			e.getMessage();

		}

		return md;
	}

	public boolean actualizar(Medicamento medicamento);

	public boolean borrar(Medicamento medicamento);

	public List<Medicamento> leerTodos();

}
