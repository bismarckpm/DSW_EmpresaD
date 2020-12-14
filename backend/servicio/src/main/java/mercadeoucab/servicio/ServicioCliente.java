package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.entidades.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/cliente" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioCliente extends AplicacionBase{

    @GET
    @Path("/{id}/solicitudes")
    public Response solicitudesCliente(@PathParam("id") long id){
        JsonObject data;
        JsonArrayBuilder solicitudesList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoSolicitud dao = new DaoSolicitud();
            List<Solicitud> solicitudes = dao.solicitudesCliente(new Usuario(id));

            if(!(solicitudes.isEmpty())){

                for (Solicitud resul: solicitudes){
                    if(resul.getActivo() == 1){

                        JsonArrayBuilder tiposList = Json.createArrayBuilder();
                        for(Tipo tipo: resul.getTipos()){
                            JsonObject objecto = Json.createObjectBuilder()
                                    .add("_id", tipo.get_id())
                                    .add("nombre", tipo.getNombre())
                                    .build();
                            tiposList.add(objecto);
                        }

                        JsonArrayBuilder presentacionlist = Json.createArrayBuilder();
                        for(Presentacion presentacion: resul.getPresentaciones()){
                            JsonObject objecto = Json.createObjectBuilder()
                                    .add("_id", presentacion.get_id())
                                    .add("tipo", presentacion.getTipo())
                                    .add("Cantidad",presentacion.getCantidad())
                                    .build();
                            presentacionlist.add(objecto);
                        }

                        JsonArrayBuilder subCategoriaslist = Json.createArrayBuilder();
                        for(SubCategoria subCategoria: resul.getSubCategorias()){
                            JsonObject objecto = Json.createObjectBuilder()
                                    .add("_id", subCategoria.get_id())
                                    .add("nombre", subCategoria.getNombre())
                                    .add("categoria",Json.createObjectBuilder()
                                            .add("_id", subCategoria.getCategoria().get_id())
                                            .add("nombre", subCategoria.getCategoria().getNombre()))
                                    .build();
                            subCategoriaslist.add(objecto);
                        }
                        JsonObject object = Json.createObjectBuilder()
                                .add("_id", resul.get_id())
                                .add("estado",resul.getEstado())
                                .add("marca",Json.createObjectBuilder()
                                        .add("_id",resul.getMarca().get_id())
                                        .add("nombre",resul.getMarca().getNombre()))
                                .add("tipos", tiposList)
                                .add("presentaciones", presentacionlist)
                                .add("subcategorias", subCategoriaslist)
                                .build();

                        solicitudesList.add(object);
                    }
                } //final for

            }
            else{
                JsonObject agregar = Json.createObjectBuilder()
                        .add("Solicitudes", "No posee solicitudes asociadas")
                        .build();
                solicitudesList.add(agregar);
            }

            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", solicitudesList)
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