package mercadeoucab.responses;

import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoCategoria;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseCategoria implements ResponseBase<DtoCategoria>{

    @Override
    public JsonObject generate(DtoCategoria dtoCategoria) {
        return Json.createObjectBuilder()
                .add("_id", dtoCategoria.get_id())
                .add("nombre", dtoCategoria.getNombre())
                .build();
    }
}
