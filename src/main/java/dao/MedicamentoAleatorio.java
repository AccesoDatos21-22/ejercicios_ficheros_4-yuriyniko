package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Scanner;

import modelo.Medicamento;

public class MedicamentoAleatorio implements MedicamentoDAO {
	
	public final static int TAM_NOMBRE = 30;
	public final static int TAM_REGISTRO = 88;
	static Scanner sc = new Scanner(System.in);
	
	@Override
	public boolean guardar(Medicamento medicamento) {
		try(RandomAccessFile raf = new RandomAccessFile("medicamentos.bin", "rw")){
			raf.seek(medicamento.getCod() * TAM_REGISTRO);
			
			StringBuffer bf = new StringBuffer(medicamento.getNombre());
			bf.setLength(TAM_NOMBRE);
			
			raf.writeChars(bf.toString());
			raf.writeDouble(medicamento.getPrecio());
			raf.writeInt(medicamento.getCod());
			raf.writeInt(medicamento.getStock());
			raf.writeInt(medicamento.getStockMaximo());
			raf.writeInt(medicamento.getStockMinimo());
			raf.writeInt(medicamento.getCodProveedor());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Medicamento buscar(int cod) {
		String nombre = "";
		double precio;
		int codigo, stock, stockMin, stockMax, codP;
		
		Medicamento md = new Medicamento();
		
		try(RandomAccessFile raf = new RandomAccessFile("medicamentos.bin", "r")){
			if(raf.length() < TAM_REGISTRO * cod) {
				System.out.println(cod + " Este medicamento no existe...");
			}
			
			raf.seek(cod * TAM_REGISTRO);
			
			for(int i = 0; i < TAM_NOMBRE; i++) {
				char letra = raf.readChar();

				nombre += letra != 0 ? letra : ' ';
			}
			precio = raf.readDouble();
			codigo = raf.readInt();
			stock = raf.readInt();
			stockMax = raf.readInt();
			stockMin = raf.readInt();
			codP = raf.readInt();
			
			md = new Medicamento(nombre,codigo,precio,stock,stockMax,stockMin,codP);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return md;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		System.out.println("Introduce el nuevo precio");
		double precio = Double.parseDouble(sc.nextLine());
		medicamento.setPrecio(precio);
		
		guardar(medicamento);
		return false;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		try(RandomAccessFile raf = new RandomAccessFile("medicamentos.bin", "rw")){
			int posicion = (medicamento.getCod()-1) * TAM_REGISTRO;
			byte[] b = new byte[88];
			raf.seek(posicion);
			raf.write(b,0,88);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
