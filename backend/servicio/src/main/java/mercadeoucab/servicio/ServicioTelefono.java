package mercadeoucab.servicio;

import jdk.nashorn.internal.objects.annotations.Getter;
import mercadeoucab.accesodatos.DaoTelefono;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Telefono;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;

@Path( "/telefonos" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioTelefono extends AplicacionBase {

    @GET
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoTelefono obtenerTelefono( long id){
        DtoTelefono resultado = new DtoTelefono();
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono resul = dao.find( id, Telefono.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @POST
    @Path("/")
    public DtoTelefono registrarTelefono( DtoTelefono dtoTelefono){
        DtoTelefono resultado = new DtoTelefono();
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = new Telefono();
            telefono.setTelefono( dtoTelefono.getTelefono());

            telefono.setActivo( 1);
            telefono.setCreado_el(new Date(Calendar
                                            .getInstance()
                                            .getTime()
                                            .getTime()));
            DatoEncuestado datoEncuestado = new DatoEncuestado(
                    dtoTelefono.getDatoEncuestado().get_id()
            );
            telefono.setDatoEncuestado( datoEncuestado);
            Telefono resul = dao.insert( telefono);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoTelefono modificarTelefono( DtoTelefono dtoTelefono){
        DtoTelefono resultado = new DtoTelefono();
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = dao.find( dtoTelefono.get_id(), Telefono.class);
            telefono.setTelefono( dtoTelefono.getTelefono());
            telefono.setActivo( 1);
            telefono.setModificado_el(new Date(Calendar
                                            .getInstance()
                                            .getTime()
                                            .getTime()));
            Telefono resul = dao.update( telefono);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoTelefono eliminarTelefono( long id){
        DtoTelefono resultado = new DtoTelefono();
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = dao.find( id, Telefono.class);
            telefono.setActivo( 0);
            telefono.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Telefono resul = dao.update( telefono);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }
}
