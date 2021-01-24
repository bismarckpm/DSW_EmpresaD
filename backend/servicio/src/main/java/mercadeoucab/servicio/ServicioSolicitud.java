package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.dtos.*;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.MuestraPoblacionMapper;
import mercadeoucab.mappers.PreguntaMapper;
import mercadeoucab.mappers.SolicitudMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseMuestraPoblacion;
import mercadeoucab.responses.ResponsePregunta;
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
import java.util.Objects;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/solicitudes" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSolicitud extends AplicacionBase{

    /**
     * Metodo para consultar una Solicitud dado un identificador
     * @param id Identificador de la Solicitud que se desea consultar
     * @return regresa la Solicitud consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerSolicitud(@PathParam("id") Long id){
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud resul = dao.find( id, Solicitud.class);

            ResponseSolicitud responseSolicitud = new ResponseSolicitud();
            DtoSolicitud dtoSolicitud = SolicitudMapper.mapEntityToDto( resul);
            JsonObject solicitud = responseSolicitud.generate( dtoSolicitud);
            if (Objects.nonNull( dtoSolicitud)){
                resultado = ResponseGeneral.Succes( solicitud);
            }else{
                resultado = ResponseGeneral.NoData();
            }
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar todas las Solicitudes registradas
     * @return regresa la lista de las Solicitudes, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarSolicitud(){
        JsonArrayBuilder solicitudesList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoSolicitud dao = new DaoSolicitud();
            List<Solicitud> solicitudes = dao.findAll(Solicitud.class);
            if ( !solicitudes.isEmpty()) {
                for (Solicitud resul : solicitudes) {
                    if (resul.getActivo() == 1) {
                        ResponseSolicitud responseSolicitud = new ResponseSolicitud();
                        DtoSolicitud dtoSolicitud = SolicitudMapper.mapEntityToDto(resul);
                        JsonObject object = responseSolicitud.generate(dtoSolicitud);
                        solicitudesList.add(object);
                    }
                } //final for
                resultado = ResponseGeneral.Succes( solicitudesList);
            } else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }


    /**
     * Metodo para crear una Solicitud
     * @param dtoSolicitud Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarSolicitud(DtoSolicitud dtoSolicitud){
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = new Solicitud();
            solicitud.setEstado( dtoSolicitud.getEstado() );
            solicitud.setActivo( 1 );
            solicitud.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Usuario usuario = new Usuario(
                    dtoSolicitud.getUsuario().get_id()
            );
            solicitud.setUsuario( usuario);
            for(DtoTipo dtoTipo: dtoSolicitud.getTipos()){
                Tipo tipo = new Tipo(dtoTipo.get_id());
                solicitud.addTipo( tipo);
            }
            for(DtoSubCategoria dtoSubCategoria: dtoSolicitud.getSubCategorias()){
                SubCategoria subCategoria = new SubCategoria(dtoSubCategoria.get_id());
                solicitud.addSubCategoria( subCategoria);
            }
            for(DtoPresentacion dtoPresentacion: dtoSolicitud.getPresentaciones()){
                Presentacion presentacion = new Presentacion(dtoPresentacion.get_id());
                solicitud.addPresentacion( presentacion );
            }

            Solicitud resul = dao.insert( solicitud );
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Solicitud dado un identificador
     * @param id Identificador de la Solicitud que se desea actualizar
     * @param dtoSolicitud Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarSolicitud(@PathParam("id") Long id, DtoSolicitud dtoSolicitud){
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = dao.find( id, Solicitud.class);
            solicitud.setEstado( dtoSolicitud.getEstado());
            solicitud.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Solicitud resul = dao.update( solicitud);
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar una Solicitud dado un identificador
     * @param id Identificador de la Solicitud que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarSolicitud(@PathParam("id") Long id){
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
            resultado = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar todas las Solicitudes segun el estado proporcionado
     * @param estado estado de las Solicitudes que se desea obtener
     * @return regresa la lista de las Solicitudes, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/estado/{estado}")
    public Response listarSolicitudEstado(@PathParam("estado") String estado){
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
                resultado = ResponseGeneral.Succes( solicitudesList);
            }
            else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar las preguntas que se recomiendan en base a
     * los estudios realizados en el sistema
     * @param id Identificador de la Solicitud
     * @return devuelve la lista de preguntas o
     *      un mensaje que no se encontro
     */
    @GET
    @Path("/{id}/preguntas_recomendadas")
    public Response preguntasRecomendadas(@PathParam("id") long id){
        JsonArrayBuilder preguntaslist = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            DaoSolicitud daoSolicitud = new DaoSolicitud();
            List<Estudio> estudios = dao.preguntasSimilares(
                    daoSolicitud.find(id, Solicitud.class)
            );
            if(!(estudios.isEmpty())){
                for (Estudio estudio: estudios){
                    for(Pregunta pregunta: estudio.getPreguntas()){
                        if (pregunta.getActivo() == 1)
                        {
                            String tipo = pregunta.getTipo();
                            JsonObject objeto = null;
                            ResponsePregunta responsePregunta = new ResponsePregunta();
                            DtoPregunta dtoPregunta = PreguntaMapper.mapEntityToDto( pregunta);
                            switch (tipo) {
                                case "abierta":
                                case "boolean":
                                    objeto = responsePregunta.generate( dtoPregunta);
                                    preguntaslist.add(objeto);
                                    break;

                                case "multiple":
                                case "simple":
                                    objeto = responsePregunta.generateWithOptions( dtoPregunta);
                                    preguntaslist.add(objeto);
                                    break;
                                case "rango":
                                    objeto = responsePregunta.generateWithRango( dtoPregunta);
                                    preguntaslist.add(objeto);
                                    break;
                            }//final switch
                        }
                    }
                }
                resultado = ResponseGeneral.Succes( preguntaslist);
            }
            else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
    /**
     * Metodo para listar las Muestras poblacionales que se recomiendan en base a
     * los estudios realizados en el sistema
     * @param id Identificador de la Solicitud
     * @return devuelve la lista de Muestras poblacionales o
     *      un mensaje que no se encontro
     */
    @GET
    @Path("/{id}/poblaciones_recomendadas")
    public Response poblacionesRecomendadas(@PathParam("id") long id){
        JsonArrayBuilder muestrasList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoSolicitud daoSolicitud = new DaoSolicitud();
            DaoEstudio daoEstudio = new DaoEstudio();
            List<MuestraPoblacion> muestras = daoEstudio.poblacionesSimilares(
                    daoSolicitud.find(id, Solicitud.class)
            );
            ResponseMuestraPoblacion responseMuestraPoblacion = new ResponseMuestraPoblacion();
            if(!(muestras.isEmpty())){
                for(MuestraPoblacion muestra: muestras){
                    if (muestra.getActivo() == 1) {
                        DtoMuestraPoblacion dtoMuestraPoblacion = MuestraPoblacionMapper.mapEntitytoDto(
                                muestra
                        );
                        JsonObject agregar = responseMuestraPoblacion.generate( dtoMuestraPoblacion);
                        muestrasList.add(agregar);
                    }
                }
                resultado = ResponseGeneral.Succes( muestrasList);
            }
            else {
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
