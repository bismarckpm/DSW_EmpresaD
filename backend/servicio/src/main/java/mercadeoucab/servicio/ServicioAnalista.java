package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.usuario.ComandoEstudiosAnalista;
import mercadeoucab.comandos.usuario.ComandoListarAnalistas;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseEstudio;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseUsuario;

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
    public Response listarAnalistas(){
        Response resultado = null;
        try {
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
    public Response estudiosAnalista(@PathParam("id") long id){
        Response resultado = null;
        try {
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
