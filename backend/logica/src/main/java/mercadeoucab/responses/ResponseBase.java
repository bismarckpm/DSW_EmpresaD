package mercadeoucab.responses;

import mercadeoucab.dtos.DtoBase;

import javax.json.JsonObject;

public interface ResponseBase<DtoBase> {

    JsonObject generate(DtoBase object);
}
