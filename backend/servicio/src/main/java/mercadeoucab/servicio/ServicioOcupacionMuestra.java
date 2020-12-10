package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoOcupacionMuestra;
import mercadeoucab.dtos.DtoOcupacionMuestra;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.entidades.OcupacionMuestra;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/ocupacionMuestra" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioOcupacionMuestra extends AplicacionBase{

    @GET
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoOcupacionMuestra obtenerOcupacionMuestra(@PathParam("id") Long id){
        DtoOcupacionMuestra resultado = new DtoOcupacionMuestra();
        try{
            DaoOcupacionMuestra dao = new DaoOcupacionMuestra();
            OcupacionMuestra resul = dao.find( id, OcupacionMuestra.class);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/")
    public List<OcupacionMuestra> listarOcupacionMuestra(){
        DaoOcupacionMuestra dao = new DaoOcupacionMuestra();
        return dao.findAll( OcupacionMuestra.class);
    }

    @POST
    @Path("/")
    public DtoOcupacionMuestra registrarOcupacionMuestra(DtoOcupacionMuestra DTOM){
        DtoOcupacionMuestra resultado = new DtoOcupacionMuestra();
        try{
            DaoOcupacionMuestra daoO = new DaoOcupacionMuestra();
            OcupacionMuestra OM= new OcupacionMuestra();
            MuestraPoblacion MP=new MuestraPoblacion(DTOM.getFk_muestra_poblacion().get_id());
            OM.setFk_muestra_poblacion(MP);
            Ocupacion O=new Ocupacion(DTOM.getFk_ocupacion().get_id());
            OM.setFk_ocupacion(O);
            OM.setActivo(1);
            OM.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            OcupacionMuestra resul = daoO.insert( OM);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    // @PathParam("id") Long id
    public DtoOcupacionMuestra actualizarOcupacionMuestra(DtoOcupacionMuestra DTOM){
        DtoOcupacionMuestra resultado = new DtoOcupacionMuestra();
        try{
            DaoOcupacionMuestra dao = new DaoOcupacionMuestra();
            OcupacionMuestra OM = dao.find( DTOM.get_id(), OcupacionMuestra.class);
            if(DTOM.getFk_muestra_poblacion()!=null){
                OM.setFk_muestra_poblacion(new MuestraPoblacion(DTOM.getFk_muestra_poblacion().get_id()));
            }
            if(DTOM.getFk_ocupacion()!=null){
                OM.setFk_ocupacion(new Ocupacion(DTOM.getFk_ocupacion().get_id()));
            }
            if(DTOM.getActivo()!=0){
                OM.setActivo(DTOM.getActivo());
            }
            OM.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            OcupacionMuestra resul = dao.update( OM);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}/eliminar")
    // @PathParam("id") Long id
    public DtoOcupacionMuestra eliminarOcupacionMuestra(long id){
        DtoOcupacionMuestra resultado = new DtoOcupacionMuestra();
        try{
            DaoOcupacionMuestra dao = new DaoOcupacionMuestra();
            OcupacionMuestra OM= dao.find( id, OcupacionMuestra.class);
            OM.setActivo( 0);
            OM.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            OcupacionMuestra resul = dao.update( OM);
            resultado.set_id( resul.get_id());
        }catch (Exception e) {
            String problema = e.getMessage();
        }
        return resultado;
    }


}
