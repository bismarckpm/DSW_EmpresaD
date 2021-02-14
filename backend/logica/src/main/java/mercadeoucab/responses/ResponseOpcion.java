package mercadeoucab.responses;

import mercadeoucab.dtos.DtoOpcion;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseOpcion implements ResponseBase<DtoOpcion> {

    /**
     * @param dtoOpcion Objeto que se desea convertir en Json
     * @return se retorna el Json
     */
    @Override
    public JsonObject generate(DtoOpcion dtoOpcion) throws Exception {
        return Json.createObjectBuilder()
                .add("_id", dtoOpcion.get_id())
                .add("nombre",dtoOpcion.getNombre_opcion())
                .build();
    }
}
