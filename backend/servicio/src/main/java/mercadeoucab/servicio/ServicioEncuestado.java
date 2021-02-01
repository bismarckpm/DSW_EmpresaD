package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoUsuario;
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
        JsonArrayBuilder estudiosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoDatoEncuestado daoDatoEncuestado = new DaoDatoEncuestado();
            DatoEncuestado datoEncuestado = daoDatoEncuestado.datoEncuestado(daoUsuario.find(id, Usuario.class));
            System.out.print(datoEncuestado.get_id() +" "
                            + datoEncuestado.getCedula()+" "
                            + datoEncuestado.getGenero()+" "
                            + datoEncuestado.getNive_economico()+" "
                            + datoEncuestado.getPersonasHogar()+" "
                            + datoEncuestado.getNivelAcademico());

            List<Estudio> estudios = dao.estudiosAplicanUsuario(datoEncuestado);
            ResponseEstudio responseEstudio = new ResponseEstudio();
            if(!(estudios.isEmpty())){
                for(Estudio estudio: estudios){
                    if(estudio.getActivo() == 1){
                        DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto( estudio);
                        JsonObject agregar = responseEstudio.generate( dtoEstudio);
                        estudiosList.add(agregar);
                    }
                }
                resultado = ResponseGeneral.Succes( estudiosList);
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
}
