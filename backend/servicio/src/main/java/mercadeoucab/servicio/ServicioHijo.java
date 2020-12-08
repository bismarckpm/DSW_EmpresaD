package mercadeoucab.servicio;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoHijo;
import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoHijo;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Hijo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/hijos" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioHijo {

    @GET
    @Path("/")
    public List<Hijo> listarHijos(){
        DaoHijo dao = new DaoHijo();
        return dao.findAll(Hijo.class);
    }

    @POST
    @Path("/")
    public Hijo registrarHijo(DtoHijo dtoHijo){
        Hijo resultado = new Hijo();
        try {
            DaoHijo dao = new DaoHijo();
            Hijo hijo = new Hijo();
            hijo.setEdad(dtoHijo.getEdad());
            hijo.setGenero(dtoHijo.getGenero());
            hijo.setActivo(1);
            hijo.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            DatoEncuestado datoEncuestado = new DatoEncuestado(dtoHijo.getFk_dato_encuestado().get_id());
            hijo.setFk_dato_encuestado(datoEncuestado);
            resultado = dao.insert(hijo);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public Hijo consultarHijo(@PathParam("id") long id){
        DaoHijo dao = new DaoHijo();
        return dao.find(id, Hijo.class);
    }

    @PUT
    @Path("/eliminar/{id}")
    public Hijo eliminarHijo(@PathParam("id") long id){
        Hijo resultado = new Hijo();
        try {
            DaoHijo dao = new DaoHijo();
            Hijo hijo = dao.find(id, Hijo.class);
            hijo.setActivo(0);
            hijo.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.update(hijo);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public Hijo actuaizarHijo(@PathParam("id") long id, DtoHijo dtoHijo){
        Hijo resultado = new Hijo();
        try {
            DaoHijo dao = new DaoHijo();
            Hijo hijo = dao.find(id, Hijo.class);
            hijo.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            hijo.setGenero(dtoHijo.getGenero());
            hijo.setEdad(dtoHijo.getEdad());
            resultado = dao.update( hijo );
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }
}
