package mercadeoucab.servicio;

import mercadeoucab.comandos.Solicitud.*;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.FabricaComandosAbstractos;
import mercadeoucab.fabricas.fabricasComandoConcretos.FabricaComandosSolicitud;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    private final FabricaComandosSolicitud fabricaComandosSolicitud = (FabricaComandosSolicitud) FabricaComandosAbstractos.getFactory(Comandos.SOLICITUD);
    /**
     * Metodo para consultar una Solicitud dado un identificador
     * @param id Identificador de la Solicitud que se desea consultar
     * @return regresa la Solicitud consultada, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerSolicitud(@HeaderParam("Authorization") String token, @PathParam("id") Long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            ComandoObtenerSolicitud comandoObtenerSolicitud = (ComandoObtenerSolicitud) fabricaComandosSolicitud.comandoConsultar();
            comandoObtenerSolicitud.setId(id);
            comandoObtenerSolicitud.execute();
            resultado = comandoObtenerSolicitud.getResult();
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
    public Response listarSolicitud(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try {
            validateToken(token);
            ComandoListarSolicitud comandoListarSolicitud = (ComandoListarSolicitud) fabricaComandosSolicitud.comandoListar();
            comandoListarSolicitud.execute();
            resultado = comandoListarSolicitud.getResult();
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
    public Response registrarSolicitud(@HeaderParam("Authorization") String token, DtoSolicitud dtoSolicitud){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(dtoSolicitud);
            ComandoRegistrarSolicitud comandoRegistrarSolicitud = (ComandoRegistrarSolicitud) fabricaComandosSolicitud.comandoCrear();
            comandoRegistrarSolicitud.setDtoSolicitud(dtoSolicitud);
            comandoRegistrarSolicitud.execute();
            resultado = comandoRegistrarSolicitud.getResult();
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
    public Response actualizarSolicitud(@HeaderParam("Authorization") String token, @PathParam("id") Long id, DtoSolicitud dtoSolicitud){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            verifyParams(dtoSolicitud);
            ComandoActualizarSolicitud comandoActualizarSolicitud = (ComandoActualizarSolicitud) fabricaComandosSolicitud.comandoModificar();
            comandoActualizarSolicitud.setId(id);
            comandoActualizarSolicitud.setDtoSolicitud(dtoSolicitud);
            comandoActualizarSolicitud.execute();
            resultado = comandoActualizarSolicitud.getResult();
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
    public Response eliminarSolicitud(@HeaderParam("Authorization") String token, @PathParam("id") Long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams(id);
            ComandoEliminarSolicitud comandoEliminarSolicitud = (ComandoEliminarSolicitud) fabricaComandosSolicitud.comandoEliminar();
            comandoEliminarSolicitud.setId(id);
            comandoEliminarSolicitud.execute();
            resultado = comandoEliminarSolicitud.getResult();
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
    public Response listarSolicitudEstado(@HeaderParam("Authorization") String token, @PathParam("estado") String estado){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(estado);
            ComandoListarSolicitudEstado comandoListarSolicitudEstado = (ComandoListarSolicitudEstado) fabricaComandosSolicitud.comandoListarPorEstado();
            comandoListarSolicitudEstado.setEstado(estado);
            comandoListarSolicitudEstado.execute();
            resultado = comandoListarSolicitudEstado.getResult();
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
    public Response preguntasRecomendadas(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            ComandoPreguntasRecomendadas comandoPreguntasRecomendadas = (ComandoPreguntasRecomendadas) fabricaComandosSolicitud.comandoPreguntasRecomendadas();
            comandoPreguntasRecomendadas.setId(id);
            comandoPreguntasRecomendadas.execute();
            resultado = comandoPreguntasRecomendadas.getResult();
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
    public Response poblacionesRecomendadas(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            ComandoPoblacionesRecomendadas comandoPoblacionesRecomendadas = (ComandoPoblacionesRecomendadas) fabricaComandosSolicitud.comandoPoblacionesRecomendadas();
            comandoPoblacionesRecomendadas.setId(id);
            comandoPoblacionesRecomendadas.execute();
            resultado = comandoPoblacionesRecomendadas.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
