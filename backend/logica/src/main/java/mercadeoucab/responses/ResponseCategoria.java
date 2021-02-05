package mercadeoucab.responses;

import mercadeoucab.dtos.DtoCategoria;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseCategoria implements ResponseBase<DtoCategoria>{

    /**
     * Metodo para generar el Json de la clase DtoDatoEncuestado
     * @param dtoCategoria Objeto Dato encuestado que se desea convertir
     * @return regresa un json del dtoCategoria
     */
    @Override
    public JsonObject generate(DtoCategoria dtoCategoria) {
        return Json.createObjectBuilder()
                .add("_id", dtoCategoria.get_id())
                .add("nombre", dtoCategoria.getNombre())
                .build();
    }
}
