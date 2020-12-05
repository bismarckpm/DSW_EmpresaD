package mercadeoucab.servicio;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoMunicipio;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Municipio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/municipios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioMunicipio extends AplicacionBase{

    @GET
    @Path("/")
    public List<Municipio> listarMunicipios(){
        DaoMunicipio dao = new DaoMunicipio();
        return dao.findAll(Municipio.class);
    }

    @GET
    @Path("/{id}")
    public DtoMunicipio obtenerMunicipio(DtoMunicipio dtoMunicipio){
        DtoMunicipio resultado = new DtoMunicipio();
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio resul = dao.find(dtoMunicipio.getId(), Municipio.class);
            resultado.set_id(resul.get_id());
        }
        catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
        }

    @POST
    @Path("/")
    public DtoMunicipio registrarMunicipio(DtoMunicipio dtoMunicipio){
        DtoMunicipio resultado = new DtoMunicipio();
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio municipio = new Municipio();
            municipio.setActivo(1);
            municipio.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            municipio.setNombre(dtoMunicipio.getNombre());
            Estado estado = new Estado(dtoMunicipio.getFk_estado().getId());
            municipio.setFk_estado( estado );
            Municipio resul = dao.insert(municipio);
            resultado.set_id(resul.get_id());
        }
        catch (Exception e) {
            String problema = e.getMessage();
            System.out.println(problema);
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public DtoMunicipio actualizarMunicipio(DtoMunicipio dtoMunicipio){
        DtoMunicipio resultado = new DtoMunicipio();
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio municipio = dao.find(dtoMunicipio.getId(), Municipio.class);
            municipio.setNombre(dtoMunicipio.getNombre());
            municipio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Estado estado = new Estado(dtoMunicipio.getId());
            municipio.setFk_estado( estado );
            Municipio resul = dao.update(municipio);
            resultado.set_id(resul.get_id());
        }
        catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public DtoMunicipio eliminarMunicipio(DtoMunicipio dtoMunicipio){
        DtoMunicipio resultado = new DtoMunicipio();
        try {
            DaoMunicipio dao = new DaoMunicipio();
            Municipio municipio = dao.find(dtoMunicipio.getId(), Municipio.class);
            municipio.setActivo(0);
            municipio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Municipio resul = dao.update(municipio);
            resultado.set_id(resul.get_id());
        }
        catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }
}
