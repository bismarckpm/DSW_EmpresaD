package mercadeoucab.responses;

import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseMunicipio implements  ResponseBase<DtoMunicipio>{

    private  final FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTADO);
    @Override
    public JsonObject generate(DtoMunicipio dtoMunicipio) {

        ResponseEstado responseEstado = (ResponseEstado) fabrica.generarResponse();
        JsonObject estado = responseEstado.generate( dtoMunicipio.getFk_estado());
        return Json.createObjectBuilder()
                .add("_id", dtoMunicipio.get_id())
                .add("nombre", dtoMunicipio.getNombre())
                .add("estado", estado)
                .build();
    }
}
