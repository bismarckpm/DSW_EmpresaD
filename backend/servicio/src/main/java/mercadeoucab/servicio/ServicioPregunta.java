package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/preguntas" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPregunta extends AplicacionBase{

    @GET
    @Path("/")
    public List<Pregunta> listarPreguntas(){
        DaoPregunta dao = new DaoPregunta();
        return dao.findAll(Pregunta.class);
    }

    @POST
    @Path("/")
    public Pregunta registrarPregunta(DtoPregunta dtoPregunta){
        Pregunta resultado = new Pregunta();
        try {
            DaoPregunta dao = new DaoPregunta();
            Pregunta pregunta = new Pregunta();
            pregunta.setNombrePregunta(dtoPregunta.getNombre_pregunta());
            pregunta.setTipo(dtoPregunta.getTipo());
            pregunta.setRango(dtoPregunta.getRango());
            Usuario usuario = new Usuario(dtoPregunta.getUsuarioDto().get_id());
            pregunta.setUsuario(usuario);
            pregunta.setActivo(1);
            pregunta.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Estudio estudio = new Estudio(dtoPregunta.getEstudio().get_id());
            pregunta.addEstudio(estudio);
            resultado = dao.insert(pregunta);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public Pregunta consultarPregunta(@PathParam("id") long id){
        DaoPregunta dao = new DaoPregunta();
        return dao.find(id, Pregunta.class);
    }

    @PUT
    @Path("/eliminar/{id}")
    public Pregunta eliminarPregunta(@PathParam("id") long id){
        Pregunta resultado = new Pregunta();
        try {
            DaoPregunta dao = new DaoPregunta();
            Pregunta pregunta = dao.find(id, Pregunta.class);
            pregunta.setActivo(0);
            pregunta.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.update(pregunta);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public Pregunta actualizarPregunta(@PathParam("id") long id, DtoPregunta dtoPregunta){
        Pregunta resultado = new Pregunta();
        try {
            DaoPregunta dao = new DaoPregunta();
            Pregunta pregunta = dao.find(id, Pregunta.class);
            pregunta.setNombrePregunta(dtoPregunta.getNombre_pregunta());
            pregunta.setTipo(dtoPregunta.getTipo());
            pregunta.setRango(dtoPregunta.getRango());
            pregunta.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.update( pregunta );
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

}
