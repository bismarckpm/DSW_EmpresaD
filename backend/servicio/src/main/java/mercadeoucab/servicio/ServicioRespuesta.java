package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.entidades.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/respuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioRespuesta extends AplicacionBase{

    @GET
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoRespuesta obtenerRespuesta(@PathParam("id") Long id){
        DtoRespuesta resultado = new DtoRespuesta();
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta resul = dao.find( id, Respuesta.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<Respuesta> listarRespuesta(){
        DaoRespuesta dao = new DaoRespuesta();
        return dao.findAll( Respuesta.class);
    }

    @POST
    @Path("/")
    public DtoRespuesta registrarRespuesta(DtoRespuesta dtoRespuesta){
        DtoRespuesta resultado = new DtoRespuesta();
        try{

            DaoRespuesta dao = new DaoRespuesta();
            Respuesta respuesta = new Respuesta();

            EncuestaEstudio encuestaEstudio =new EncuestaEstudio(dtoRespuesta.getDtoEncuestaEstudio().get_id());
            respuesta.setEncuesta_estudio(encuestaEstudio);

            Opcion opcion = new Opcion(dtoRespuesta.get_dtoopcion().get_id());
            respuesta.setFk_opcion(opcion);

            Usuario usuario = new Usuario(dtoRespuesta.getDtousuario().get_id());
            respuesta.setFk_usuario(usuario);

            respuesta.setRespuesta(dtoRespuesta.getRespuesta());
            respuesta.setActivo(1);
            respuesta.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));

            Respuesta resul = dao.insert( respuesta );
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }


    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoRespuesta actualizarRespuesta(@PathParam("id") long id, DtoRespuesta dtoRespuesta){
        DtoRespuesta resultado = new DtoRespuesta();
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta respuesta = dao.find( id, Respuesta.class);

            respuesta.setRespuesta(dtoRespuesta.getRespuesta());
            respuesta.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Respuesta resul = dao.update( respuesta );
            resultado.set_id( resul.get_id());
            resultado.setModificado_el(resul.getModificado_el());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoRespuesta eliminarRespuesta(@PathParam("id") long id){
        DtoRespuesta resultado = new DtoRespuesta();
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta respuesta = dao.find( id, Respuesta.class);
            respuesta.setActivo( 0);
            respuesta.setModificado_el(new Date(Calendar
                                            .getInstance()
                                            .getTime()
                                            .getTime()));
            Respuesta resul = dao.update( respuesta );
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }


}
