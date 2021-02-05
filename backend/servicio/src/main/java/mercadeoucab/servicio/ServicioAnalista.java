package mercadeoucab.servicio;

import mercadeoucab.comandos.Usuario.ComandoEstudiosAnalista;
import mercadeoucab.comandos.Usuario.ComandoListarAnalistas;
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
@Path( "/analista" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAnalista extends AplicacionBase{

    /**
     * Metodo para listar todos los usuarios con rol analista de la base de datos
     * @return regresa la lista de los usuarios analistas o
     *      respuesta que no se encontro
     */
    @GET
    @Path("/")
    public Response listarAnalistas(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try {
            validateToken(token);
            ComandoListarAnalistas comandoListarAnalistas = new ComandoListarAnalistas();
            comandoListarAnalistas.execute();
            resultado = comandoListarAnalistas.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para encontrar los estudios asignados a un analista
     * @param id Identificador del usuario analista
     * @return devuelve la lista de estudios de un usuario analista o
     *      un mensaje que no se encontro
     */
    @GET
    @Path("/{id}/estudios")
    public Response estudiosAnalista(@HeaderParam("Authorization") String token,@PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            ComandoEstudiosAnalista comandoEstudiosAnalista = new ComandoEstudiosAnalista();
            comandoEstudiosAnalista.setId( id);
            comandoEstudiosAnalista.execute();
            resultado = comandoEstudiosAnalista.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
