package mercadeoucab.responses;

import mercadeoucab.dtos.DtoEstado;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseEstado implements  ResponseBase<DtoEstado> {

    @Override
    public JsonObject generate(DtoEstado dtoEstado) {
        ResponsePais responsePais = new ResponsePais();
        JsonObject pais = responsePais.generate(dtoEstado.getFk_pais() );
        return Json.createObjectBuilder()
                .add("_id", dtoEstado.get_id())
                .add("nombre", dtoEstado.getNombre())
                .add("pais", pais)
                .build();
    }
}
