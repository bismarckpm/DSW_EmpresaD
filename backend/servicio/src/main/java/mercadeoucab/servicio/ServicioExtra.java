package mercadeoucab.servicio;

import mercadeoucab.comandos.Categoria.ComandoListaReversa;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2021-02-05
 */
@Path("/extra")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioExtra extends AplicacionBase{

    /**
     * Metodo para listar de manera reversa las catgorias
     * @param token
     * @return
     */
    @GET
    @Path("/")
    public Response listarCategoriasReversa(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try
        {
            validateToken(token);
            ComandoListaReversa comandoListaReversa = new ComandoListaReversa();
            comandoListaReversa.execute();
            resultado = comandoListaReversa.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
