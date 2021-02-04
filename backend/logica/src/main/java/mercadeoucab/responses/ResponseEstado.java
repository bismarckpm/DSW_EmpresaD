package mercadeoucab.responses;

import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseEstado implements  ResponseBase<DtoEstado> {

    private  final FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PAIS);

    @Override
    public JsonObject generate(DtoEstado dtoEstado) {
        ResponsePais responsePais = (ResponsePais) fabrica.generarResponse();
        JsonObject pais = responsePais.generate(dtoEstado.getFk_pais() );
        return Json.createObjectBuilder()
                .add("_id", dtoEstado.get_id())
                .add("nombre", dtoEstado.getNombre())
                .add("pais", pais)
                .build();
    }
}
