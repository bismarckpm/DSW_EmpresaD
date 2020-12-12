package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.directorioactivo.DirectorioActivo;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/usuarios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioUsuario extends AplicacionBase{

    @GET
    @Path("/{id}")
    public Response obtenerUsuario(@PathParam("id") Long id){
        JsonObject data;
        JsonObject usuario;
        Response resultado = null;
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario resul = dao.find( id, Usuario.class);
            usuario = Json.createObjectBuilder()
                            .add("_id", resul.get_id())
                            .add( "nombre", resul.getNombre())
                            .add( "apellido", resul.getApellido())
                            .add( "rol", resul.getRol())
                            .add( "estado", resul.getEstado())
                            .add( "correo", resul.getCorreo())
                            .build();
            data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", usuario)
                        .build();
            resultado = Response.status(Response.Status.OK)
                                .entity(data)
                                .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                                .entity(data)
                                .build();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public Response listarUsuarios(){
        JsonObject data;
        JsonArrayBuilder usuarios = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoUsuario dao = new DaoUsuario();
            List<Usuario> usuariosObtenidos = dao.findAll(Usuario.class);

            for (Usuario usuario: usuariosObtenidos){
                JsonObject objeto = Json.createObjectBuilder()
                                        .add("_id", usuario.get_id())
                                        .add( "nombre", usuario.getNombre())
                                        .add( "apellido", usuario.getApellido())
                                        .add( "rol", usuario.getRol())
                                        .add( "estado", usuario.getEstado())
                                        .add( "correo", usuario.getCorreo())
                                        .build();
                usuarios.add( objeto);
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", usuarios)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return  resultado;
    }

    @POST
    @Path("/")
    public Response registrarUsuario(DtoUsuario dtoUsuario){
        JsonObject data;
        Response resultado = null;
        try{
            // Agregacion a la BD
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = new Usuario();
            usuario.setNombre( dtoUsuario.getNombre());
            usuario.setApellido( dtoUsuario.getApellido());
            usuario.setEstado( dtoUsuario.getEstado());
            usuario.setRol( dtoUsuario.getRol());
            usuario.setCorreo( dtoUsuario.getCorreo());
            usuario.setActivo( 1);
            usuario.setCreado_el( new Date(Calendar.getInstance().getTime().getTime()));
            Usuario resul = dao.insert( usuario);
            // Agregar al directorio activo
            DirectorioActivo ldap = new DirectorioActivo( dtoUsuario.getRol());
            DtoDirectorioAUser paraInsertar = new DtoDirectorioAUser(
                    dtoUsuario.getCorreo(),
                    dtoUsuario.getEstado(),
                    dtoUsuario.getPassword()
            );
            ldap.addEntryToLdap( paraInsertar);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Agregado exitosamente")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public Response actualizarUsuario( @PathParam("id") Long id, DtoUsuario dtoUsuario){
        JsonObject data;
        Response resultado = null;
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.find( id, Usuario.class);
            usuario.setNombre( dtoUsuario.getNombre());
            usuario.setApellido( dtoUsuario.getApellido());
            usuario.setEstado( dtoUsuario.getEstado());
            usuario.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Usuario resul = dao.update( usuario);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Agregado exitosamente")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarUsuario( @PathParam("id") Long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.find( id, Usuario.class);
            usuario.setActivo(0);
            usuario.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Usuario resul = dao.update( usuario);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Agregado exitosamente")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }
}
