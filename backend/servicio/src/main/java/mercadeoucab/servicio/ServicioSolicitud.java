package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/solicitudes" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioSolicitud extends AplicacionBase{

    @GET
    @Path("/{id}")
    public Response obtenerSolicitud(@PathParam("id") Long id){
        JsonObject data;
        JsonObject solicitud;
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud resul = dao.find( id, Solicitud.class);

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
            solicitud = Json.createObjectBuilder()
                            .add("_id", resul.get_id())
                            .add("estado",resul.getEstado())
                            .add("usuario",Json.createObjectBuilder()
                                                    .add("_id",resul.getUsuario().get_id())
                                                    .add("nombre",resul.getUsuario().getNombre())
                                                    .add("apellido", resul.getUsuario().getApellido())
                                                    .add("rol", resul.getUsuario().getRol())
                                                    .add("correo", resul.getUsuario().getCorreo()))
                            .add("marca",Json.createObjectBuilder()
                                                .add("_id",resul.getMarca().get_id())
                                                .add("nombre",resul.getMarca().getNombre()))
                            .add("tipos", tiposList)
                            .add("presentaciones", presentacionlist)
                            .add("subcategorias", subCategoriaslist)
                            .build();
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", solicitud)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("mensaje",problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(data).build();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public Response listarSolicitud(){
        JsonObject data;
        JsonArrayBuilder solicitudesList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoSolicitud dao = new DaoSolicitud();
            List<Solicitud> solicitudes = dao.findAll(Solicitud.class);
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
                            .add("usuario",Json.createObjectBuilder()
                                    .add("_id",resul.getUsuario().get_id())
                                    .add("nombre",resul.getUsuario().getNombre())
                                    .add("apellido", resul.getUsuario().getApellido())
                                    .add("rol", resul.getUsuario().getRol())
                                    .add("correo", resul.getUsuario().getCorreo()))
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
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", solicitudesList)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){

        }
        return resultado;
    }


    @POST
    @Path("/")
    public Response registrarSolicitud(DtoSolicitud dtoSolicitud){
        JsonObject data;
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = new Solicitud();
            solicitud.setEstado( dtoSolicitud.getEstado() );
            solicitud.setActivo( 1 );
            solicitud.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            Usuario usuario = new Usuario(
                    dtoSolicitud.getUsuario().get_id()
            );
            solicitud.setUsuario( usuario);
            Marca marca = new Marca(
                    dtoSolicitud.getMarca().get_id()
            );
            solicitud.setMarca( marca );
            Tipo tipo = new Tipo(dtoSolicitud.getTipo().get_id());
            solicitud.addTipo(tipo);

            SubCategoria subCategoria = new SubCategoria(dtoSolicitud.getSubCategoria().get_id());
            solicitud.addSubCategoria(subCategoria);

            Presentacion presentacion = new Presentacion(dtoSolicitud.getPresentacion().get_id());
            solicitud.addPresentacion( presentacion );

            Solicitud resul = dao.insert( solicitud );
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Solicitud creado con exito")
                    .build();
            resultado = Response.status(Response.Status.OK).entity(data).build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("mensaje",problema)
                    .build();
            resultado = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(data).build();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public Response actualizarSolicitud(@PathParam("id") Long id, DtoSolicitud dtoSolicitud){
        JsonObject data;
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = dao.find( id, Solicitud.class);
            solicitud.setEstado( dtoSolicitud.getEstado());
            solicitud.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Solicitud resul = dao.update( solicitud);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Solicitud actualizada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
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

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public Response eliminarSolicitud(@PathParam("id") Long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoSolicitud dao = new DaoSolicitud();
            Solicitud solicitud = dao.find( id, Solicitud.class);
            solicitud.setActivo( 0);
            solicitud.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Solicitud resul = dao.update( solicitud);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("mensaje", "Solicitud eliminada con exito")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
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
