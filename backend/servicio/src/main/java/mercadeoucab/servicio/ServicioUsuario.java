package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/usuarios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioUsuario extends AplicacionBase{

    @GET
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoUsuario obtenerUsuario(@PathParam("id") Long id){
        DtoUsuario resultado = new DtoUsuario();
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario resul = dao.find( id, Usuario.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    // @PathParam("id") Long id
    public List<Usuario> listarUsuarios(){
        DaoUsuario dao = new DaoUsuario();
        return dao.findAll( Usuario.class);
    }

    @POST
    @Path("/")
    public DtoUsuario registrarUsuario(DtoUsuario dtoUsuario){
        DtoUsuario resultado = new DtoUsuario();
        try{
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
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoUsuario actualizarUsuario(DtoUsuario dtoUsuario){
        DtoUsuario resultado = new DtoUsuario();
        try{
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = dao.find(dtoUsuario.get_id(), Usuario.class);
            usuario.setNombre( dtoUsuario.getNombre());
            usuario.setApellido( dtoUsuario.getApellido());
            usuario.setEstado( dtoUsuario.getEstado());
            usuario.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Usuario resul = dao.update( usuario);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoUsuario eliminarUsuario( long id){
        DtoUsuario resultado = new DtoUsuario();
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
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }
}
