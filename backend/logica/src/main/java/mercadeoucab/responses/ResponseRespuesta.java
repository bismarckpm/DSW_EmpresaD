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
        JsonObject resultado = Json.createObjectBuilder()
                .add("_id", dtoRespuesta.get_id())
                .build();
        ResponseUsuario responseUsuario = new ResponseUsuario();
        if (Objects.nonNull( dtoRespuesta.getDtousuario())) {
            JsonObject usuario = responseUsuario.generate(
                    dtoRespuesta.getDtousuario()
            );
            resultado.put("usuario", usuario);
        }
        if (Objects.nonNull(dtoRespuesta.getDtoEncuestaEstudio().getFk_pregunta())) {
            ResponsePregunta responsePregunta = new ResponsePregunta();
            JsonObject pregunta = responsePregunta.generate(
                    dtoRespuesta.getDtoEncuestaEstudio().getFk_pregunta()
            );
            resultado.put("pregunta", pregunta);
        }
        if (Objects.nonNull(dtoRespuesta.getDtoEncuestaEstudio().getFk_estudio())) {
            ResponseEstudio responseEstudio = new ResponseEstudio();
            JsonObject estudio = responseEstudio.generate(
                    dtoRespuesta.getDtoEncuestaEstudio().getFk_estudio()
            );
            resultado.put("estudio", estudio);
        }
        if ( Objects.nonNull( dtoRespuesta.getDtoopcion())){
            ResponseOpcion responseOpcion = new ResponseOpcion();
            JsonObject opcion = responseOpcion.generate(
                    dtoRespuesta.getDtoopcion()
            );
            resultado.put("opcion", opcion);
        }else {
            JsonObject respuesta = Json.createObjectBuilder()
                                        .add("respuesta", dtoRespuesta.getRespuesta())
                                        .build();
            resultado.put("respuesta", respuesta);
        }
        return resultado;
    }
}
