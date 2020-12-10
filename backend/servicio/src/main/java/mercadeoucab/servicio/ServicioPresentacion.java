package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoPresentacion;
import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.entidades.Presentacion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/presentacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPresentacion extends AplicacionBase{

    @GET
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoPresentacion obtenerPresentacion(@PathParam("id") Long id){
        DtoPresentacion resultado = new DtoPresentacion();
        try{
            DaoPresentacion daoP = new DaoPresentacion();
            Presentacion resul = daoP.find( id, Presentacion.class);
            resultado.set_id( resul.get_id());
            resultado.setTipo(resul.getTipo());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<Presentacion> listarPresentacion(){
        DaoPresentacion dao = new DaoPresentacion();
        return dao.findAll( Presentacion.class);
    }

    @POST
    @Path("/")
    public DtoPresentacion registrarPresentacion(DtoPresentacion DTOP){
        DtoPresentacion resultado = new DtoPresentacion();
        try{
            DaoPresentacion daoP = new DaoPresentacion();
            Presentacion presentacion = new Presentacion();
            presentacion.setCantidad(DTOP.getCantidad());
            presentacion.setTipo(DTOP.getTipo());
            presentacion.setActivo(DTOP.getActivo());
            presentacion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Presentacion resul = daoP.insert( presentacion);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoPresentacion actualizarPresentacion(DtoPresentacion DTOP){
        DtoPresentacion resultado = new DtoPresentacion();
        try{
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion presentacion = dao.find( DTOP.get_id(), Presentacion.class);
            if(DTOP.getCantidad()!=null){
                presentacion.setCantidad(DTOP.getCantidad());
            }
            if(DTOP.getTipo()!=null){
                presentacion.setTipo(DTOP.getTipo());
            }
            if(DTOP.getActivo()!=0){
                presentacion.setTipo(DTOP.getTipo());
            }

            presentacion.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            Presentacion resul = dao.update( presentacion);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoPresentacion eliminarPresentacion(long id){
        DtoPresentacion resultado = new DtoPresentacion();
        try{
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion presentacion = dao.find( id, Presentacion.class);
            presentacion.setActivo( 0);
            presentacion.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Presentacion resul = dao.update( presentacion);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

}
