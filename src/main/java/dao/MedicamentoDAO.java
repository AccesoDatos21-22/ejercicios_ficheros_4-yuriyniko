package dao;

import java.util.List;

import modelo.Medicamento;

public interface MedicamentoDAO {

	// Operaciones CRUD

	public boolean guardar(Medicamento medicamento);

	public Medicamento buscar(int cod);

	public boolean actualizar(Medicamento medicamento);

	public boolean borrar(Medicamento medicamento);




}