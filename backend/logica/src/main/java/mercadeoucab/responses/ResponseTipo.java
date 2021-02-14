package mercadeoucab.responses;

import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.Objects;

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

    /**
     * Metodo para generar el json reverso de tipos
     * @param dtoTipo
     * @return
     */
    public JsonObject generateReverse(DtoTipo dtoTipo){
        FabricaAbstracta fabricaPresentacion = FabricaAbstracta.getFactory(Fabricas.PRESENTACION);
        ResponsePresentacion responsePresentacion = (ResponsePresentacion) fabricaPresentacion.generarResponse();
        JsonArrayBuilder presentacionesList = Json.createArrayBuilder();

        if (Objects.nonNull(dtoTipo.getPresentacionList()) && dtoTipo.getPresentacionList().size() > 0)
        {
            for (DtoPresentacion dtoPresentacion : dtoTipo.getPresentacionList())
                presentacionesList.add(responsePresentacion.generateReverse(dtoPresentacion));
        }
        return Json.createObjectBuilder()
                .add("_id", dtoTipo.get_id())
                .add("nombre", dtoTipo.getNombre())
                .add("presentaciones", presentacionesList)
                .build();
    }
}
