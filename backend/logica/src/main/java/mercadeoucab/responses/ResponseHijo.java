package mercadeoucab.responses;

import mercadeoucab.dtos.DtoHijo;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.LocalDate;
import java.time.Period;

public class ResponseHijo implements ResponseBase<DtoHijo> {

    @Override
    public JsonObject generate(DtoHijo dtoHijo) {
        LocalDate ahora = LocalDate.now();
        Period periodoHijo = Period.between( dtoHijo.getEdad().toLocalDate(),ahora);
        return Json.createObjectBuilder()
                .add("_id", dtoHijo.get_id())
                .add("genero", dtoHijo.getGenero())
                .add("edad", periodoHijo.getYears())
                .build();
    }
}
