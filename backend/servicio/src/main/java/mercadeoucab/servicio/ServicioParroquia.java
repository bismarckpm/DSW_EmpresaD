package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoMunicipio;
import mercadeoucab.accesodatos.DaoParroquia;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.entidades.Parroquia;

import javax.lang.model.util.ElementScanner6;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/parroquias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioParroquia {

    @GET
    @Path("/")
    public List<Parroquia> listarParroquias(){
        DaoParroquia dao = new DaoParroquia();
        return dao.findAll(Parroquia.class);
    }

    @POST
    @Path("/")
    public DtoParroquia registrarParroquia(DtoParroquia dtoParroquia){
        DtoParroquia resultado = new DtoParroquia();
        try {
            DaoParroquia dao = new DaoParroquia();
            Parroquia parroquia = new Parroquia();
            parroquia.setNombre(dtoParroquia.getNombre());
            parroquia.setActivo(1);
            parroquia.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            parroquia.setValor_socio_economico(dtoParroquia.getValor_socio_economico());
            parroquia.setFk_municipio(new Municipio(dtoParroquia.getFk_municipio().get_id()));
            Parroquia resul = dao.insert( parroquia );
            resultado.set_id(resul.get_id());
        }
        catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
        }

    @GET
    @Path("/{id}")
    public Parroquia consultarParroquia(@PathParam("id") long id){
        Parroquia resultado = new Parroquia();
        try {
            DaoParroquia dao = new DaoParroquia();
            resultado = dao.find(id ,Parroquia.class);
        }
        catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public Parroquia eliminarParroquia(@PathParam("id") long id){
        Parroquia resultado = new Parroquia();
        try {
            DaoParroquia dao = new DaoParroquia();
            Parroquia parroquia = dao.find(id , Parroquia.class);
            parroquia.setActivo(0);
            parroquia.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.update( parroquia );
        }
        catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public Parroquia actualizarParroquia(@PathParam("id") long id, DtoParroquia dtoParroquia){
        Parroquia resultado = new Parroquia();
        try{
            DaoParroquia dao = new DaoParroquia();
            Parroquia parroquia = dao.find(id , Parroquia.class);
            parroquia.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            parroquia.setNombre(dtoParroquia.getNombre());
            parroquia.setValor_socio_economico(dtoParroquia.getValor_socio_economico());
            resultado = dao.update( parroquia );
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }
}

