package mercadeoucab.responses;

import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.Objects;

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

    /**
     * Metodo para generar el json reverso de subcategorias
     * @param dtoSubCategoria
     * @return
     */
    public JsonObject generateReverse(DtoSubCategoria dtoSubCategoria){
        FabricaAbstracta fabricaTipo = FabricaAbstracta.getFactory(Fabricas.TIPO);
        ResponseTipo responseTipo = (ResponseTipo) fabricaTipo.generarResponse();
        JsonArrayBuilder tiposList = Json.createArrayBuilder();

        if (Objects.nonNull(dtoSubCategoria.getTipoList()) && dtoSubCategoria.getTipoList().size() > 0){
            for (DtoTipo dtoTipo : dtoSubCategoria.getTipoList())
                tiposList.add(
                        responseTipo.generateReverse(dtoTipo)
                );
        }

        return Json.createObjectBuilder()
                .add("_id", dtoSubCategoria.get_id())
                .add("nombre", dtoSubCategoria.getNombre())
                .add("tipos", tiposList)
                .build();
    }
}
