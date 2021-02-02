package mercadeoucab.comandos.Presentacion;

import mercadeoucab.accesodatos.DaoPresentacion;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.PresentacionMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponsePresentacion;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ComandoObtenerPresentacion implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            JsonObject presentacion;
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PRESENTACION);
            DaoPresentacion dao = (DaoPresentacion) fabrica.generarDao();
            Presentacion resul = dao.find( id, Presentacion.class);
            ResponsePresentacion responsePresentacion = (ResponsePresentacion) fabrica.generarResponse();
            DtoPresentacion dtoPresentacion = PresentacionMapper.mapEntityToDto( resul);
            presentacion = responsePresentacion.generate( dtoPresentacion);
            if ( resul.getActivo() == 1){
                result = ResponseGeneral.Succes( presentacion);
            }else{
                result = ResponseGeneral.NoData();
            }
        }catch (Exception e) {
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al consultar la presentacion");
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
