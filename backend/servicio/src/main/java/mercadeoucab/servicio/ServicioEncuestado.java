package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.entidades.*;

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
            if(!(estudios.isEmpty())){
                for(Estudio estudio: estudios){

                    JsonArrayBuilder tiposList = Json.createArrayBuilder();
                    for(Tipo tipo: estudio.getSolicitud().getTipos()){
                        JsonObject objecto = Json.createObjectBuilder()
                                .add("_id", tipo.get_id())
                                .add("nombre", tipo.getNombre())
                                .build();
                        tiposList.add(objecto);
                    }

                    JsonArrayBuilder presentacionlist = Json.createArrayBuilder();
                    for(Presentacion presentacion: estudio.getSolicitud().getPresentaciones()){
                        JsonObject objecto = Json.createObjectBuilder()
                                .add("_id", presentacion.get_id())
                                .add("tipo", presentacion.getTipo())
                                .add("Cantidad",presentacion.getCantidad())
                                .build();
                        presentacionlist.add(objecto);
                    }

                    JsonArrayBuilder subCategoriaslist = Json.createArrayBuilder();
                    for(SubCategoria subCategoria: estudio.getSolicitud().getSubCategorias()){
                        JsonObject objecto = Json.createObjectBuilder()
                                .add("_id", subCategoria.get_id())
                                .add("nombre", subCategoria.getNombre())
                                .add("categoria",Json.createObjectBuilder()
                                        .add("_id", subCategoria.getCategoria().get_id())
                                        .add("nombre", subCategoria.getCategoria().getNombre()))
                                .build();
                        subCategoriaslist.add(objecto);
                    }

                    JsonObject agregar = Json.createObjectBuilder()
                            .add("_id",estudio.get_id())
                            .add("estado", estudio.getEstado())
                            .add("tipo", estudio.getTipo())
                            .add("encuestas_esperadas", estudio.getEscuestasEsperadas())
                            .add("solicitud",Json.createObjectBuilder()
                                    .add("_id", estudio.getSolicitud().get_id())
                                    .add("estado",estudio.getSolicitud().getEstado())
                                    .add("marca",Json.createObjectBuilder()
                                                        .add("_id",estudio.getSolicitud().getMarca().get_id())
                                                        .add("nombre",estudio.getSolicitud().getMarca().getNombre()))
                                    .add("tipo", tiposList)
                                    .add("presentacion", presentacionlist)
                                    .add("subcategorias",subCategoriaslist))
                            .build();
                    estudiosList.add(agregar);
                }
            }
            else{
                JsonObject agregar = Json.createObjectBuilder()
                        .add("Estudios", "Lamentablemente su perfil aun no aplica para ningun estudio")
                        .build();
                estudiosList.add(agregar);
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", estudiosList)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("problema", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}
