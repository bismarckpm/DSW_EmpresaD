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
                                                    "601",
                                                    "laptop",
                                                    Date.valueOf("1997-02-28"),
                                                    "masculino",
                                                    50,
                                                    "universitario",
                                                    5);
        datoEncuestado.setFk_lugar(new DtoParroquia(1));
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        DtoUsuario usuario = servicioUsuario.registrarUsuario(new DtoUsuario("nombre","apellido","administrador", "activo", "mail@mail.com"));
        datoEncuestado.setUsuario(new DtoUsuario(usuario.get_id()));
        DatoEncuestado resultado = servicioDato.registrarDatoEncuestado(datoEncuestado);
        Assert.assertNotEquals(0 , resultado.get_id());
    }

    @Test
    public void consultarDatoEncuestadoTest() throws Exception{
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        DtoDatoEncuestado datoEncuestado = new DtoDatoEncuestado("Concepcion",
                "arevalo",
                "602",
                "laptop",
                Date.valueOf("1997-02-28"),
                "masculino",
                50,
                "universitario",
                5);
        datoEncuestado.setFk_lugar(new DtoParroquia(1));
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        DtoUsuario usuario = servicioUsuario.registrarUsuario(new DtoUsuario("nombre","apellido","administrador", "activo", "mail@mail.com"));
        datoEncuestado.setUsuario(new DtoUsuario(usuario.get_id()));
        DatoEncuestado consultar = servicioDato.registrarDatoEncuestado(datoEncuestado);
        DatoEncuestado consultado = servicioDato.consultarDatoEncuestado(consultar.get_id());
        Assert.assertEquals(consultar.get_id(), consultado.get_id());
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
                "arevalo",
                "603",
                "laptop",
                Date.valueOf("1997-02-28"),
                "masculino",
                50,
                "universitario",
                5);
        datoEncuestado.setFk_lugar(new DtoParroquia(1));
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        DtoUsuario usuario = servicioUsuario.registrarUsuario(new DtoUsuario("nombre","apellido","administrador", "activo", "mail@mail.com"));
        datoEncuestado.setUsuario(new DtoUsuario(usuario.get_id()));
        DatoEncuestado actualizar = servicioDato.registrarDatoEncuestado(datoEncuestado);
        datoEncuestado.setSegundoapellido("cambio");
        DatoEncuestado actualizado = servicioDato.actualizarDatoEncuestado(actualizar.get_id(), datoEncuestado);
        Assert.assertNotNull(actualizado.getModificado_el());
    }

    @Test
    public void eliminarDatoEncuestadoTest() throws Exception{
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        DtoDatoEncuestado datoEncuestado = new DtoDatoEncuestado("Concepcion",
                "arevalo",
                "604",
                "laptop",
                Date.valueOf("1997-02-28"),
                "masculino",
                50,
                "universitario",
                5);
        datoEncuestado.setFk_lugar(new DtoParroquia(1));
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        DtoUsuario usuario = servicioUsuario.registrarUsuario(new DtoUsuario("nombre","apellido","administrador", "activo", "mail@mail.com"));
        datoEncuestado.setUsuario(new DtoUsuario(usuario.get_id()));
        DatoEncuestado eliminar = servicioDato.registrarDatoEncuestado(datoEncuestado);
        DatoEncuestado eliminado = servicioDato.eliminarDatoEncuestado(eliminar.get_id());
        Assert.assertNotNull(eliminado.getModificado_el());
    }


}
