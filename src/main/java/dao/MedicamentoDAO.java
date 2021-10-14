package dao;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import modelo.Medicamento;

public interface MedicamentoDAO {
	
	// Operaciones CRUD
	
	public static final String fichero = "medicamentos.bin";
	
	public static boolean guardar(Medicamento medicamento) {
		try (RandomAccessFile ficheroM = new RandomAccessFile(fichero,"rw")){
			ficheroM.seek(medicamento.getCod() * Medicamento.TAM_REGISTRO);
			StringBuilder nombre = new StringBuilder(medicamento.getNombre());
			nombre.setLength(Medicamento.TAM_NOMBRE);
			ficheroM.writeUTF(medicamento.getNombre());
			ficheroM.writeDouble(medicamento.getPrecio());
			ficheroM.writeInt(medicamento.getStock());
			ficheroM.writeInt(medicamento.getStockMaximo());
			ficheroM.writeInt(medicamento.getStockMinimo());
			ficheroM.writeInt(medicamento.getCodProveedor());
		} catch(IOException exception) {
			return false;
		}
		return true;
	}
	
	public Medicamento buscar(String nombre);

	public boolean actualizar(Medicamento medicamento);
	
	public boolean borrar(Medicamento medicamento);	
	
	public List<Medicamento> leerTodos();




}
