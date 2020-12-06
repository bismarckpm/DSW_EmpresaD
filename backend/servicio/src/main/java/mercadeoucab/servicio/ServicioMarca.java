package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoMarca;
import mercadeoucab.dtos.DtoMarca;
import mercadeoucab.entidades.Marca;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/marcas" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioMarca extends AplicacionBase{

    @GET
    @Path("/")
    public List<Marca> listarMarcas(){
        DaoMarca dao = new DaoMarca();
        return dao.findAll(Marca.class);
    }

    @POST
    @Path("/")
    public Marca registrarMarca(DtoMarca dtoMarca){
        Marca resultado = new Marca();
        try {
            DaoMarca dao = new DaoMarca();
            Marca marca = new Marca();
            marca.setNombre(dtoMarca.getNombre());
            marca.setActivo(1);
            marca.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.insert(marca);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return  resultado;
    }

    @PUT
    @Path("/{id}")
    public Marca actualizarMarca(@PathParam("id") long id, DtoMarca dtoMarca){
        Marca resultado = new Marca();
        try {
            DaoMarca dao = new DaoMarca();
            Marca marca = dao.find(id, Marca.class);
            marca.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            marca.setNombre(dtoMarca.getNombre());
            resultado = dao.update(marca);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public  Marca consultarMarca(@PathParam("id") long id){
        Marca resultado = new Marca();
        try {
            DaoMarca dao = new DaoMarca();
            resultado = dao.find(id, Marca.class);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/eliminar/{id}")
    public Marca eliminarMarca(@PathParam("id") long id){
        Marca resultado = new Marca();
        try {
            DaoMarca dao = new DaoMarca();
            Marca marca = dao.find(id, Marca.class);
            marca.setActivo(0);
            marca.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.update(marca);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }
}
