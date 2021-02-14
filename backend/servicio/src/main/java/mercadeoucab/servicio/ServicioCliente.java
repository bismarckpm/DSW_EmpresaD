package mercadeoucab.servicio;

import mercadeoucab.comandos.Estudio.ComandoEstudiosCliente;
import mercadeoucab.comandos.Usuario.ComandoSolicitudesCliente;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.FabricaComandosAbstractos;
import mercadeoucab.fabricas.fabricasComandoConcretos.FabricaComandosUsuario;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/cliente" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCliente extends AplicacionBase{

    private final FabricaComandosUsuario fabricaComandosUsuario = (FabricaComandosUsuario) FabricaComandosAbstractos.getFactory(Comandos.USUARIO);
    /**
     * Metodo para consultar las solicitudes creadar por algun cliente
     * @param id Identificador del cliente
     * @return regresa lista de solicitudes en caso de tener alguna creada,
     *      mensaje que no posee o mensaje de error en caso de ocurrir algun
     *      fallo
     */
    @GET
    @Path("/{id}/solicitudes")
    public Response solicitudesCliente(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            ComandoSolicitudesCliente comandoSolicitudesCliente = (ComandoSolicitudesCliente) fabricaComandosUsuario.comandoSolicitudesCliente();
            comandoSolicitudesCliente.setId( id);
            comandoSolicitudesCliente.execute();
            resultado = comandoSolicitudesCliente.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    @GET
    @Path("/{id}/estudios")
    public Response estudiosCliente(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            ComandoEstudiosCliente comandoEstudiosCliente = (ComandoEstudiosCliente) fabricaComandosUsuario.comandoEstudiosCliente();
            comandoEstudiosCliente.setId(id);
            comandoEstudiosCliente.execute();
            resultado = comandoEstudiosCliente.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
