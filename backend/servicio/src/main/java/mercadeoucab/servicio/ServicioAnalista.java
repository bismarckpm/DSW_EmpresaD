package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseEstudio;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseUsuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.swing.*;
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
        JsonArrayBuilder usuariosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoUsuario dao = new DaoUsuario();
            List<Usuario> usuarios = dao.listarAnalistas();
            ResponseUsuario responseUsuario = new ResponseUsuario();
            if(!(usuarios.isEmpty())){
                for(Usuario usuario: usuarios){
                    if(usuario.getActivo() == 1) {
                        DtoUsuario dtoUsuario = UsuarioMapper.mapEntityToDto( usuario);
                        JsonObject objeto = responseUsuario.generate( dtoUsuario);
                        usuariosList.add(objeto);
                    }
                }
                resultado = ResponseGeneral.Succes( usuariosList);
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
     * Metodo para encontrar los estudios asignados a un analista
     * @param id Identificador del usuario analista
     * @return devuelve la lista de estudios de un usuario analista o
     *      un mensaje que no se encontro
     */
    @GET
    @Path("/{id}/estudios")
    public Response estudiosAnalista(@PathParam("id") long id){
        JsonArrayBuilder estudiosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.estudiosAnalista(new Usuario( id));
            ResponseEstudio responseEstudio = new ResponseEstudio();
            if (!(estudios.isEmpty())){
                for(Estudio estudio: estudios){

                    if(estudio.getActivo() == 1) {
                        DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto( estudio);
                        JsonObject agregar = responseEstudio.generate( dtoEstudio);
                        estudiosList.add(agregar);
                    }
                }//final del for
                resultado = ResponseGeneral.Succes( estudiosList);
            }//Final IF
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
}
