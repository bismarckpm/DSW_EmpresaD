package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/estudios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstudio {

    @GET
    @Path("/")
    public List<Estudio> listarEstudios(){
        DaoEstudio dao = new DaoEstudio();
        return dao.findAll(Estudio.class);
    }

    @POST
    @Path("/")
    public Estudio agregarEstudio(DtoEstudio dtoEstudio){
        Estudio resultado = new Estudio();
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = new Estudio();
            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setTipo(dtoEstudio.getTipo());
            estudio.setEscuestasEsperadas(dtoEstudio.getEscuestasEsperadas());
            estudio.setActivo(1);
            estudio.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));

            Solicitud solicitud = new Solicitud(dtoEstudio.getSolicitud().get_id());
            estudio.setSolicitud( solicitud );
            Usuario usuario = new Usuario(dtoEstudio.getFk_usuario().get_id());
            estudio.setFk_usuario( usuario );
            MuestraPoblacion muestraPoblacion = new MuestraPoblacion(dtoEstudio.getFk_muestra_poblacion().get_id());
            estudio.setFk_muestra_poblacion( muestraPoblacion );

            resultado = dao.insert(estudio);
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema + "**************");
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public Estudio consultarEstudio(@PathParam("id") long id){
        DaoEstudio dao = new DaoEstudio();
        return dao.find(id, Estudio.class);
    }

    @PUT
    @Path("/eliminar/{id}")
    public Estudio eliminarEstudio(@PathParam("id") long id){
        Estudio resultado = new Estudio();
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.setActivo(0);
            estudio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.update( estudio );
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return  resultado;
    }

    @PUT
    @Path("/{id}")
    public Estudio actualizarEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio){
        Estudio resultado = new Estudio();
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setTipo(dtoEstudio.getTipo());
            estudio.setEscuestasEsperadas(dtoEstudio.getEscuestasEsperadas());
            estudio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.update(estudio);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }
}
