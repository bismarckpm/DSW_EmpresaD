package mercadeoucab.comandos.Presentacion;

import mercadeoucab.accesodatos.DaoPresentacion;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoEliminarPresentacion extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PRESENTACION);
            DaoPresentacion dao = (DaoPresentacion) fabrica.generarDao();
            Presentacion presentacion = dao.find( id, Presentacion.class);
            presentacion.setActivo( 0);
            presentacion.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            dao.update( presentacion);
            result = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al eliminar la presentacion");
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
