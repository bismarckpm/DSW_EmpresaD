package mercadeoucab.responses;

import mercadeoucab.dtos.DtoTipo;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseTipo implements ResponseBase<DtoTipo> {

    /**
     * @param dtoTipo Objeto que se desea convertir en Json
     * @return se retorna el Json dtoTipo
     */
    @Override
    public JsonObject generate(DtoTipo dtoTipo) {
        return Json.createObjectBuilder()
                .add("_id", dtoTipo.get_id())
                .add("nombre", dtoTipo.getNombre())
                .build();
    }
}
