package mercadeoucab.responses;

import mercadeoucab.dtos.DtoSubCategoria;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseSubCategoria implements ResponseBase<DtoSubCategoria> {

    /**
     * @param dtoSubCategoria Objeto que se desea convertir en Json
     * @return se retorna el Json de dtoSubCategoria
     */
    @Override
    public JsonObject generate(DtoSubCategoria dtoSubCategoria) throws Exception {
        ResponseCategoria responseCategoria = new ResponseCategoria();
        JsonObject categoria = responseCategoria.generate( dtoSubCategoria.getCategoria());
        return Json.createObjectBuilder()
                .add("_id", dtoSubCategoria.get_id())
                .add("nombre", dtoSubCategoria.getNombre())
                .add("categoria", categoria)
                .build();
    }
}
