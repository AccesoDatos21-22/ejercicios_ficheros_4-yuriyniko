package modelo;

import java.util.ArrayList;
import java.util.Iterator;
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
		for(Medicamento lista:medicamentos){
			if(lista.getCod() == cod){
				return lista;
			}
		}
		return null;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		Iterator<Medicamento> it1 = medicamentos.iterator();
		while(it1.hasNext()){
			if(it1.next().equals(medicamento)){
				it1.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		for(Medicamento lista:medicamentos){
			if(lista.equals(medicamento)){
				System.out.println("Introduce el nuevo precio:");
				lista.setPrecio(10);
				return true;
			}
		}
		return false;
	}
}