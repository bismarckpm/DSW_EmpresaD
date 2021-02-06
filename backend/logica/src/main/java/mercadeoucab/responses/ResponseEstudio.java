package mercadeoucab.responses;

import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.Objects;

public class ResponseEstudio implements ResponseBase<DtoEstudio> {

    private  final FabricaAbstracta fabricaPregunta = FabricaAbstracta.getFactory(Fabricas.PREGUNTA);
    private  final FabricaAbstracta fabricaRespuesta = FabricaAbstracta.getFactory(Fabricas.RESPUESTA);
    /**
     * @param dtoEstudio Objeto que se desea convertir en Json
     * @return se retorna el Json de dtoEstudio
     */
    @Override
    public JsonObject generate(DtoEstudio dtoEstudio) throws Exception {
        JsonArrayBuilder preguntaslist = Json.createArrayBuilder();

        ResponsePregunta responsePregunta = (ResponsePregunta) fabricaPregunta.generarResponse();
        ResponseRespuesta responseRespuesta = (ResponseRespuesta) fabricaRespuesta.generarResponse();

        if(Objects.nonNull(dtoEstudio.getEncuestaEstudio()) && dtoEstudio.getEncuestaEstudio().size() > 0){
            for(DtoEncuestaEstudio encuestaEstudio: dtoEstudio.getEncuestaEstudio()){
                String tipo = encuestaEstudio.getFk_pregunta().getTipo();
                JsonObject objeto;
                switch (tipo){
                    case "abierta":
                    case "boolean":
                        JsonArrayBuilder respuestasList = Json.createArrayBuilder();
                        if (Objects.nonNull(encuestaEstudio.getRespuestas()) && encuestaEstudio.getRespuestas().size() > 0){
                            for (DtoRespuesta dtoRespuesta: encuestaEstudio.getRespuestas()){
                                JsonObject respuesta = responseRespuesta.generate(dtoRespuesta);
                                respuestasList.add( respuesta);
                            }
                        }
                        objeto = Json.createObjectBuilder()
                                .add("_id", encuestaEstudio.get_id())
                                .add("pregunta", responsePregunta.generate( encuestaEstudio.getFk_pregunta() ))
                                .add("respuestas", respuestasList)
                                .build();
                        preguntaslist.add(objeto);
                        break;

                    case "multiple":
                    case "simple":
                        JsonArrayBuilder respuestasListS = Json.createArrayBuilder();
                        if ( Objects.nonNull(encuestaEstudio.getRespuestas()) && encuestaEstudio.getRespuestas().size() > 0){
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
                        if (Objects.nonNull(encuestaEstudio.getRespuestas()) && encuestaEstudio.getRespuestas().size() > 0){
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
        FabricaAbstracta fabricaUsuario = FabricaAbstracta.getFactory(Fabricas.USUARIO);
        FabricaAbstracta fabricaSolicitud = FabricaAbstracta.getFactory(Fabricas.SOLICITUD);
        ResponseUsuario responseUsuario = (ResponseUsuario) fabricaUsuario.generarResponse();
        JsonObject objetoUsuario = responseUsuario.generate( dtoEstudio.getFk_usuario());
        ResponseSolicitud responseSolicitud = (ResponseSolicitud) fabricaSolicitud.generarResponse();
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
