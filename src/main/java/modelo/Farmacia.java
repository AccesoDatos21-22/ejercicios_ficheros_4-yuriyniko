package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dao.MedicamentoDAO;

public class Farmacia implements MedicamentoDAO {
	static Scanner sc = new Scanner(System.in);

	private List<Medicamento> medicamentos = new ArrayList<Medicamento>();

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	/**
	 * Constructor de la farmacia
	 */



	public Farmacia() {

	}

	@Override
	public boolean guardar(Medicamento medicamento) {
		if(medicamentos.add(medicamento)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		Iterator it1 = medicamentos.iterator();

		while(it1.hasNext()){
			if(it1.next().equals(medicamento)){
				it1.remove();
			}
		}
		return false;
	}

	@Override
	public Medicamento buscar(int cod) {
		for(Medicamento list:medicamentos){
			if(list.getCod() == cod){
				return list;
			}
		}

		return null;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		for(Medicamento list:medicamentos){
			if(list.equals(medicamento)){
				System.out.println("Introduce el nuevo precio:");
				list.setPrecio(Integer.parseInt(sc.nextLine()));
				return true;
			}
		}
		return false;
	}
}
