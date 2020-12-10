package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoOcupacionEncuestado;
import mercadeoucab.dtos.DtoOcupacionEncuestado;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.entidades.OcupacionEncuestado;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/ocupacionEncuestado" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioOcupacionEncuestado extends AplicacionBase {

    @GET
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoOcupacionEncuestado obtenerOcupacioEncuestado(@PathParam("id") Long id){
        DtoOcupacionEncuestado resultado = new DtoOcupacionEncuestado();
        try{
            DaoOcupacionEncuestado dao = new DaoOcupacionEncuestado();
            OcupacionEncuestado resul = dao.find( id, OcupacionEncuestado.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<OcupacionEncuestado> listarOcupacionEncuestado(){
        DaoOcupacionEncuestado dao = new DaoOcupacionEncuestado();
        return dao.findAll( OcupacionEncuestado.class);
    }

    @POST
    @Path("/")
    public DtoOcupacionEncuestado registrarOcupacionEncuestado(DtoOcupacionEncuestado DTOO){
        DtoOcupacionEncuestado resultado = new DtoOcupacionEncuestado();
        try{
            DaoOcupacionEncuestado daoO = new DaoOcupacionEncuestado();
            OcupacionEncuestado ocupacionE= new OcupacionEncuestado();
            DatoEncuestado DE=new DatoEncuestado(DTOO.getFk_dato_encuestado().get_id());
            ocupacionE.setFk_dato_encuestado(DE);
            Ocupacion O=new Ocupacion(DTOO.getFk_ocupacion().get_id());
            ocupacionE.setFk_ocupacion(O);
            ocupacionE.setActivo(1);
            ocupacionE.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            OcupacionEncuestado resul = daoO.insert( ocupacionE);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoOcupacionEncuestado actualizarOcupacionEncuestado(DtoOcupacionEncuestado DTOO){
        DtoOcupacionEncuestado resultado = new DtoOcupacionEncuestado();
        try{
            DaoOcupacionEncuestado dao = new DaoOcupacionEncuestado();
            OcupacionEncuestado OE = dao.find( DTOO.get_id(), OcupacionEncuestado.class);
            if(DTOO.getFk_dato_encuestado()!=null){
                OE.setFk_dato_encuestado(new DatoEncuestado(DTOO.getFk_dato_encuestado().get_id()));
            }
            if(DTOO.getFk_ocupacion()!=null){
                OE.setFk_ocupacion(new Ocupacion(DTOO.getFk_ocupacion().get_id()));
            }
            if(DTOO.getActivo()!=0){
                OE.setActivo(DTOO.getActivo());
            }
            OE.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            OcupacionEncuestado resul = dao.update( OE);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoOcupacionEncuestado eliminarOcupacionEncuestado(long id){
        DtoOcupacionEncuestado resultado = new DtoOcupacionEncuestado();
        try{
            DaoOcupacionEncuestado dao = new DaoOcupacionEncuestado();
            OcupacionEncuestado ocupacionE = dao.find( id, OcupacionEncuestado.class);
            ocupacionE.setActivo( 0);
            ocupacionE.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            OcupacionEncuestado resul = dao.update( ocupacionE);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }


}
