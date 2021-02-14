package mercadeoucab.responses;

import mercadeoucab.dtos.DtoPais;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponsePais implements ResponseBase<DtoPais> {

    @Override
    public JsonObject generate(DtoPais dtoPais) {
        return Json.createObjectBuilder()
                .add("_id", dtoPais.get_id())
                .add("nombre", dtoPais.getNombre())
                .build();
    }
}
