package mercadeoucab.responses;

import mercadeoucab.dtos.DtoMunicipio;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseMunicipio implements  ResponseBase<DtoMunicipio>{

    @Override
    public JsonObject generate(DtoMunicipio dtoMunicipio) {
        ResponseEstado responseEstado = new ResponseEstado();
        JsonObject estado = responseEstado.generate( dtoMunicipio.getFk_estado());
        return Json.createObjectBuilder()
                .add("_id", dtoMunicipio.get_id())
                .add("nombre", dtoMunicipio.getNombre())
                .add("estado", estado)
                .build();
    }
}
