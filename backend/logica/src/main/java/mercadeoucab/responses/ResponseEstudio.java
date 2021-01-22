package mercadeoucab.responses;

import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Opcion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class ResponseEstudio implements ResponseBase<DtoEstudio> {

    /**
     * @param dtoEstudio Objeto que se desea convertir en Json
     * @return se retorna el Json de dtoEstudio
     */
    @Override
    public JsonObject generate(DtoEstudio dtoEstudio) throws Exception {
        JsonArrayBuilder preguntaslist = Json.createArrayBuilder();
        ResponsePregunta responsePregunta = new ResponsePregunta();
        if(!(dtoEstudio.getEncuestaEstudio().isEmpty())){
            for(DtoEncuestaEstudio encuestaEstudio: dtoEstudio.getEncuestaEstudio()){
                String tipo = encuestaEstudio.getFk_pregunta().getTipo();
                JsonObject objeto = null;
                switch (tipo){
                    case "abierta":
                    case "boolean":
                        objeto = Json.createObjectBuilder()
                                .add("_id", encuestaEstudio.get_id())
                                .add("pregunta", responsePregunta.generate( encuestaEstudio.getFk_pregunta()))
                                .build();
                        preguntaslist.add(objeto);
                        break;

                    case "multiple":
                    case "simple":
                        objeto = Json.createObjectBuilder()
                                .add("_id", encuestaEstudio.get_id())
                                .add("pregunta", responsePregunta.generateWithOptions( encuestaEstudio.getFk_pregunta()))
                                .build();
                        preguntaslist.add(objeto);
                        break;
                    case "rango":
                        objeto = Json.createObjectBuilder()
                                .add("_id", encuestaEstudio.get_id())
                                .add("pregunta", responsePregunta.generateWithRango( encuestaEstudio.getFk_pregunta()))
                                .build();
                        preguntaslist.add(objeto);
                        break;
                }//final switch
            }//Final for
        }//IF de la encuesta
        ResponseUsuario responseUsuario = new ResponseUsuario();
        JsonObject objetoUsuario = responseUsuario.generate( dtoEstudio.getFk_usuario());
        ResponseMuestraPoblacion responseMuestraPoblacion = new ResponseMuestraPoblacion();
        JsonObject muestraPoblacion = responseMuestraPoblacion.generate( dtoEstudio.getFk_muestra_poblacion());
        ResponseSolicitud responseSolicitud = new ResponseSolicitud();
        JsonObject solicitud = responseSolicitud.generate( dtoEstudio.getSolicitud());
        return Json.createObjectBuilder()
                .add("_id",dtoEstudio.get_id())
                .add("estado", dtoEstudio.getEstado())
                .add("tipo", dtoEstudio.getTipo())
                .add("encuestas_esperadas", dtoEstudio.getEncuestasEsperadas())
                .add("solicitud", solicitud)
                .add("analista", objetoUsuario)
                .add("muestra_poblacion", muestraPoblacion)
                .add("encuesta", preguntaslist)
                .build();
    }
}
