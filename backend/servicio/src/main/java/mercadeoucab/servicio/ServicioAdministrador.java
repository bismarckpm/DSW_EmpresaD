package mercadeoucab.servicio;


import mercadeoucab.comandos.Usuario.ComandoPreguntasAdministrador;
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
@Path( "/administrador" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioAdministrador extends AplicacionBase{

    /**
     * Metodo para listar todas las preguntas que un usuario administrador ha
     * creado
     * @param id Identificador del usuario administrador
     * @return regresa la lista de las preguntas de un usuario
     *  administrador o respuesta que no se encontro
     */
    @GET
    @Path("/{id}/preguntas")
    public Response preguntasAdministrador(@HeaderParam("Authorization") String token,@PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            ComandoPreguntasAdministrador comandoPreguntasAdministrador = new ComandoPreguntasAdministrador();
            comandoPreguntasAdministrador.setId( id);
            comandoPreguntasAdministrador.execute();
            resultado = comandoPreguntasAdministrador.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
