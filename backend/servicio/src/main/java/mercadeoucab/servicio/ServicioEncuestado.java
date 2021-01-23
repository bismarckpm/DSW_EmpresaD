package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.responses.ResponseEstudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/encuestados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEncuestado extends AplicacionBase{

    @GET
    @Path("/estudios/{id}")
    public Response estudiosAplicables(@PathParam("id") long id){
        JsonObject data;
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
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", estudiosList)
                        .build();
            }
            else{
                data = Json.createObjectBuilder()
                        .add("status", 204)
                        .add("message", "Lamentablemente su perfil aun no aplica para ningun estudio")
                        .build();
            }

            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}
