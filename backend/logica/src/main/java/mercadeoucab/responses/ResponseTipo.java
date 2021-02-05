package mercadeoucab.responses;

import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseTipo implements ResponseBase<DtoTipo> {

    /**
     * @param dtoTipo Objeto que se desea convertir en Json
     * @return se retorna el Json dtoTipo
     */
    @Override
    public JsonObject generate(DtoTipo dtoTipo) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
        ResponseSubCategoria responseSubCategoria = (ResponseSubCategoria) fabrica.generarResponse();
        JsonObject subCategoria = responseSubCategoria.generate(dtoTipo.getSubCategoria());
        return Json.createObjectBuilder()
                .add("_id", dtoTipo.get_id())
                .add("nombre", dtoTipo.getNombre())
                .add("subCategoria", subCategoria)
                .build();
    }
}
