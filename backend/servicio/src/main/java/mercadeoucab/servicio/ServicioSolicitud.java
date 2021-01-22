package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.SolicitudMapper;
import mercadeoucab.responses.ResponseSolicitud;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/solicitudes" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSolicitud extends AplicacionBase{

    @GET
    @Path("/{id}")
    public Response obtenerSolicitud(@PathParam("id") Long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud resul = dao.find( id, Solicitud.class);

            ResponseSolicitud responseSolicitud = new ResponseSolicitud();
            DtoSolicitud dtoSolicitud = SolicitudMapper.mapEntityToDto( resul);
            JsonObject solicitud = responseSolicitud.generate( dtoSolicitud);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", solicitud)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message",problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(data).build();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public Response listarSolicitud(){
        JsonObject data;
        JsonArrayBuilder solicitudesList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoSolicitud dao = new DaoSolicitud();
            List<Solicitud> solicitudes = dao.findAll(Solicitud.class);
            for (Solicitud resul: solicitudes){
                if(resul.getActivo() == 1){
                    ResponseSolicitud responseSolicitud = new ResponseSolicitud();
                    DtoSolicitud dtoSolicitud = SolicitudMapper.mapEntityToDto( resul);
                    JsonObject object = responseSolicitud.generate( dtoSolicitud);
                    solicitudesList.add(object);
                }
            } //final for
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", solicitudesList)
                    .build();
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


    @POST
    @Path("/")
    public Response registrarSolicitud(DtoSolicitud dtoSolicitud){
        JsonObject data;
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = new Solicitud();
            solicitud.setEstado( dtoSolicitud.getEstado() );
            solicitud.setActivo( 1 );
            solicitud.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Usuario usuario = new Usuario(
                    dtoSolicitud.getUsuario().get_id()
            );
            solicitud.setUsuario( usuario);
            Marca marca = new Marca(
                    dtoSolicitud.getMarca().get_id()
            );
            solicitud.setMarca( marca );

            for(DtoTipo dtoTipo: dtoSolicitud.getTipos()){
                Tipo tipo = new Tipo(dtoTipo.get_id());
                solicitud.addTipo(tipo);
            }
            for(DtoSubCategoria dtoSubCategoria: dtoSolicitud.getSubCategorias()){
                SubCategoria subCategoria = new SubCategoria(dtoSubCategoria.get_id());
                solicitud.addSubCategoria(subCategoria);
            }

            for(DtoPresentacion dtoPresentacion: dtoSolicitud.getPresentaciones()){
                Presentacion presentacion = new Presentacion(dtoPresentacion.get_id());
                solicitud.addPresentacion( presentacion );
            }


            Solicitud resul = dao.insert( solicitud );
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Solicitud creado con exito")
                    .build();
            resultado = Response.status(Response.Status.OK).entity(data).build();
        }catch (Exception e) {
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

    @PUT
    @Path("/{id}")
    public Response actualizarSolicitud(@PathParam("id") Long id, DtoSolicitud dtoSolicitud){
        JsonObject data;
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = dao.find( id, Solicitud.class);
            solicitud.setEstado( dtoSolicitud.getEstado());
            solicitud.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Solicitud resul = dao.update( solicitud);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Solicitud actualizada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
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

    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarSolicitud(@PathParam("id") Long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = dao.find( id, Solicitud.class);
            solicitud.setActivo( 0);
            solicitud.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Solicitud resul = dao.update( solicitud);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Solicitud eliminada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
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
    @Path("/estado/{estado}")
    public Response listarSolicitudEstado(@PathParam("estado") String estado){
        JsonObject data;
        JsonArrayBuilder solicitudesList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoSolicitud dao = new DaoSolicitud();
            List<Solicitud> solicitudes = dao.solicitudesSegunEstado(estado);
            if(solicitudes.size() > 0){

                for (Solicitud resul: solicitudes){
                        if(resul.getActivo() == 1) {
                            ResponseSolicitud responseSolicitud = new ResponseSolicitud();
                            DtoSolicitud dtoSolicitud = SolicitudMapper.mapEntityToDto( resul);
                            JsonObject object = responseSolicitud.generate( dtoSolicitud);
                            solicitudesList.add(object);
                        }
                } //final for
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", solicitudesList)
                        .build();
            }
            else{
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "Actualmente no existen solicitudes con ese estado")
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
    @Path("/{id}/preguntas_recomendadas")
    public Response preguntasRecomendadas(@PathParam("id") long id){
        JsonObject data;
        JsonArrayBuilder preguntaslist = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            DaoSolicitud daoSolicitud = new DaoSolicitud();
            List<Estudio> estudios = dao.preguntasSimilares(daoSolicitud.find(id, Solicitud.class));
            if(!(estudios.isEmpty())){
                for (Estudio estudio: estudios){
                    for(Pregunta pregunta: estudio.getPreguntas()){
                        if (pregunta.getActivo() == 1)
                        {
                            String tipo = pregunta.getTipo();
                            JsonObject objeto = null;
                            JsonArrayBuilder opcionesList = null;

                            switch (tipo) {
                                case "abierta":
                                    objeto = Json.createObjectBuilder()
                                            .add("pregunta", Json.createObjectBuilder()
                                                    .add("_id", pregunta.get_id())
                                                    .add("nombre", pregunta.getNombrePregunta())
                                                    .add("tipo", pregunta.getTipo()))
                                            .build();
                                    preguntaslist.add(objeto);
                                    break;

                                case "multiple":
                                    opcionesList = Json.createArrayBuilder();
                                    for (Opcion opcion : pregunta.getOpciones()) {
                                        JsonObject option = Json.createObjectBuilder()
                                                .add("_id", opcion.get_id())
                                                .add("nombre_opcion", opcion.getNombre_opcion())
                                                .build();
                                        opcionesList.add(option);
                                    }
                                    objeto = Json.createObjectBuilder()
                                            .add("pregunta", Json.createObjectBuilder()
                                                    .add("_id", pregunta.get_id())
                                                    .add("nombre", pregunta.getNombrePregunta())
                                                    .add("tipo", pregunta.getTipo())
                                                    .add("opciones", opcionesList))
                                            .build();
                                    preguntaslist.add(objeto);
                                    break;
                                case "simple":
                                    opcionesList = Json.createArrayBuilder();
                                    for (Opcion opcion : pregunta.getOpciones()) {
                                        JsonObject option = Json.createObjectBuilder()
                                                .add("_id", opcion.get_id())
                                                .add("nombre_nombre", opcion.getNombre_opcion())
                                                .build();
                                        opcionesList.add(option);
                                    }
                                    objeto = Json.createObjectBuilder()
                                            .add("pregunta", Json.createObjectBuilder()
                                                    .add("_id", pregunta.get_id())
                                                    .add("nombre", pregunta.getNombrePregunta())
                                                    .add("tipo", pregunta.getTipo())
                                                    .add("opciones", opcionesList))
                                            .build();
                                    preguntaslist.add(objeto);
                                    break;
                                case "boolean":
                                    objeto = Json.createObjectBuilder()
                                            .add("pregunta", Json.createObjectBuilder()
                                                    .add("_id", pregunta.get_id())
                                                    .add("nombre", pregunta.getNombrePregunta())
                                                    .add("tipo", pregunta.getTipo()))
                                            .build();
                                    preguntaslist.add(objeto);
                                    break;
                                case "rango":
                                    objeto = Json.createObjectBuilder()
                                            .add("pregunta", Json.createObjectBuilder()
                                                    .add("_id", pregunta.get_id())
                                                    .add("nombre", pregunta.getNombrePregunta())
                                                    .add("tipo", pregunta.getTipo())
                                                    .add("rango", pregunta.getRango()))
                                            .build();
                                    preguntaslist.add(objeto);
                                    break;
                            }//final switch
                        }
                    }

                }
            }
            else{
                JsonObject agregar = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "Actualmente no hay preguntas que se puedan recomendar para este estudio")
                        .build();
                preguntaslist.add(agregar);
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", preguntaslist)
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
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
    
    @GET
    @Path("/{id}/poblaciones_recomendadas")
    public Response poblacionesRecomendadas(@PathParam("id") long id){
        JsonObject data;
        JsonArrayBuilder muestrasList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoSolicitud daoSolicitud = new DaoSolicitud();
            DaoEstudio daoEstudio = new DaoEstudio();
            List<MuestraPoblacion> muestras = daoEstudio.poblacionesSimilares(daoSolicitud.find(id, Solicitud.class));
            if(!(muestras.isEmpty())){
                for(MuestraPoblacion muestra: muestras){
                    if (muestra.getActivo() == 1) {
                        JsonObject objetoOcupacion = Json.createObjectBuilder()
                                .add("_id", muestra.getFk_ocupacion().get_id())
                                .add("nombre", muestra.getFk_ocupacion().getNombre())
                                .build();
                        JsonObject agregar = Json.createObjectBuilder()
                                .add("_id", muestra.get_id())
                                .add("genero", muestra.getGenero())
                                .add("nivel_economico", muestra.getNivelEconomico())
                                .add("nivel_academico", muestra.getNivelAcademico())
                                .add("rango_edad_inicio", muestra.getRangoEdadInicio())
                                .add("rango_edad_fin", muestra.getRangoEdadFin())
                                .add("ocupacion", objetoOcupacion)
                                .add("cantidad_hijos", muestra.getCantidadHijos())
                                .add("parroquia", Json.createObjectBuilder()
                                        .add("_id", muestra.getFk_lugar().get_id())
                                        .add("nombre", muestra.getFk_lugar().getNombre())
                                        .add("valorSocioEconomico", muestra.getFk_lugar().getValor_socio_economico())
                                        .add("municipio", Json.createObjectBuilder()
                                                .add("_id", muestra.getFk_lugar().getFk_municipio().get_id())
                                                .add("nombre", muestra.getFk_lugar().getFk_municipio().getNombre())
                                                .add("estado", Json.createObjectBuilder()
                                                        .add("_id", muestra.getFk_lugar().getFk_municipio().getFk_estado().get_id())
                                                        .add("nombre", muestra.getFk_lugar().getFk_municipio().getFk_estado().getNombre())
                                                        .add("pais", Json.createObjectBuilder()
                                                                .add("_id", muestra.getFk_lugar().getFk_municipio().getFk_estado().getFk_pais().get_id())
                                                                .add("nombre", muestra.getFk_lugar().getFk_municipio().getFk_estado().getFk_pais().getNombre())))))
                                .build();
                        muestrasList.add(agregar);
                    }
                }
            }
            else {
                JsonObject agregar = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "Actualmente no ninguna muestra que pueda ser recomendada a este estudio")
                        .build();
                muestrasList.add(agregar);
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", muestrasList)
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
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}
