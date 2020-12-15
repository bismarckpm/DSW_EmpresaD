package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Usuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path( "/analista" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAnalista extends AplicacionBase{


    @GET
    @Path("/")
    public Response listarAnalistas(){
        JsonObject data;
        JsonArrayBuilder usuariosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoUsuario dao = new DaoUsuario();
            List<Usuario> usuarios = dao.listarAnalistas();
            if(!(usuarios.isEmpty())){
                for(Usuario usuario: usuarios){
                    JsonObject objeto = Json.createObjectBuilder()
                            .add("_id", usuario.get_id())
                            .add("nombre", usuario.getNombre())
                            .add("apellido", usuario.getApellido())
                            .add("rol", usuario.getRol())
                            .add("estado", usuario.getEstado())
                            .add("correo", usuario.getCorreo())
                            .build();
                    usuariosList.add(objeto);
                }
            }
            else{
                JsonObject agregar = Json.createObjectBuilder()
                        .add("Analistas", "Actualmente no hay analistas registrados")
                        .build();
                usuariosList.add(agregar);
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", usuariosList)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("problema", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @GET
    @Path("/{id}/estudios")
    public Response estudiosAnalista(@PathParam("id") long id){
        JsonObject data;
        JsonArrayBuilder estudiosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.estudiosAnalista(new Usuario(id));
            if (!(estudios.isEmpty())){
            for(Estudio estudio: estudios){

                JsonArrayBuilder preguntaslist = Json.createArrayBuilder();
                for(Pregunta pregunta: estudio.getPreguntas()){
                    JsonObject objecto = Json.createObjectBuilder()
                            .add("_id", pregunta.get_id())
                            .add("pregunta",pregunta.getNombrePregunta())
                            .add("tipo", pregunta.getTipo())
                            .add("rango", pregunta.getRango())
                            .build();
                    preguntaslist.add(objecto);
                }
                JsonObject agregar = Json.createObjectBuilder()
                        .add("_id",estudio.get_id())
                        .add("estado", estudio.getEstado())
                        .add("tipo", estudio.getTipo())
                        .add("encuestas_esperadas", estudio.getEscuestasEsperadas())
                        .add("solicitud",Json.createObjectBuilder()
                                .add("_id", estudio.getSolicitud().get_id())
                                .add("estado",estudio.getSolicitud().getEstado()))
                        .add("muestra_poblacion",Json.createObjectBuilder()
                                .add("_id",estudio.getFk_muestra_poblacion().get_id())
                                .add("genero",estudio.getFk_muestra_poblacion().getGenero())
                                .add("nivel_academico", estudio.getFk_muestra_poblacion().getNivelAcademico())
                                .add("rango_edad_inicio", estudio.getFk_muestra_poblacion().getRangoEdadInicio())
                                .add("rango_edad_fin", estudio.getFk_muestra_poblacion().getRangoEdadFin())
                                .add("cantidad_hijos", estudio.getFk_muestra_poblacion().getCantidadHijos())
                                .add("parroquia",Json.createObjectBuilder()
                                        .add("_id",estudio.getFk_muestra_poblacion().getFk_lugar().get_id())
                                        .add("nombre",estudio.getFk_muestra_poblacion().getFk_lugar().getNombre())
                                        .add("valor_socioeconomico", estudio.getFk_muestra_poblacion().getFk_lugar().getValor_socio_economico())
                                        .add("municipio",Json.createObjectBuilder()
                                                .add("_id", estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().get_id())
                                                .add("nombre", estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getNombre())
                                                .add("estado",Json.createObjectBuilder()
                                                        .add("_id",estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().get_id())
                                                        .add("nombre",estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().getNombre())
                                                        .add("pais", Json.createObjectBuilder()
                                                                .add("_id",estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().getFk_pais().get_id())
                                                                .add("nombre",estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().getFk_pais().getNombre()))))))
                        .add("preguntas", preguntaslist)
                        .build();
                estudiosList.add(agregar);
            }//final del for
            }
            else{
                JsonObject agregar = Json.createObjectBuilder()
                                         .add("Sin estudios", "Sin estudios")
                                         .build();
                estudiosList.add(agregar);
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", estudiosList)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();

        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("problema", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}
