package mercadeoucab.comandos.MuestraPoblacion;

import mercadeoucab.accesodatos.DaoMuestraPoblacion;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.ws.rs.core.Response;

public class ComandoConsultarMuestraPoblacion extends ComandoAbstracto implements ComandoBase {

    private long id;
    private MuestraPoblacion muestraPoblacion;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.MUESTRAPOBLACION);
        DaoMuestraPoblacion dao = (DaoMuestraPoblacion) fabrica.generarDao();
        muestraPoblacion = dao.find(id, MuestraPoblacion.class);
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() {
        return null;
    }

    public void setId(long id) { this.id = id; }

    public MuestraPoblacion getMuestraPoblacion() { return muestraPoblacion; }
}
