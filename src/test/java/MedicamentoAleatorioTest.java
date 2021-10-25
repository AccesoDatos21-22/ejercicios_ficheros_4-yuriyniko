import dao.MedicamentoAleatorio;
import modelo.Medicamento;
import org.junit.Assert;
import org.junit.Test;

public class MedicamentoAleatorioTest {

    private final MedicamentoAleatorio medAl = new MedicamentoAleatorio();
    private final Medicamento med = new Medicamento("Paracetamol",1.25, 10,20,5,3);
    private final Medicamento med1 = new Medicamento("Ibuprofeno",2.15, 10,20,5,3);

    @Test
    public void probarGuardar(){
        Assert.assertTrue(medAl.guardar(med));
        Assert.assertTrue(medAl.guardar(med1));
    }

    @Test
    public void probarBuscar(){
        Assert.assertEquals(med, medAl.buscar(5)); //Por que me coge código 3
    }

    @Test
    public void probarActualizar(){
        Assert.assertTrue(medAl.actualizar(med1)); //Como introducir los datos que me pide la pantalla
    }

    @Test
    public void probarBorrar(){
        Assert.assertTrue(medAl.borrar(med));
    }

}
