package modelo;

import java.util.List;

import dao.MedicamentoDAO;

public class Farmacia implements MedicamentoDAO {

	private List<Medicamento> medicamentos;

	/**
	 * Constructor de la farmacia
	 */
	public Farmacia() {

	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public boolean guardar(Medicamento medicamento) {

		return false;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {

		return false;
	}

	@Override
	public List<Medicamento> leerTodos() {

		return null;
	}

	public Medicamento buscar(String nombre) {
		return null;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		return false;
	}
}
