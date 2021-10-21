package modelo;

import java.util.ArrayList;
import java.util.List;
import dao.MedicamentoDAO;

public class Farmacia implements MedicamentoDAO {

	private List<Medicamento> medicamentos = new ArrayList<Medicamento>();

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
		try{
			medicamentos.add(medicamento);
		} catch (Exception e){
			System.out.println(e.toString());
			return false;
		}
		return true;
	}

	@Override
	public Medicamento buscar(int cod) {
		return null;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {

		return false;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		return false;
	}
}