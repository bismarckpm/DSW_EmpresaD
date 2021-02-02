package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.Usuario.ComandoEstudiosAplicablesEncuestado;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.responses.ResponseEstudio;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/encuestados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEncuestado extends AplicacionBase{

    /**
     * Metodo para listar todos los estudios para los cuales aplica un usuario
     * con rol encuestado
     * @param id Identificador del usuario encuestado
     * @return regresa la lista de estudios de un usuario encuestado
     *       ,respuesta que no se encontro o mensaje que ha ocurido un error
     */
    @GET
    @Path("/estudios/{id}")
    public Response estudiosAplicables(@PathParam("id") long id){
        Response resultado = null;
        try {
            verifyParams( id);
            ComandoEstudiosAplicablesEncuestado comandoEstudiosAplicablesEncuestado = new ComandoEstudiosAplicablesEncuestado();
            comandoEstudiosAplicablesEncuestado.setId( id);
            comandoEstudiosAplicablesEncuestado.execute();
            resultado = comandoEstudiosAplicablesEncuestado.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
