package mercadeoucab.responses;

import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseSubCategoria implements ResponseBase<DtoSubCategoria> {

    private  final FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.CATEGORIA);
    /**
     * @param dtoSubCategoria Objeto que se desea convertir en Json
     * @return se retorna el Json de dtoSubCategoria
     */
    @Override
    public JsonObject generate(DtoSubCategoria dtoSubCategoria) throws Exception {

        ResponseCategoria responseCategoria = (ResponseCategoria) fabrica.generarResponse();
        JsonObject categoria = responseCategoria.generate( dtoSubCategoria.getCategoria());
        return Json.createObjectBuilder()
                .add("_id", dtoSubCategoria.get_id())
                .add("nombre", dtoSubCategoria.getNombre())
                .add("categoria", categoria)
                .build();
    }
}
