package mercadeoucab.responses;

import mercadeoucab.dtos.DtoRespuesta;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.Objects;

public class ResponseRespuesta implements ResponseBase<DtoRespuesta> {

    /**
     * @param dtoRespuesta Objeto que se desea convertir en Json
     * @return se retorna el Json de dtoRespuesta
     */
    @Override
    public JsonObject generate(DtoRespuesta dtoRespuesta) throws Exception {
        JsonObject resultado;
        ResponseUsuario responseUsuario = new ResponseUsuario();
        JsonObject usuario = responseUsuario.generate(
                dtoRespuesta.getDtousuario()
        );
        /*
        ResponsePregunta responsePregunta = new ResponsePregunta();
        JsonObject pregunta = responsePregunta.generate(
                dtoRespuesta.getDtoEncuestaEstudio().getFk_pregunta()
        );
        ResponseEstudio responseEstudio = new ResponseEstudio();
        JsonObject estudio = responseEstudio.generate(
                dtoRespuesta.getDtoEncuestaEstudio().getFk_estudio()
        );
         */
        if ( Objects.nonNull( dtoRespuesta.getDtoopcion())){
            ResponseOpcion responseOpcion = new ResponseOpcion();
            JsonObject opcion = responseOpcion.generate(
                    dtoRespuesta.getDtoopcion()
            );
            resultado = Json.createObjectBuilder()
                            .add("_id", dtoRespuesta.get_id())
                            .add("usuario", usuario)
                            .add("opcion", opcion)
                            .build();
        }else {
            resultado = Json.createObjectBuilder()
                            .add("_id", dtoRespuesta.get_id())
                            .add("respuesta", dtoRespuesta.getRespuesta())
                            .add("usuario", usuario)
                            .build();

        }
        return resultado;
    }
}
