package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.*;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Arrays;
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
                                                    .add("nombre", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                                    .add("tipo",encuestaEstudio.getFk_pregunta().getTipo()))
                                            .add("usuario",Json.createObjectBuilder()
                                                    .add("_id",encuestaEstudio.getFk_pregunta().getUsuario().get_id())
                                                    .add("nombre",encuestaEstudio.getFk_pregunta().getUsuario().getNombre())
                                                    .add("apellido",encuestaEstudio.getFk_pregunta().getUsuario().getApellido())
                                                    .add("correo",encuestaEstudio.getFk_pregunta().getUsuario().getCorreo())
                                                    .add("rol",encuestaEstudio.getFk_pregunta().getUsuario().getRol()))
                                            .build();
                                    preguntaslist.add(objeto);
                                    break;

                                case "multiple":
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
                                            .add("usuario",Json.createObjectBuilder()
                                                    .add("_id",encuestaEstudio.getFk_pregunta().getUsuario().get_id())
                                                    .add("nombre",encuestaEstudio.getFk_pregunta().getUsuario().getNombre())
                                                    .add("apellido",encuestaEstudio.getFk_pregunta().getUsuario().getApellido())
                                                    .add("correo",encuestaEstudio.getFk_pregunta().getUsuario().getCorreo())
                                                    .add("rol",encuestaEstudio.getFk_pregunta().getUsuario().getRol()))
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
                                            .add("usuario",Json.createObjectBuilder()
                                                    .add("_id",encuestaEstudio.getFk_pregunta().getUsuario().get_id())
                                                    .add("nombre",encuestaEstudio.getFk_pregunta().getUsuario().getNombre())
                                                    .add("apellido",encuestaEstudio.getFk_pregunta().getUsuario().getApellido())
                                                    .add("correo",encuestaEstudio.getFk_pregunta().getUsuario().getCorreo())
                                                    .add("rol",encuestaEstudio.getFk_pregunta().getUsuario().getRol()))
                                            .build();
                                    preguntaslist.add(objeto);
                                    break;
                                case "boolean":
                                    objeto = Json.createObjectBuilder()
                                            .add("_id", encuestaEstudio.get_id())
                                            .add("pregunta",Json.createObjectBuilder()
                                                    .add("_id",encuestaEstudio.getFk_pregunta().get_id())
                                                    .add("nombre", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                                    .add("tipo",encuestaEstudio.getFk_pregunta().getTipo()))
                                            .add("usuario",Json.createObjectBuilder()
                                                    .add("_id",encuestaEstudio.getFk_pregunta().getUsuario().get_id())
                                                    .add("nombre",encuestaEstudio.getFk_pregunta().getUsuario().getNombre())
                                                    .add("apellido",encuestaEstudio.getFk_pregunta().getUsuario().getApellido())
                                                    .add("correo",encuestaEstudio.getFk_pregunta().getUsuario().getCorreo())
                                                    .add("rol",encuestaEstudio.getFk_pregunta().getUsuario().getRol()))
                                            .build();
                                    preguntaslist.add(objeto);
                                    break;
                                case "rango":
                                    objeto = Json.createObjectBuilder()
                                            .add("_id", encuestaEstudio.get_id())
                                            .add("pregunta",Json.createObjectBuilder()
                                                    .add("_id",encuestaEstudio.getFk_pregunta().get_id())
                                                    .add("nombre", encuestaEstudio.getFk_pregunta().getNombrePregunta())
                                                    .add("tipo",encuestaEstudio.getFk_pregunta().getTipo())
                                                    .add("rango",encuestaEstudio.getFk_pregunta().getRango()))
                                            .add("usuario",Json.createObjectBuilder()
                                                    .add("_id",encuestaEstudio.getFk_pregunta().getUsuario().get_id())
                                                    .add("nombre",encuestaEstudio.getFk_pregunta().getUsuario().getNombre())
                                                    .add("apellido",encuestaEstudio.getFk_pregunta().getUsuario().getApellido())
                                                    .add("correo",encuestaEstudio.getFk_pregunta().getUsuario().getCorreo())
                                                    .add("rol",encuestaEstudio.getFk_pregunta().getUsuario().getRol()))
                                            .build();
                                    preguntaslist.add(objeto);
                                    break;
                            }//final switch
                        }//Final for
                    }//IF de la encuesta

                    JsonObject agregar = Json.createObjectBuilder()
                                             .add("_id",estudio.get_id())
                                             .add("estado", estudio.getEstado())
                                             .add("tipo", estudio.getTipo())
                                             .add("encuestas_esperadas", estudio.getEncuestasEsperadas())
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
                                                                                                 .add("valorSocioEconomico", estudio.getFk_muestra_poblacion().getFk_lugar().getValor_socio_economico())
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
            estudio.setEncuestasEsperadas(dtoEstudio.getEncuestasEsperadas());
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
                                                        .add("nombre_opcion",opcion.getNombre_opcion())
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
                                        .add("nombre_opcion",opcion.getNombre_opcion())
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
                    .add("encuestas_esperadas", estudio.getEncuestasEsperadas())
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
                                    .add("valorSocioEconomico", estudio.getFk_muestra_poblacion().getFk_lugar().getValor_socio_economico())
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
    @Path("/{id}/eliminar")
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

    @GET
    @Path("/s")
    public Response listarEstudios2(){
        JsonObject data;
        JsonArrayBuilder estudiosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.findAll(Estudio.class);

            for(Estudio estudio: estudios){

                if(estudio.getActivo() == 1){
                    JsonArrayBuilder tiposList = Json.createArrayBuilder();
                    for(Tipo tipo: estudio.getSolicitud().getTipos()){
                        JsonObject objecto = Json.createObjectBuilder()
                                .add("_id", tipo.get_id())
                                .add("nombre", tipo.getNombre())
                                .build();
                        tiposList.add(objecto);
                    }

                    JsonArrayBuilder presentacionlist = Json.createArrayBuilder();
                    for(Presentacion presentacion: estudio.getSolicitud().getPresentaciones()){
                        JsonObject objecto = Json.createObjectBuilder()
                                .add("_id", presentacion.get_id())
                                .add("tipo", presentacion.getTipo())
                                .add("Cantidad",presentacion.getCantidad())
                                .build();
                        presentacionlist.add(objecto);
                    }

                    JsonArrayBuilder subCategoriaslist = Json.createArrayBuilder();
                    for(SubCategoria subCategoria: estudio.getSolicitud().getSubCategorias()){
                        JsonObject objecto = Json.createObjectBuilder()
                                .add("_id", subCategoria.get_id())
                                .add("nombre", subCategoria.getNombre())
                                .add("categoria",Json.createObjectBuilder()
                                        .add("_id", subCategoria.getCategoria().get_id())
                                        .add("nombre", subCategoria.getCategoria().getNombre()))
                                .build();
                        subCategoriaslist.add(objecto);
                    }
                    JsonObject solicitud;
                    solicitud = Json.createObjectBuilder()
                            .add("_id",estudio.getSolicitud().get_id())
                            .add("estado",estudio.getSolicitud().getEstado())
                            .add("usuario",Json.createObjectBuilder()
                                    .add("_id",estudio.getSolicitud().getUsuario().get_id())
                                    .add("nombre",estudio.getSolicitud().getUsuario().getNombre())
                                    .add("apellido",estudio.getSolicitud().getUsuario().getApellido())
                                    .add("rol",estudio.getSolicitud().getUsuario().getRol())
                                    .add("correo",estudio.getSolicitud().getUsuario().getCorreo()))
                            .add("marca",Json.createObjectBuilder()
                                    .add("_id",estudio.getSolicitud().getMarca().get_id())
                                    .add("nombre",estudio.getSolicitud().getMarca().getNombre()))
                            .add("tipos", tiposList)
                            .add("presentaciones", presentacionlist)
                            .add("subcategorias", subCategoriaslist)
                            .build();
                    JsonObject agregar = Json.createObjectBuilder()
                            .add("_id",estudio.get_id())
                            .add("estado", estudio.getEstado())
                            .add("tipo", estudio.getTipo())
                            .add("encuestas_esperadas", estudio.getEncuestasEsperadas())
                            .add("solicitud", solicitud)
                            .add("analista", Json.createObjectBuilder()
                                    .add("_id", estudio.getFk_usuario().get_id())
                                    .add("nombre",estudio.getFk_usuario().getNombre())
                                    .add("apellido", estudio.getFk_usuario().getApellido())
                                    .add("correo", estudio.getFk_usuario().getCorreo())
                                    .add("rol", estudio.getFk_usuario().getRol()))
                                    .add("estado", estudio.getFk_usuario().getEstado())
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
                                            .add("valorSocioEconomico", estudio.getFk_muestra_poblacion().getFk_lugar().getValor_socio_economico())
                                            .add("municipio",Json.createObjectBuilder()
                                                    .add("_id", estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().get_id())
                                                    .add("nombre", estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getNombre())
                                                    .add("estado",Json.createObjectBuilder()
                                                            .add("_id",estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().get_id())
                                                            .add("nombre",estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().getNombre())
                                                            .add("pais", Json.createObjectBuilder()
                                                                    .add("_id",estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().getFk_pais().get_id())
                                                                    .add("nombre",estudio.getFk_muestra_poblacion().getFk_lugar().getFk_municipio().getFk_estado().getFk_pais().getNombre()))))))
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
            estudio.setEncuestasEsperadas(dtoEstudio.getEncuestasEsperadas());
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

    @GET
    @Path("/{id}/usuarios_respondieron")
    public Response usuariosRespondieronEncuesta(@PathParam("id") long id){
        JsonObject data;
        JsonArrayBuilder usuariosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            DaoEstudio daoEstudio = new DaoEstudio();
            DaoUsuario daoUsuario = new DaoUsuario();
            List<Long> ids = daoRespuesta.usuariosRespondidoEncuesta(daoEstudio.find(id, Estudio.class));
            if(!(ids.isEmpty())){
                for( int i = 0; i < ids.size(); i++){

                    Usuario add = daoUsuario.find(ids.get(i),Usuario.class);
                    JsonObject agregar = Json.createObjectBuilder()
                                             .add("_id", add.get_id())
                                             .add("nombre", add.getNombre())
                                             .add("apellido", add.getApellido())
                                             .add("rol", add.getRol())
                                             .add("estado", add.getEstado())
                                             .add("correo", add.getCorreo())
                                             .build();
                    usuariosList.add(agregar);
                }
            }
            else{
                JsonObject agregar = Json.createObjectBuilder()
                        .add("usuarios", "Actualmente ningun usuario ha respondido a esta encuesta")
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
            System.out.println(problema);
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
    @Path("/{id}/usuarios_aplican")
    public Response usuariosAplicanEncuesta(@PathParam("id") long id){
        JsonObject data;
        JsonArrayBuilder usuariosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            List<Usuario> usuarios = daoEstudio.personasAplicanEstudio(daoEstudio.find(id, Estudio.class));
            if (!(usuarios.isEmpty())){
                for(Usuario usuario: usuarios){
                    JsonObject agregar = Json.createObjectBuilder()
                            .add("_id", usuario.get_id())
                            .add("nombre", usuario.getNombre())
                            .add("apellido", usuario.getApellido())
                            .add("rol", usuario.getRol())
                            .add("estado", usuario.getEstado())
                            .add("correo", usuario.getCorreo())
                            .build();
                    usuariosList.add(agregar);
                }
            }
            else{
                JsonObject agregar = Json.createObjectBuilder()
                        .add("usuarios", "Actualmente ningun usuario califica a esta encuesta")
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
            System.out.println(problema);
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
