package mercadeoucab.responses;

import mercadeoucab.dtos.DtoUsuario;

import javax.json.Json;
import javax.json.JsonObject;

public class ResponseUsuario implements ResponseBase<DtoUsuario> {

    @Override
    public JsonObject generate(DtoUsuario dtoUsuario) {
        return Json.createObjectBuilder()
                .add("_id", dtoUsuario.get_id())
                .add("nombre", dtoUsuario.getNombre())
                .add("apellido", dtoUsuario.getApellido())
                .add("rol", dtoUsuario.getRol())
                .add("estado", dtoUsuario.getEstado())
                .add("correo", dtoUsuario.getCorreo())
                .build();
    }
}
