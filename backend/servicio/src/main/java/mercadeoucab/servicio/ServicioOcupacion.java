package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoOcupacion;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.entidades.Ocupacion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/ocupacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioOcupacion extends AplicacionBase{

    @GET
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoOcupacion obtenerOcupacio(@PathParam("id") Long id){
        DtoOcupacion resultado = new DtoOcupacion();
        try{
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion resul = dao.find( id, Ocupacion.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<Ocupacion> listarOcupacion(){
        DaoOcupacion dao = new DaoOcupacion();
        return dao.findAll( Ocupacion.class);
    }

    @POST
    @Path("/")
    public DtoOcupacion registrarOcupacion(DtoOcupacion DTOO){
        DtoOcupacion resultado = new DtoOcupacion();
        try{
            DaoOcupacion daoO = new DaoOcupacion();
            Ocupacion O= new Ocupacion();
            O.setNombre(DTOO.getNombre());
            O.setActivo(1);
            O.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Ocupacion resul = daoO.insert( O);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoOcupacion actualizarOcupacion(DtoOcupacion DTOO){
        DtoOcupacion resultado = new DtoOcupacion();
        try{
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion O = dao.find( DTOO.get_id(), Ocupacion.class);
            if(DTOO.getNombre()!=null){
                O.setNombre(DTOO.getNombre());
            }
            if(DTOO.getActivo()!=0){
                O.setActivo(DTOO.getActivo());
            }
            O.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Ocupacion resul = dao.update( O);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoOcupacion eliminarOcupacion(long id){
        DtoOcupacion resultado = new DtoOcupacion();
        try{
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion O = dao.find( id, Ocupacion.class);
            O.setActivo( 0);
            O.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Ocupacion resul = dao.update( O);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

}
