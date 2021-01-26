package mercadeoucab.responses;

import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoRespuesta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.Objects;

public class ResponseEstudio implements ResponseBase<DtoEstudio> {

    /**
     * @param dtoEstudio Objeto que se desea convertir en Json
     * @return se retorna el Json de dtoEstudio
     */
    @Override
    public JsonObject generate(DtoEstudio dtoEstudio) throws Exception {
        JsonArrayBuilder preguntaslist = Json.createArrayBuilder();
        ResponsePregunta responsePregunta = new ResponsePregunta();
        ResponseRespuesta responseRespuesta = new ResponseRespuesta();
        if(!(dtoEstudio.getEncuestaEstudio().isEmpty())){
            for(DtoEncuestaEstudio encuestaEstudio: dtoEstudio.getEncuestaEstudio()){
                String tipo = encuestaEstudio.getFk_pregunta().getTipo();
                JsonObject objeto = null;
                switch (tipo){
                    case "abierta":
                    case "boolean":
                        JsonArrayBuilder respuestasList = Json.createArrayBuilder();
                        if (Objects.nonNull( encuestaEstudio.getRespuestas()) && !encuestaEstudio.getRespuestas().isEmpty()){
                            for (DtoRespuesta dtoRespuesta: encuestaEstudio.getRespuestas()){
                                JsonObject respuesta = responseRespuesta.generate(dtoRespuesta);
                                respuestasList.add( respuesta);
                            }
                        }
                        objeto = Json.createObjectBuilder()
                                .add("_id", encuestaEstudio.get_id())
                                .add("pregunta", responsePregunta.generate( encuestaEstudio.getFk_pregunta()))
                                .add("respuestas", respuestasList)
                                .build();
                        preguntaslist.add(objeto);
                        break;

                    case "multiple":
                    case "simple":
                        JsonArrayBuilder respuestasListS = Json.createArrayBuilder();
                        if ( Objects.nonNull( encuestaEstudio.getRespuestas()) && !encuestaEstudio.getRespuestas().isEmpty()){
                            for (DtoRespuesta dtoRespuesta: encuestaEstudio.getRespuestas()){
                                JsonObject respuesta = responseRespuesta.generate(dtoRespuesta);
                                respuestasListS.add( respuesta);
                            }
                        }
                        objeto = Json.createObjectBuilder()
                                .add("_id", encuestaEstudio.get_id())
                                .add("pregunta", responsePregunta.generateWithOptions( encuestaEstudio.getFk_pregunta()))
                                .add("respuestas", respuestasListS)
                                .build();
                        preguntaslist.add(objeto);
                        break;
                    case "rango":
                        JsonArrayBuilder respuestasListR = Json.createArrayBuilder();
                        if (Objects.nonNull( encuestaEstudio.getRespuestas()) && !encuestaEstudio.getRespuestas().isEmpty()){
                            for (DtoRespuesta dtoRespuesta: encuestaEstudio.getRespuestas()){
                                JsonObject respuesta = responseRespuesta.generate(dtoRespuesta);
                                respuestasListR.add( respuesta);
                            }
                        }
                        objeto = Json.createObjectBuilder()
                                .add("_id", encuestaEstudio.get_id())
                                .add("pregunta", responsePregunta.generateWithRango( encuestaEstudio.getFk_pregunta()))
                                .add("respuestas", respuestasListR)
                                .build();
                        preguntaslist.add(objeto);
                        break;
                }//final switch
            }//Final for
        }//IF de la encuesta
        ResponseUsuario responseUsuario = new ResponseUsuario();
        JsonObject objetoUsuario = responseUsuario.generate( dtoEstudio.getFk_usuario());
        ResponseMuestraPoblacion responseMuestraPoblacion = new ResponseMuestraPoblacion();
        ResponseSolicitud responseSolicitud = new ResponseSolicitud();
        JsonObject solicitud = responseSolicitud.generate( dtoEstudio.getSolicitud());
        return Json.createObjectBuilder()
                .add("_id",dtoEstudio.get_id())
                .add("estado", dtoEstudio.getEstado())
                .add("tipo", dtoEstudio.getTipo())
                .add("encuestas_esperadas", dtoEstudio.getEncuestasEsperadas())
                .add("solicitud", solicitud)
                .add("analista", objetoUsuario)
                .add("encuesta", preguntaslist)
                .build();
    }
}
