package mercadeoucab.responses;

import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseParroquia implements ResponseBase<DtoParroquia>{

    private  final FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.MUNICIPIO);
    @Override
    public JsonObject generate(DtoParroquia dtoParroquia) {

        ResponseMunicipio responseMunicipio = (ResponseMunicipio) fabrica.generarResponse();
        JsonObject municipio = responseMunicipio.generate( dtoParroquia.getFk_municipio());
        return Json.createObjectBuilder()
                .add("_id", dtoParroquia.get_id())
                .add("nombre",dtoParroquia.getNombre())
                .add("valorSocioEconomico", dtoParroquia.getValor_socio_economico())
                .add("municipio", municipio)
                .build();
    }
}
