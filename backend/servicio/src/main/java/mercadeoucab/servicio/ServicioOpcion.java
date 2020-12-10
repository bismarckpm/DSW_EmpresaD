package mercadeoucab.servicio;

import mercadeoucab.accesodatos.*;
import mercadeoucab.dtos.*;
import mercadeoucab.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/opcion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioOpcion extends AplicacionBase{

    @GET
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoOpcion obtenerOpcion(@PathParam("id") Long id){
        DtoOpcion resultado = new DtoOpcion();
        try{
            DaoOpcion dao = new DaoOpcion();
            Opcion resul = dao.find( id, Opcion.class);
            resultado.set_id( resul.get_id());
            resultado.setNombre_opcion(resul.getNombre_opcion());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<Opcion> listarOpcion(){
        DaoOpcion dao = new DaoOpcion();
        return dao.findAll( Opcion.class);
    }

    @POST
    @Path("/")
    public DtoOpcion registrarOpcion(DtoOpcion DTOO){
        DtoOpcion resultado = new DtoOpcion();
        try{
            DaoOpcion daoO = new DaoOpcion();
            Opcion opcion = new Opcion();
            opcion.setNombre_opcion(DTOO.getNombre_opcion());
            opcion.setActivo(DTOO.getActivo());
            opcion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pregunta pregunta=new Pregunta(DTOO.get_Dtopregunta().get_id());
            opcion.setFk_pregunta(pregunta);
            Opcion resul = daoO.insert( opcion);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }


    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoOpcion actualizarOpcion(DtoOpcion DTOO){
        DtoOpcion resultado = new DtoOpcion();
        try{
            DaoOpcion dao = new DaoOpcion();
            Opcion opcion = dao.find( DTOO.get_id(), Opcion.class);
            if(DTOO.getNombre_opcion()!=null){
                opcion.setNombre_opcion(DTOO.getNombre_opcion());
            }
            if(DTOO.getActivo()!=0){
                opcion.setActivo(DTOO.getActivo());
            }
            if(DTOO.get_Dtopregunta()!=null){
                opcion.setFk_pregunta(new Pregunta(DTOO.get_Dtopregunta().get_id()));
            }
            opcion.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Opcion resul = dao.update( opcion);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoOpcion eliminarOpcion(long id){
        DtoOpcion resultado = new DtoOpcion();
        try{
            DaoOpcion dao = new DaoOpcion();
            Opcion opcion = dao.find( id, Opcion.class);
            opcion.setActivo( 0);
            opcion.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Opcion resul = dao.update( opcion);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }






}
