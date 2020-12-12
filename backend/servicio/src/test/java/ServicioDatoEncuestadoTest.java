import mercadeoucab.dtos.*;
import mercadeoucab.entidades.*;
import mercadeoucab.servicio.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class ServicioDatoEncuestadoTest {

    @Test
    public void registrarDatoEncuestadoTest() throws Exception{
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        DtoDatoEncuestado datoEncuestado = new DtoDatoEncuestado("Concepcion",
                                                    "arevalo",
                                                    "5000",
                                                    "laptop",
                                                    Date.valueOf("1997-02-28"),
                                                    "masculino",
                                                    50,
                                                    "universitario",
                                                    5);
        datoEncuestado.setFk_lugar(new DtoParroquia(1));
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        datoEncuestado.setUsuario(new DtoUsuario( 1));
        DtoOcupacion dtoOcupacion = new DtoOcupacion(1);
        datoEncuestado.setOcupacion(dtoOcupacion);
        DatoEncuestado resultado = servicioDato.registrarDatoEncuestado(datoEncuestado);
        Assert.assertNotEquals(0 , resultado.get_id());
    }

    @Test
    public void consultarDatoEncuestadoTest() throws Exception{
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        DatoEncuestado consultado = servicioDato.consultarDatoEncuestado(1);
        Assert.assertEquals(1, consultado.get_id());
    }

    @Test
    public void listarDatoEncuestadoTest() throws Exception{
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        List<DatoEncuestado> datoEncuestados = servicioDato.listarDatosEncuestado();
        Assert.assertNotNull(datoEncuestados);
    }

    @Test
    public void actualizarDatoEncuestadoTest() throws Exception{
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        DtoDatoEncuestado datoEncuestado = new DtoDatoEncuestado("Concepcion",
                "ok",
                "5002",
                "laptop",
                Date.valueOf("1997-02-28"),
                "masculino",
                50,
                "universitario",
                5);
        datoEncuestado.setFk_lugar(new DtoParroquia(1));
        datoEncuestado.setUsuario(new DtoUsuario(1));
        DtoOcupacion dtoOcupacion = new DtoOcupacion(1);
        datoEncuestado.setOcupacion(dtoOcupacion);
        DatoEncuestado actualizado = servicioDato.actualizarDatoEncuestado(1, datoEncuestado);
        Assert.assertNotNull(actualizado.getModificado_el());
    }

    @Test
    public void eliminarDatoEncuestadoTest() throws Exception{
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        DatoEncuestado eliminado = servicioDato.eliminarDatoEncuestado( (long)1);
        Assert.assertNotNull(eliminado.getModificado_el());
    }


}
