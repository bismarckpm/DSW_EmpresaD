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
    @Path("/list")
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
            parroquia.setFk_municipio(new Municipio(dtoParroquia.getMunicipios().getId()));
            Parroquia resul = dao.insert( parroquia );
            resultado.set_id(resul.get_id());
        }
        catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
        }

    @PUT
    @Path("{id}")
    public DtoParroquia consultarParroquia(DtoParroquia dtoParroquia){
        DtoParroquia resultado = new DtoParroquia();
        try {
            DaoParroquia dao = new DaoParroquia();
            Parroquia resul = dao.find(dtoParroquia.getId(),Parroquia.class);
            resultado.set_id(resul.get_id());
        }
        catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("{id}/eliminar")
    public DtoParroquia eliminarParroquia(DtoParroquia dtoParroquia){
        DtoParroquia resultado = new DtoParroquia();
        try {
            DaoParroquia dao = new DaoParroquia();
            Parroquia parroquia = dao.find(dtoParroquia.getId(), Parroquia.class);
            parroquia.setActivo(0);
            parroquia.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Parroquia resul = dao.update( parroquia );
            resultado.set_id(resul.get_id());
        }
        catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("{id}")
    public DtoParroquia actualizarParroquia(DtoParroquia dtoParroquia){
        DtoParroquia resultado = new DtoParroquia();
        try{
            DaoParroquia dao = new DaoParroquia();
            Parroquia parroquia = dao.find(dtoParroquia.getId(), Parroquia.class);
            parroquia.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            parroquia.setNombre(dtoParroquia.getNombre());
            parroquia.setValor_socio_economico(dtoParroquia.getValor_socio_economico());
            parroquia.setFk_municipio(new Municipio(dtoParroquia.getMunicipios().getId()));
            Parroquia resul = dao.update( parroquia );
            resultado.set_id( resul.get_id() );
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
        }
        return resultado;
    }
}

