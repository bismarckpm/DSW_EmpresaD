package mercadeoucab.comandos.MuestraPoblacion;

import mercadeoucab.accesodatos.DaoMuestraPoblacion;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoEliminarMuestraPoblacion extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.MUESTRAPOBLACION);
            DaoMuestraPoblacion dao = (DaoMuestraPoblacion) fabrica.generarDao();
            MuestraPoblacion muestraPoblacion = dao.find(id, MuestraPoblacion.class);
            muestraPoblacion.setActivo(0);
            muestraPoblacion.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            dao.update(muestraPoblacion);
            result = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            result = ResponseGeneral.Failure("Ha ocurrido un error");
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
