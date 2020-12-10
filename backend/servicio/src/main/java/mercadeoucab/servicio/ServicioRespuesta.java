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
    public DtoRespuesta registrarRespuesta(DtoRespuesta DTOR){
        DtoRespuesta resultado = new DtoRespuesta();
        try{
            DaoRespuesta daoO = new DaoRespuesta();
            Respuesta R = new Respuesta();
            EncuestaEstudio EE=new EncuestaEstudio();
            EE.set_id(DTOR.getDtoEncuestaEstudio().get_id());
            R.setEncuesta_estudio(EE);
            Opcion O=new Opcion();
            O.set_id(DTOR.get_dtoopcion().get_id());
            R.setFk_opcion(O);
            Usuario U=new Usuario(DTOR.getDtousuario().get_id());
            R.setFk_usuario(U);
            R.setRespuesta(DTOR.getRespuesta());
            R.setActivo(DTOR.getActivo());
            R.setRespuesta(DTOR.getRespuesta());
            R.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Respuesta resul = daoO.insert( R);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }


    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoRespuesta actualizarRespuesta(DtoRespuesta DTOR){
        DtoRespuesta resultado = new DtoRespuesta();
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta R = dao.find( DTOR.get_id(), Respuesta.class);
            if(DTOR.getDtoEncuestaEstudio()!=null){
                R.setEncuesta_estudio(new EncuestaEstudio(DTOR.getDtoEncuestaEstudio().get_id()));
            }
            if(DTOR.get_dtoopcion()!=null){
                R.setFk_opcion(new Opcion(DTOR.get_dtoopcion().get_id()));
            }
            if(DTOR.getDtousuario()!=null){
                R.setFk_usuario(new Usuario(DTOR.getDtousuario().get_id()));
            }
            if(DTOR.getRespuesta()!=null){
                R.setRespuesta(DTOR.getRespuesta());
            }
            if (DTOR.getActivo()!=0){
                R.setActivo(DTOR.getActivo());
            }

            R.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Respuesta resul = dao.update( R);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoRespuesta eliminarRespuesta(long id){
        DtoRespuesta resultado = new DtoRespuesta();
        try{
            DaoRespuesta dao = new DaoRespuesta();
            Respuesta R = dao.find( id, Respuesta.class);
            R.setActivo( 0);
            R.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Respuesta resul = dao.update( R);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }


}
