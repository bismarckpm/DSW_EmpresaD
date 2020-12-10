import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.dtos.DtoOcupacionEncuestado;
import mercadeoucab.entidades.OcupacionEncuestado;
import mercadeoucab.excepciones.PruebaExcepcion;
import mercadeoucab.servicio.ServicioOcupacionEncuestado;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioOcupacionEncuestadoTest {

    @Test
    public void registrarOcupacionEncuestadoTest() throws PruebaExcepcion {
        ServicioOcupacionEncuestado servicio = new ServicioOcupacionEncuestado();
        DtoOcupacionEncuestado DTOOE= new DtoOcupacionEncuestado();
        DTOOE.setActivo(1);
        DtoDatoEncuestado DE=new DtoDatoEncuestado();
        DE.set_id(2);
        DTOOE.setFk_dato_encuestado(DE);
        DtoOcupacion DOC=new DtoOcupacion();
        DOC.setId(1);
        DTOOE.setFk_ocupacion(DOC);
        DtoOcupacionEncuestado resultado = servicio.registrarOcupacionEncuestado( DTOOE);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerOcupacionEncuestadoTest() throws PruebaExcepcion {
        ServicioOcupacionEncuestado servicio = new ServicioOcupacionEncuestado();
        DtoOcupacionEncuestado DTOOE= new DtoOcupacionEncuestado();
        DTOOE.setActivo(1);
        DtoDatoEncuestado DE=new DtoDatoEncuestado();
        DE.set_id(1);
        DTOOE.setFk_dato_encuestado(DE);
        DtoOcupacion DOC=new DtoOcupacion();
        DOC.setId(1);
        DTOOE.setFk_ocupacion(DOC);
        DtoOcupacionEncuestado paraConsultar = servicio.registrarOcupacionEncuestado( DTOOE);
        DtoOcupacionEncuestado resultado = servicio.obtenerOcupacioEncuestado(paraConsultar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void obtenerListaOcupacionEncuestadoTest(){
        ServicioOcupacionEncuestado servicio = new ServicioOcupacionEncuestado();
        List<OcupacionEncuestado> LDTOO= servicio.listarOcupacionEncuestado();
        Assert.assertNotNull(LDTOO);
    }

    @Test
    public void actualizarOcupacionEncuestadoTest() throws Exception {
        ServicioOcupacionEncuestado servicio = new ServicioOcupacionEncuestado();
        DtoOcupacionEncuestado DTOOE= new DtoOcupacionEncuestado();
        DTOOE.setActivo(1);
        DtoDatoEncuestado DE=new DtoDatoEncuestado();
        DE.set_id(2);
        DTOOE.setFk_dato_encuestado(DE);
        DtoOcupacion DOC=new DtoOcupacion();
        DOC.setId(1);
        DTOOE.setFk_ocupacion(DOC);
        DtoOcupacionEncuestado paraActualizar = servicio.registrarOcupacionEncuestado( DTOOE);
        paraActualizar.setActivo(2);
        DtoOcupacionEncuestado resultado = servicio.actualizarOcupacionEncuestado( paraActualizar);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void eliminarOcupacionEncuestadoTest() throws Exception {
        ServicioOcupacionEncuestado servicio = new ServicioOcupacionEncuestado();
        DtoOcupacionEncuestado DTOOE= new DtoOcupacionEncuestado();
        DTOOE.setActivo(1);
        DtoDatoEncuestado DE=new DtoDatoEncuestado();
        DE.set_id(2);
        DTOOE.setFk_dato_encuestado(DE);
        DtoOcupacion DOC=new DtoOcupacion();
        DOC.setId(1);
        DTOOE.setFk_ocupacion(DOC);
        DtoOcupacionEncuestado paraEliminar = servicio.registrarOcupacionEncuestado( DTOOE);
        DtoOcupacionEncuestado resultado = servicio.eliminarOcupacionEncuestado(paraEliminar.get_id());
        Assert.assertNotEquals(resultado.get_id(), 0);
    }


/*




*/


}
