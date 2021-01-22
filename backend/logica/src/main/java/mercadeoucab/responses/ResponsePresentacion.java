package mercadeoucab.responses;

import mercadeoucab.dtos.DtoPresentacion;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponsePresentacion implements ResponseBase<DtoPresentacion> {

    /**
     * @param dtoPresentacion Objeto que se desea convertir en Json
     * @return se retorna el Json dtoPresentacion
     */
    @Override
    public JsonObject generate(DtoPresentacion dtoPresentacion) throws Exception {
        return Json.createObjectBuilder()
                .add("_id", dtoPresentacion.get_id())
                .add("tipo", dtoPresentacion.getTipo())
                .add("Cantidad",dtoPresentacion.getCantidad())
                .build();
    }
}
