import mercadeoucab.dtos.*;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Hijo;
import mercadeoucab.servicio.ServicioDatoEncuestado;
import mercadeoucab.servicio.ServicioHijo;
import mercadeoucab.servicio.ServicioUsuario;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class ServicioHijoTest {

    @Test
    public void registrarHijoTest() throws Exception{
        ServicioHijo servicio = new ServicioHijo();
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        DtoDatoEncuestado datoEncuestado = new DtoDatoEncuestado("Concepcion",
                "arevalo",
                "7000",
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
        DtoOcupacion dtoOcupacion = new DtoOcupacion(1);
        datoEncuestado.setOcupacion(dtoOcupacion);
        DatoEncuestado datos = servicioDato.registrarDatoEncuestado(datoEncuestado);

        DtoHijo dtoHijo = new DtoHijo("masculino", Date.valueOf("1997-02-28"), new DtoDatoEncuestado(datos.get_id()));
        Hijo resultado = servicio.registrarHijo(dtoHijo);
        Assert.assertNotEquals(0, resultado.get_id());
    }

    @Test
    public void consultarHijoTest() throws Exception{
        ServicioHijo servicio = new ServicioHijo();
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        DtoDatoEncuestado datoEncuestado = new DtoDatoEncuestado("Concepcion",
                "arevalo",
                "7001",
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
        DtoOcupacion dtoOcupacion = new DtoOcupacion(1);
        datoEncuestado.setOcupacion(dtoOcupacion);
        DatoEncuestado datos = servicioDato.registrarDatoEncuestado(datoEncuestado);
        DtoHijo dtoHijo = new DtoHijo("masculino", Date.valueOf("2015-02-28"), new DtoDatoEncuestado(datos.get_id()));
        Hijo consultar = servicio.registrarHijo(dtoHijo);
        Hijo consultado = servicio.consultarHijo(consultar.get_id());
        Assert.assertEquals(consultar.get_id(), consultado.get_id());
    }

    @Test
    public void listarHijosTest() throws Exception{
        ServicioHijo servicio = new ServicioHijo();
        List<Hijo> hijos = servicio.listarHijos();
        Assert.assertNotNull(hijos);
    }

    @Test
    public void eliminarHijoTest() throws Exception{
        ServicioHijo servicio = new ServicioHijo();
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        DtoDatoEncuestado datoEncuestado = new DtoDatoEncuestado("Concepcion",
                "arevalo",
                "7002",
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
        DtoOcupacion dtoOcupacion = new DtoOcupacion(1);
        datoEncuestado.setOcupacion(dtoOcupacion);
        DatoEncuestado datos = servicioDato.registrarDatoEncuestado(datoEncuestado);
        DtoHijo dtoHijo = new DtoHijo("masculino", Date.valueOf("2015-02-28"), new DtoDatoEncuestado(datos.get_id()));
        Hijo eliminar = servicio.registrarHijo(dtoHijo);
        Hijo eliminado = servicio.eliminarHijo(eliminar.get_id());
        Assert.assertEquals(0, eliminado.getActivo());
    }

    @Test
    public void actualizarHijoTest() throws Exception{
        ServicioHijo servicio = new ServicioHijo();
        ServicioDatoEncuestado servicioDato = new ServicioDatoEncuestado();
        DtoDatoEncuestado datoEncuestado = new DtoDatoEncuestado("Concepcion",
                "arevalo",
                "7003",
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
        DtoOcupacion dtoOcupacion = new DtoOcupacion(1);
        datoEncuestado.setOcupacion(dtoOcupacion);
        DatoEncuestado datos = servicioDato.registrarDatoEncuestado(datoEncuestado);
        DtoHijo dtoHijo = new DtoHijo("masculino", Date.valueOf("2015-02-28"), new DtoDatoEncuestado(datos.get_id()));
        Hijo actualizar = servicio.registrarHijo(dtoHijo);
        dtoHijo.setGenero("femenino");
        Hijo actualizado = servicio.actuaizarHijo(actualizar.get_id(), dtoHijo);
        Assert.assertNotNull(actualizado.getModificado_el());
    }
}
