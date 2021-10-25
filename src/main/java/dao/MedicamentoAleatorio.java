package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import modelo.Medicamento;

public class MedicamentoAleatorio implements MedicamentoDAO {
	
	public final static int TAM_NOMBRE = 30;
	public final static int TAM_REGISTRO = 88;
	public static String fichero = "medicamentos.bin";
	
	@Override
	public boolean guardar(Medicamento medicamento) {
		try(RandomAccessFile raf = new RandomAccessFile(fichero, "rw")){
			raf.seek((long) medicamento.getCod() * TAM_REGISTRO);
			
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
		
		try(RandomAccessFile raf = new RandomAccessFile(fichero, "r")){
			if(raf.length() < ( (long) TAM_REGISTRO * cod)) {
				System.out.println(cod + " Este medicamento no existe...");
			} else{
				raf.seek((long) cod * TAM_REGISTRO);

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
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return md;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {

		try(RandomAccessFile raf = new RandomAccessFile(fichero, "r")){
			if(raf.length() < (long) medicamento.getCod()*TAM_REGISTRO){
				System.out.println("El medicamento que buscas no existe...");
				return false;
			} else{
				medicamento.setPrecio(100);
				guardar(medicamento);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		try(RandomAccessFile raf = new RandomAccessFile(fichero, "rw")){
			if(raf.length() < (long) medicamento.getCod()*TAM_REGISTRO){
				System.out.println("El medicamento que intentas buscar no esta en el fichero...");
				return false;
			} else{
				int pos = medicamento.getCod() * TAM_REGISTRO;

				raf.seek(pos);

				byte[] valores = new byte[TAM_REGISTRO];

				for(int i = 0; i < TAM_REGISTRO; i++){
					valores[i] = 0;
				}

				raf.write(valores);
			}
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
