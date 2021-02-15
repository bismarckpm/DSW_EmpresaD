package mercadeoucab.comandos.MuestraPoblacion;

import mercadeoucab.accesodatos.DaoMuestraPoblacion;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoListarMuestraPoblacion extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private List<MuestraPoblacion> muestraPoblacionList;


    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.MUESTRAPOBLACION);
            DaoMuestraPoblacion dao = (DaoMuestraPoblacion) fabrica.generarDao();
            muestraPoblacionList = dao.findAll(MuestraPoblacion.class);
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

    public List<MuestraPoblacion> getMuestraPoblacionList() { return muestraPoblacionList; }
}
