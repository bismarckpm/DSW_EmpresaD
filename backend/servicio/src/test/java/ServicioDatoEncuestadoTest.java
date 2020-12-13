import mercadeoucab.dtos.*;
import mercadeoucab.entidades.*;
import mercadeoucab.servicio.*;
import org.junit.Assert;
import org.junit.Test;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.ArrayList;
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
        datoEncuestado.setUsuario(new DtoUsuario( 1));
        DtoOcupacion dtoOcupacion = new DtoOcupacion(1);
        datoEncuestado.setOcupacion(dtoOcupacion);
        List<DtoTelefono> telefonos = new ArrayList<>();
        telefonos.add( new DtoTelefono(1));
        telefonos.add( new DtoTelefono(2));
        datoEncuestado.setTelefonos( telefonos);
        List<DtoHijo> hijos = new ArrayList<>();
        hijos.add( new DtoHijo(1));
        hijos.add( new DtoHijo( 2));
        datoEncuestado.setHijos( hijos);
        Response resultado = servicioDato.registrarDatoEncuestado(datoEncuestado);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void consultarDatoEncuestadoTest() throws Exception{
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        Response resultado = servicioDato.consultarDatoEncuestado(1);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void listarDatoEncuestadoTest() throws Exception{
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        Response resultado = servicioDato.listarDatosEncuestado();
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
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
        Response resultado = servicioDato.actualizarDatoEncuestado(1, datoEncuestado);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }

    @Test
    public void eliminarDatoEncuestadoTest() throws Exception{
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        Response resultado = servicioDato.eliminarDatoEncuestado( (long)1);
        Assert.assertEquals(
                Response.Status.OK.getStatusCode(),
                resultado.getStatus()
        );
    }


}
