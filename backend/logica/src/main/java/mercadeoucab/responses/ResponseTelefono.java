package mercadeoucab.responses;

import mercadeoucab.dtos.DtoTelefono;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseTelefono implements ResponseBase<DtoTelefono> {

    @Override
    public JsonObject generate(DtoTelefono dtoTelefono) {
        return Json.createObjectBuilder()
                .add("_id", dtoTelefono.get_id())
                .add("telefono", dtoTelefono.getTelefono())
                .build();
    }
}
