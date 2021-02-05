package mercadeoucab.responses;

import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponsePresentacion implements ResponseBase<DtoPresentacion> {

    /**
     * @param dtoPresentacion Objeto que se desea convertir en Json
     * @return se retorna el Json dtoPresentacion
     */
    @Override
    public JsonObject generate(DtoPresentacion dtoPresentacion) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TIPO);
        ResponseTipo responseTipo = (ResponseTipo) fabrica.generarResponse();
        JsonObject tipo = responseTipo.generate(dtoPresentacion.getFk_tipo());
        return Json.createObjectBuilder()
                   .add("_id", dtoPresentacion.get_id())
                   .add("tipo", dtoPresentacion.getTipo())
                   .add("Cantidad",dtoPresentacion.getCantidad())
                   .add("fk_tipo", tipo)
                   .build();
    }
}
