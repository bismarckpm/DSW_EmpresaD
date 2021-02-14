package mercadeoucab.responses;

import mercadeoucab.dtos.DtoOcupacion;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseOcupacion  implements ResponseBase<DtoOcupacion> {

    @Override
    public JsonObject generate(DtoOcupacion dtoOcupacion) {
        return Json.createObjectBuilder()
                .add("_id", dtoOcupacion.get_id())
                .add("nombre", dtoOcupacion.getNombre())
                .build();
    }
}
