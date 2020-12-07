import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Pais;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.servicio.ServicioEstado;
import mercadeoucab.servicio.ServicioMunicipio;
import mercadeoucab.servicio.ServicioPais;
import mercadeoucab.servicio.ServicioParroquia;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioParroquiaTest {

    @Test
    public void registroParroquiaTest() throws Exception{
        ServicioEstado servicioEstado = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("Venezuela");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Guarico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado estado = servicioEstado.agregarEstado(dtoEstado);
        ServicioMunicipio servicioMunicipio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("No conozco eso");
        dtoMunicipio.setFk_estado(new DtoEstado(estado.get_id()));
        DtoMunicipio municipio = servicioMunicipio.registrarMunicipio(dtoMunicipio);
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia();
        dtoParroquia.setNombre("menos conozco");
        dtoParroquia.setValor_socio_economico(0);
        dtoParroquia.setFk_municipio(new DtoMunicipio(municipio.get_id()));
        DtoParroquia resultado = servicio.registrarParroquia( dtoParroquia );
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void consultarParroquiaTest() throws  Exception{
        ServicioEstado servicioEstado = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("Venezuela");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Guarico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado estado = servicioEstado.agregarEstado(dtoEstado);
        ServicioMunicipio servicioMunicipio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("No conozco eso");
        dtoMunicipio.setFk_estado(new DtoEstado(estado.get_id()));
        DtoMunicipio municipio = servicioMunicipio.registrarMunicipio(dtoMunicipio);
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia();
        dtoParroquia.setNombre("menos conozco");
        dtoParroquia.setValor_socio_economico(0);
        dtoParroquia.setFk_municipio(new DtoMunicipio(municipio.get_id()));
        DtoParroquia consultar = servicio.registrarParroquia( dtoParroquia );
        Parroquia consultada = servicio.consultarParroquia(consultar.get_id());
        Assert.assertEquals(consultar.get_id(), consultada.get_id());
    }

    @Test
    public void actualizarParroquiaTest() throws Exception{
        ServicioEstado servicioEstado = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("Venezuela");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Guarico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado estado = servicioEstado.agregarEstado(dtoEstado);
        ServicioMunicipio servicioMunicipio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("No conozco eso");
        dtoMunicipio.setFk_estado(new DtoEstado(estado.get_id()));
        DtoMunicipio municipio = servicioMunicipio.registrarMunicipio(dtoMunicipio);
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia();
        dtoParroquia.setNombre("menos conozco");
        dtoParroquia.setValor_socio_economico(1000);
        dtoParroquia.setFk_municipio(new DtoMunicipio(municipio.get_id()));
        DtoParroquia actualizar = servicio.registrarParroquia( dtoParroquia );
        actualizar.setNombre("CAMBIADO");
        Parroquia actualizado = servicio.actualizarParroquia(actualizar.get_id(), actualizar);
        Assert.assertEquals(actualizar.getNombre(), actualizado.getNombre());
    }

    @Test
    public void eliminarParroquiaTest() throws Exception{
        ServicioEstado servicioEstado = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("Venezuela");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Guarico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado estado = servicioEstado.agregarEstado(dtoEstado);
        ServicioMunicipio servicioMunicipio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("No conozco eso");
        dtoMunicipio.setFk_estado(new DtoEstado(estado.get_id()));
        DtoMunicipio municipio = servicioMunicipio.registrarMunicipio(dtoMunicipio);
        ServicioParroquia servicio = new ServicioParroquia();
        DtoParroquia dtoParroquia = new DtoParroquia();
        dtoParroquia.setNombre("menos conozco");
        dtoParroquia.setValor_socio_economico(0);
        dtoParroquia.setFk_municipio(new DtoMunicipio(municipio.get_id()));
        DtoParroquia resultado = servicio.registrarParroquia( dtoParroquia );
        Parroquia eliminada = servicio.eliminarParroquia(resultado.get_id());
        Assert.assertEquals(0, eliminada.getActivo());
    }

    @Test
    public void listarParroquiasTest() throws Exception{
        ServicioParroquia servicio = new ServicioParroquia();
        List<Parroquia> resultado = servicio.listarParroquias();
        Assert.assertNotNull(resultado);
    }
}
