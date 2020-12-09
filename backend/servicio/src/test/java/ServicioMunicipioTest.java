import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.servicio.ServicioEstado;
import mercadeoucab.servicio.ServicioMunicipio;
import mercadeoucab.servicio.ServicioPais;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioMunicipioTest {

    @Test
    public void registrarMunicipioTest() throws Exception{
        ServicioEstado servicioEstado = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("Venezuela");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Guarico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado estado = servicioEstado.agregarEstado(dtoEstado);
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("No conozco eso");
        dtoMunicipio.setFk_estado(new DtoEstado(estado.get_id()));
        DtoMunicipio resultado = servicio.registrarMunicipio(dtoMunicipio);

        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void consultarMunicipioTest() throws Exception{
        ServicioEstado servicioEstado = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("Venezuela");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Guarico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado estado = servicioEstado.agregarEstado(dtoEstado);
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("No conozco eso");
        dtoMunicipio.setFk_estado(new DtoEstado(estado.get_id()));
        DtoMunicipio resultado = servicio.registrarMunicipio(dtoMunicipio);

        Municipio consultado = servicio.obtenerMunicipio(resultado.get_id());
        Assert.assertEquals(resultado.get_id(), consultado.get_id());
    }

    @Test
    public void actualizarMunicipioTest() throws Exception{
        ServicioEstado servicioEstado = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("Venezuela");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Guarico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado estado = servicioEstado.agregarEstado(dtoEstado);
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("No conozco eso");
        dtoMunicipio.setFk_estado(new DtoEstado(estado.get_id()));
        DtoMunicipio resultado = servicio.registrarMunicipio(dtoMunicipio);
        resultado.setNombre("Modified");
        Municipio actualizado = servicio.actualizarMunicipio(resultado.get_id(), resultado);
        Assert.assertEquals(resultado.getNombre(), actualizado.getNombre());
    }

    @Test
    public void eliminarMunicipioTest() throws Exception{
        ServicioEstado servicioEstado = new ServicioEstado();
        ServicioPais servicioPais = new ServicioPais();
        DtoPais dtoPais = new DtoPais();
        dtoPais.setNombre("Venezuela");
        DtoPais padre = servicioPais.agregarPais(dtoPais);
        DtoEstado dtoEstado = new DtoEstado();
        dtoEstado.setNombre("Guarico");
        dtoEstado.setFk_pais(new DtoPais(padre.get_id()));
        Estado estado = servicioEstado.agregarEstado(dtoEstado);
        ServicioMunicipio servicio = new ServicioMunicipio();
        DtoMunicipio dtoMunicipio = new DtoMunicipio();
        dtoMunicipio.setNombre("No conozco eso");
        dtoMunicipio.setFk_estado(new DtoEstado(estado.get_id()));
        DtoMunicipio resultado = servicio.registrarMunicipio(dtoMunicipio);
        Municipio eliminar = servicio.eliminarMunicipio(resultado.get_id());
        Assert.assertEquals(0, eliminar.getActivo());
    }

    @Test
    public void listarMunicipiosTest() throws Exception{
        ServicioMunicipio servicio = new ServicioMunicipio();
        List<Municipio> resultado = servicio.listarMunicipios();
        Assert.assertNotNull(resultado);
    }
}
