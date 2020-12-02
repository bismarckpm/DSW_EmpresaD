package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoPais;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/paises" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPais extends AplicacionBase{

    @GET
    @Path("/list")
    public List<Pais> listar_paises(){
        DaoPais dao = new DaoPais();
        return dao.findAll(Pais.class);
    }

    @GET
    @Path("/{id}")
    public DtoPais obtenerPais(DtoPais dtoPais){
        DtoPais resultado = new DtoPais();
        try{
            DaoPais dao = new DaoPais();
            Pais encontrado = dao.find(dtoPais.getId(), Pais.class);
            resultado.set_id(encontrado.get_id());
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @POST
    @Path("/")
    public DtoPais agregarPais(DtoPais dtoPais){
        DtoPais resultado = new DtoPais();
        try{
            DaoPais dao = new DaoPais();
            Pais pais = new Pais();
            pais.setActivo(1);
            pais.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            pais.setNombre(dtoPais.getNombre());
            Pais resul = dao.insert( pais );
            resultado.setId( resul.get_id() );
        }
        catch(Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public DtoPais actualizarPais(DtoPais dtoPais){
        DtoPais resultado = new DtoPais();
        try {
            DaoPais dao = new DaoPais();
            Pais pais = dao.find(dtoPais.getId(), Pais.class);
            pais.setNombre(dtoPais.getNombre());
            pais.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pais resul = dao.update( pais );
            resultado.setId( resul.get_id() );
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public  DtoPais eliminarPais(DtoPais dtoPais){
        DtoPais resultado = new DtoPais();
        try {
            DaoPais dao = new DaoPais();
            Pais pais = dao.find(dtoPais.getId(), Pais.class);
            pais.setActivo(0);
            pais.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pais resul = dao.update( pais );
            resultado.setId( resul.get_id() );
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

}
