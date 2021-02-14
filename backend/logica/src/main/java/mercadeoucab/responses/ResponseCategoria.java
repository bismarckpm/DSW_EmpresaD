package mercadeoucab.responses;

import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.Objects;

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

    /**
     * Metodo para generar la lista de categorias disponibles en el sistema de manera reversa
     * @param dtoCategoria
     * @return json de categorias
     */
    public JsonObject generateReverse(DtoCategoria dtoCategoria){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
        ResponseSubCategoria responseSubCategoria = (ResponseSubCategoria) fabrica.generarResponse();
        JsonArrayBuilder subCategoriasList = Json.createArrayBuilder();

        if (Objects.nonNull(dtoCategoria.getSubCategorias()) && dtoCategoria.getSubCategorias().size() > 0){
            for (DtoSubCategoria dtoSubCategoria : dtoCategoria.getSubCategorias()){
                subCategoriasList.add(responseSubCategoria.generateReverse(dtoSubCategoria));
            }
        }

        return Json.createObjectBuilder()
                .add("_id", dtoCategoria.get_id())
                .add("nombre", dtoCategoria.getNombre())
                .add("subcategorias", subCategoriasList)
                .build();
    }
}
