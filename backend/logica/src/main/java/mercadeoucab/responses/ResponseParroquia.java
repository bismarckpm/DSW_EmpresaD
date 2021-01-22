package mercadeoucab.responses;

import mercadeoucab.dtos.DtoParroquia;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseParroquia implements ResponseBase<DtoParroquia>{

    @Override
    public JsonObject generate(DtoParroquia dtoParroquia) {
        ResponseMunicipio responseMunicipio = new ResponseMunicipio();
        JsonObject municipio = responseMunicipio.generate( dtoParroquia.getFk_municipio());
        return Json.createObjectBuilder()
                .add("_id", dtoParroquia.get_id())
                .add("nombre",dtoParroquia.getNombre())
                .add("valorSocioEconomico", dtoParroquia.getValor_socio_economico())
                .add("municipio", municipio)
                .build();
    }
}
