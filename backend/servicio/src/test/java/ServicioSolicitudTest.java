import mercadeoucab.dtos.DtoMarca;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Marca;
import mercadeoucab.servicio.ServicioSolicitud;
import org.junit.Assert;
import org.junit.Test;

public class ServicioSolicitudTest {

    @Test
    public void registrarSolicitudTest() throws Exception {
        ServicioSolicitud servicio = new ServicioSolicitud();
        DtoSolicitud dtoSolicitud = new DtoSolicitud();
        dtoSolicitud.setEstado("solicitada");
        // Se debe tener una marca agregada a la BD
        dtoSolicitud.setMarca( new DtoMarca(1));
        dtoSolicitud.setUsuario( new DtoUsuario( 1));
        DtoSolicitud resultado = servicio.registrarSolicitud( dtoSolicitud);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }


}
