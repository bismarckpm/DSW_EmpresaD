package mercadeoucab.comandos.Solicitud;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoEliminarSolicitud extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SOLICITUD);
            DaoSolicitud dao = (DaoSolicitud) fabrica.generarDao();
            Solicitud solicitud = dao.find( id, Solicitud.class);
            solicitud.setActivo( 0 );
            solicitud.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            dao.update( solicitud);
            result = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            result = ResponseGeneral.Failure("Ha ocurrido un error al eliminar la solicitud");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setId(long id) { this.id = id; }
}
