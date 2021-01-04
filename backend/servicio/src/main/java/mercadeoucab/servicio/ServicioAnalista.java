package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.swing.*;
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
                    if(usuario.getActivo() == 1) {
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
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", usuariosList)
                        .build();
            }
            else{
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "Actualmente no hay analistas registrados")
                        .build();
            }

            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
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

                    if(estudio.getActivo() == 1) {

                        JsonArrayBuilder preguntaslist = Json.createArrayBuilder();

                        if (!(estudio.getEncuestaEstudio().isEmpty())) {

                            for (EncuestaEstudio encuestaEstudio : estudio.getEncuestaEstudio()) {
                                String tipo = encuestaEstudio.getFk_pregunta().getTipo();
                                JsonObject objeto = null;
                                JsonArrayBuilder opcionesList = null;
                                switch (tipo) {
                                    case "abierta":
                                        objeto = Json.createObjectBuilder()
                                                .add("_id", encuestaEstudio.get_id())
                                                .add("pregunta", Json.createObjectBuilder()
                                                        .add("_id", encuestaEstudio.getFk_pregunta().get_id())
                                                        .add("nombre", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                                        .add("tipo", encuestaEstudio.getFk_pregunta().getTipo()))
                                                .add("usuario", Json.createObjectBuilder()
                                                        .add("_id", encuestaEstudio.getFk_pregunta().getUsuario().get_id())
                                                        .add("nombre", encuestaEstudio.getFk_pregunta().getUsuario().getNombre())
                                                        .add("apellido", encuestaEstudio.getFk_pregunta().getUsuario().getApellido())
                                                        .add("correo", encuestaEstudio.getFk_pregunta().getUsuario().getCorreo())
                                                        .add("rol", encuestaEstudio.getFk_pregunta().getUsuario().getRol()))
                                                .build();
                                        preguntaslist.add(objeto);
                                        break;

                                    case "multiple":
                                        opcionesList = Json.createArrayBuilder();
                                        for (Opcion opcion : encuestaEstudio.getFk_pregunta().getOpciones()) {
                                            JsonObject option = Json.createObjectBuilder()
                                                    .add("_id", opcion.get_id())
                                                    .add("nombre_nombre", opcion.getNombre_opcion())
                                                    .build();
                                            opcionesList.add(option);
                                        }
                                        objeto = Json.createObjectBuilder()
                                                .add("_id", encuestaEstudio.get_id())
                                                .add("pregunta", Json.createObjectBuilder()
                                                        .add("_id", encuestaEstudio.getFk_pregunta().get_id())
                                                        .add("nombre", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                                        .add("tipo", encuestaEstudio.getFk_pregunta().getTipo())
                                                        .add("opciones", opcionesList))
                                                .add("usuario", Json.createObjectBuilder()
                                                        .add("_id", encuestaEstudio.getFk_pregunta().getUsuario().get_id())
                                                        .add("nombre", encuestaEstudio.getFk_pregunta().getUsuario().getNombre())
                                                        .add("apellido", encuestaEstudio.getFk_pregunta().getUsuario().getApellido())
                                                        .add("correo", encuestaEstudio.getFk_pregunta().getUsuario().getCorreo())
                                                        .add("rol", encuestaEstudio.getFk_pregunta().getUsuario().getRol()))
                                                .build();
                                        preguntaslist.add(objeto);
                                        break;
                                    case "simple":
                                        opcionesList = Json.createArrayBuilder();
                                        for (Opcion opcion : encuestaEstudio.getFk_pregunta().getOpciones()) {
                                            JsonObject option = Json.createObjectBuilder()
                                                    .add("_id", opcion.get_id())
                                                    .add("nombre_opcion", opcion.getNombre_opcion())
                                                    .build();
                                            opcionesList.add(option);
                                        }
                                        objeto = Json.createObjectBuilder()
                                                .add("_id", encuestaEstudio.get_id())
                                                .add("pregunta", Json.createObjectBuilder()
                                                        .add("_id", encuestaEstudio.getFk_pregunta().get_id())
                                                        .add("nombre", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                                        .add("tipo", encuestaEstudio.getFk_pregunta().getTipo())
                                                        .add("opciones", opcionesList))
                                                .add("usuario", Json.createObjectBuilder()
                                                        .add("_id", encuestaEstudio.getFk_pregunta().getUsuario().get_id())
                                                        .add("nombre", encuestaEstudio.getFk_pregunta().getUsuario().getNombre())
                                                        .add("apellido", encuestaEstudio.getFk_pregunta().getUsuario().getApellido())
                                                        .add("correo", encuestaEstudio.getFk_pregunta().getUsuario().getCorreo())
                                                        .add("rol", encuestaEstudio.getFk_pregunta().getUsuario().getRol()))
                                                .build();
                                        preguntaslist.add(objeto);
                                        break;
                                    case "boolean":
                                        objeto = Json.createObjectBuilder()
                                                .add("_id", encuestaEstudio.get_id())
                                                .add("pregunta", Json.createObjectBuilder()
                                                        .add("_id", encuestaEstudio.getFk_pregunta().get_id())
                                                        .add("nombre", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                                        .add("tipo", encuestaEstudio.getFk_pregunta().getTipo()))
                                                .add("usuario", Json.createObjectBuilder()
                                                        .add("_id", encuestaEstudio.getFk_pregunta().getUsuario().get_id())
                                                        .add("nombre", encuestaEstudio.getFk_pregunta().getUsuario().getNombre())
                                                        .add("apellido", encuestaEstudio.getFk_pregunta().getUsuario().getApellido())
                                                        .add("correo", encuestaEstudio.getFk_pregunta().getUsuario().getCorreo())
                                                        .add("rol", encuestaEstudio.getFk_pregunta().getUsuario().getRol()))
                                                .build();
                                        preguntaslist.add(objeto);
                                        break;
                                    case "rango":
                                        objeto = Json.createObjectBuilder()
                                                .add("_id", encuestaEstudio.get_id())
                                                .add("pregunta", Json.createObjectBuilder()
                                                        .add("_id", encuestaEstudio.getFk_pregunta().get_id())
                                                        .add("nombre", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                                        .add("tipo", encuestaEstudio.getFk_pregunta().getTipo())
                                                        .add("rango", encuestaEstudio.getFk_pregunta().getRango()))
                                                .add("usuario", Json.createObjectBuilder()
                                                        .add("_id", encuestaEstudio.getFk_pregunta().getUsuario().get_id())
                                                        .add("nombre", encuestaEstudio.getFk_pregunta().getUsuario().getNombre())
                                                        .add("apellido", encuestaEstudio.getFk_pregunta().getUsuario().getApellido())
                                                        .add("correo", encuestaEstudio.getFk_pregunta().getUsuario().getCorreo())
                                                        .add("rol", encuestaEstudio.getFk_pregunta().getUsuario().getRol()))
                                                .build();
                                        preguntaslist.add(objeto);
                                        break;
                                }//final switch

                            }//Final for
                        }//IF de la encuesta

                        JsonObject agregar = Json.createObjectBuilder()
                                .add("_id", estudio.get_id())
                                .add("estado", estudio.getEstado())
                                .add("tipo", estudio.getTipo())
                                .add("encuestas_esperadas", estudio.getEncuestasEsperadas())
                                .add("solicitud", Json.createObjectBuilder()
                                        .add("_id", estudio.getSolicitud().get_id())
                                        .add("estado", estudio.getSolicitud().getEstado()))
                                .add("muestra_poblacion", Json.createObjectBuilder()
                                        .add("_id", estudio.getFk_muestra_poblacion().get_id())
                                        .add("genero", estudio.getFk_muestra_poblacion().getGenero())
                                        .add("nivel_academico", estudio.getFk_muestra_poblacion().getNivelAcademico())
                                        .add("rango_edad_inicio", estudio.getFk_muestra_poblacion().getRangoEdadInicio())
                                        .add("rango_edad_fin", estudio.getFk_muestra_poblacion().getRangoEdadFin())
                                        .add("cantidad_hijos", estudio.getFk_muestra_poblacion().getCantidadHijos())
                                        .add("parroquia", Json.createObjectBuilder()
                                                .add("_id", estudio.getFk_muestra_poblacion().getFk_lugar().get_id())
                                                .add("nombre", estudio.getFk_muestra_poblacion().getFk_lugar().getNombre())
                                                .add("valorSocioEconomico", estudio.getFk_muestra_poblacion().getFk_lugar().getValor_socio_economico())
                                                .add("municipio", Json.createObjectBuilder()
                                                        .add("_id", estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().get_id())
                                                        .add("nombre", estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getNombre())
                                                        .add("estado", Json.createObjectBuilder()
                                                                .add("_id", estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().get_id())
                                                                .add("nombre", estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().getNombre())
                                                                .add("pais", Json.createObjectBuilder()
                                                                        .add("_id", estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().getFk_pais().get_id())
                                                                        .add("nombre", estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().getFk_pais().getNombre()))))))
                                .add("preguntas", preguntaslist)
                                .build();
                        estudiosList.add(agregar);
                    }
                }//final del for
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", estudiosList)
                        .build();
            }//Final IF
            else{
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "Sin estudios")
                        .build();
            }
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();

        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.print(problema);
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}
