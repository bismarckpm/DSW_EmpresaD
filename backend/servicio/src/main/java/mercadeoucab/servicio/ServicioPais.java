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
    @Path("/")
    public List<Pais> listar_paises(){
        DaoPais dao = new DaoPais();
        return dao.findAll(Pais.class);
    }

    @GET
    @Path("/{id}")
    public DtoPais obtenerPais(@PathParam("id") long id){
        DtoPais resultado = new DtoPais();
        try{
            DaoPais dao = new DaoPais();
            Pais encontrado = dao.find(id, Pais.class);
            resultado.set_id(encontrado.get_id());
            resultado.setNombre(encontrado.getNombre());
            resultado.setActivo(encontrado.getActivo());
            resultado.setCreado_el(encontrado.getCreado_el());
            resultado.setModificado_el(encontrado.getModificado_el());
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
            resultado.set_id(resul.get_id());
            resultado.setNombre(resul.getNombre());
            resultado.setActivo(resul.getActivo());
            resultado.setCreado_el(resul.getCreado_el());
            resultado.setModificado_el(resul.getModificado_el());
        }
        catch(Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public DtoPais actualizarPais(@PathParam("id") long id,DtoPais dtoPais){
        DtoPais resultado = new DtoPais();
        try {
            DaoPais dao = new DaoPais();
            Pais pais = dao.find(id, Pais.class);
            pais.setNombre(dtoPais.getNombre());
            pais.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pais resul = dao.update( pais );
            resultado.setId( resul.get_id() );
            resultado.set_id(resul.get_id());
            resultado.setNombre(resul.getNombre());
            resultado.setActivo(resul.getActivo());
            resultado.setCreado_el(resul.getCreado_el());
            resultado.setModificado_el(resul.getModificado_el());
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    public  DtoPais eliminarPais(@PathParam("id") long id){
        DtoPais resultado = new DtoPais();
        try {
            DaoPais dao = new DaoPais();
            Pais pais = dao.find(id , Pais.class);
            pais.setActivo(0);
            pais.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Pais resul = dao.update( pais );
            resultado.setId( resul.get_id() );
            resultado.setNombre(resul.getNombre());
            resultado.setActivo(resul.getActivo());
            resultado.setCreado_el(resul.getCreado_el());
            resultado.setModificado_el(resul.getModificado_el());
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

}
