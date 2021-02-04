package mercadeoucab.responses;

import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseMuestraPoblacion implements ResponseBase<DtoMuestraPoblacion> {

    private  final FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PARROQUIA);
    /**
     * @param dtoMuestraPoblacion Objeto que se desea convertir en Json
     * @return se retorna el Json de dtoMuestraPoblacion
     */
    @Override
    public JsonObject generate(DtoMuestraPoblacion dtoMuestraPoblacion) {

        ResponseParroquia responseParroquia = (ResponseParroquia) fabrica.generarResponse();
        JsonObject parroquia = responseParroquia.generate( dtoMuestraPoblacion.getFk_lugar());
        return Json.createObjectBuilder()
                .add("_id",dtoMuestraPoblacion.get_id())
                .add("genero",dtoMuestraPoblacion.getGenero())
                .add("nivel_economico", dtoMuestraPoblacion.getNivelEconomico())
                .add("nivel_academico", dtoMuestraPoblacion.getNivelAcademico())
                .add("rango_edad_inicio", dtoMuestraPoblacion.getRangoEdadInicio().toString())
                .add("rango_edad_fin", dtoMuestraPoblacion.getRangoEdadFin().toString())
                .add("cantidad_hijos", dtoMuestraPoblacion.getCantidadHijos())
                .add("parroquia", parroquia)
                .build();
    }
}
