package mercadeoucab.responses;

import mercadeoucab.dtos.DtoBase;

import javax.json.JsonObject;

/**
 * @param <DtoBase> Dto el cual sera convertido en Json
 */
public interface ResponseBase<DtoBase> {

    /**
     * @param object Objeto que se desea convertir en Json
     * @return se retorna el Json
     */
    JsonObject generate(DtoBase object) throws Exception;
}
