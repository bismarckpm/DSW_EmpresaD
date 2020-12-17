package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/estudios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstudio {

    @GET
    @Path("/")
    public Response listarEstudios(){
        JsonObject data;
        JsonArrayBuilder estudiosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.findAll(Estudio.class);
            for(Estudio estudio: estudios){
                if(estudio.getActivo() == 1){

                    JsonArrayBuilder preguntaslist = Json.createArrayBuilder();
                    for(Pregunta pregunta: estudio.getPreguntas()){
                        JsonObject objecto = Json.createObjectBuilder()
                                                .add("_id", pregunta.get_id())
                                                .add("pregunta",pregunta.getNombrePregunta())
                                                .add("tipo", pregunta.getTipo())
                                                .add("rango", pregunta.getRango())
                                                .add("usuario",Json.createObjectBuilder()
                                                        .add("_id",pregunta.getUsuario().get_id())
                                                        .add("nombre",pregunta.getUsuario().getNombre())
                                                        .add("apellido",pregunta.getUsuario().getApellido())
                                                        .add("correo",pregunta.getUsuario().getCorreo())
                                                        .add("rol",pregunta.getUsuario().getRol()))
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
                                             .add("analista", Json.createObjectBuilder()
                                                                    .add("_id", estudio.getFk_usuario().get_id())
                                                                    .add("nombre",estudio.getFk_usuario().getNombre())
                                                                    .add("apellido", estudio.getFk_usuario().getApellido())
                                                                    .add("correo", estudio.getFk_usuario().getCorreo())
                                                                    .add("rol", estudio.getFk_usuario().getRol()))
                                             .add("muestra_poblacion",Json.createObjectBuilder()
                                                                             .add("_id",estudio.getFk_muestra_poblacion().get_id())
                                                                             .add("genero",estudio.getFk_muestra_poblacion().getGenero())
                                                                             .add("nivel_economico", estudio.getFk_muestra_poblacion().getNivelEconomico())
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
                }
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
                    .add("mensaje",problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(data).build();
        }
        return resultado;
    }

    @POST
    @Path("/")
    public Response agregarEstudio(DtoEstudio dtoEstudio){
        JsonObject data;
        Response resultado = null;
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

            for(DtoPregunta pregunta: dtoEstudio.getPreguntas()){
                Pregunta pregunta1 = new Pregunta(pregunta.get_id());
                estudio.addpregunta(pregunta1);
            }

            Estudio resul = dao.insert(estudio);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Estudio creada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("mensaje",problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(data).build();

        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public Response consultarEstudio(@PathParam("id") long id){
        JsonObject data;
        JsonObject estudioJson;
        Response resultado = null;
        try {

            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);

            JsonArrayBuilder preguntaslist = Json.createArrayBuilder();

           if(!(estudio.getEncuestaEstudio().isEmpty())){
                for(EncuestaEstudio encuestaEstudio: estudio.getEncuestaEstudio()){
                    String tipo = encuestaEstudio.getFk_pregunta().getTipo();
                    JsonObject objeto = null;
                    JsonArrayBuilder opcionesList = null;
                    switch (tipo){
                        case "abierta":
                            objeto = Json.createObjectBuilder()
                                                    .add("_id", encuestaEstudio.get_id())
                                                    .add("pregunta",Json.createObjectBuilder()
                                                                           .add("_id",encuestaEstudio.getFk_pregunta().get_id())
                                                                           .add("pregunta", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                                                           .add("tipo",encuestaEstudio.getFk_pregunta().getTipo()))
                                                    .build();
                            preguntaslist.add(objeto);
                            break;

                        case "multiple":
                            opcionesList = Json.createArrayBuilder();
                            for(Opcion opcion: encuestaEstudio.getFk_pregunta().getOpciones()){
                                JsonObject option = Json.createObjectBuilder()
                                                        .add("_id", opcion.get_id())
                                                        .add("pregunta",opcion.getNombre_opcion())
                                                        .build();
                                opcionesList.add(option);
                            }
                            objeto = Json.createObjectBuilder()
                                                    .add("_id", encuestaEstudio.get_id())
                                                    .add("pregunta",Json.createObjectBuilder()
                                                                            .add("_id",encuestaEstudio.getFk_pregunta().get_id())
                                                                            .add("pregunta", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                                                            .add("tipo",encuestaEstudio.getFk_pregunta().getTipo())
                                                                            .add("opciones", opcionesList))
                                    .build();
                            preguntaslist.add(objeto);
                            break;
                        case "simple":
                            opcionesList = Json.createArrayBuilder();
                            for(Opcion opcion: encuestaEstudio.getFk_pregunta().getOpciones()){
                                JsonObject option = Json.createObjectBuilder()
                                        .add("_id", opcion.get_id())
                                        .add("nombre",opcion.getNombre_opcion())
                                        .build();
                                opcionesList.add(option);
                            }
                            objeto = Json.createObjectBuilder()
                                    .add("_id", encuestaEstudio.get_id())
                                    .add("pregunta",Json.createObjectBuilder()
                                            .add("_id",encuestaEstudio.getFk_pregunta().get_id())
                                            .add("nombre", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                            .add("tipo",encuestaEstudio.getFk_pregunta().getTipo())
                                            .add("opciones", opcionesList))
                                    .build();
                            preguntaslist.add(objeto);
                            break;
                        case "boolean":
                            objeto = Json.createObjectBuilder()
                                    .add("_id", encuestaEstudio.get_id())
                                    .add("pregunta",Json.createObjectBuilder()
                                            .add("_id",encuestaEstudio.getFk_pregunta().get_id())
                                            .add("pregunta", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                            .add("tipo",encuestaEstudio.getFk_pregunta().getTipo()))
                                    .build();
                            preguntaslist.add(objeto);
                            break;
                        case "rango":
                            objeto = Json.createObjectBuilder()
                                    .add("_id", encuestaEstudio.get_id())
                                    .add("pregunta",Json.createObjectBuilder()
                                            .add("_id",encuestaEstudio.getFk_pregunta().get_id())
                                            .add("pregunta", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                            .add("tipo",encuestaEstudio.getFk_pregunta().getTipo())
                                            .add("rango",encuestaEstudio.getFk_pregunta().getRango()))
                                    .build();
                            preguntaslist.add(objeto);
                            break;
                    }//final switch
                }//Final for
            }//IF de la encuesta
            estudioJson = Json.createObjectBuilder()
                    .add("_id",estudio.get_id())
                    .add("estado", estudio.getEstado())
                    .add("tipo", estudio.getTipo())
                    .add("encuestas_esperadas", estudio.getEscuestasEsperadas())
                    .add("solicitud",Json.createObjectBuilder()
                            .add("_id", estudio.getSolicitud().get_id())
                            .add("estado",estudio.getSolicitud().getEstado()))
                    .add("analista", Json.createObjectBuilder()
                            .add("_id", estudio.getFk_usuario().get_id())
                            .add("nombre",estudio.getFk_usuario().getNombre())
                            .add("apellido", estudio.getFk_usuario().getApellido())
                            .add("correo", estudio.getFk_usuario().getCorreo())
                            .add("rol", estudio.getFk_usuario().getRol()))
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
                    .add("encuesta", preguntaslist)
                    .build();
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", estudioJson)
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
            System.out.print(problema);
        }
        return resultado;
    }

    @PUT
    @Path("/eliminar/{id}")
    public Response eliminarEstudio(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.setActivo(0);
            estudio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Estudio resul = dao.update( estudio );
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Estudio eliminado con exito")
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
        return  resultado;
    }

    @PUT
    @Path("/{id}")
    public Response actualizarEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio){
        JsonObject data;
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setTipo(dtoEstudio.getTipo());
            estudio.setEscuestasEsperadas(dtoEstudio.getEscuestasEsperadas());
            estudio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Estudio resul = dao.update(estudio);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "estudio actualizado con exito")
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
