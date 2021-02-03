package mercadeoucab.comandos.Solicitud;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.PreguntaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponsePregunta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoPreguntasRecomendadas implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder preguntaslist = Json.createArrayBuilder();
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SOLICITUD);
            FabricaAbstracta fabrica1 = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
            FabricaAbstracta fabricaPregunta = FabricaAbstracta.getFactory(Fabricas.PREGUNTA);
            DaoEstudio dao = (DaoEstudio) fabrica1.generarDao();
            DaoSolicitud daoSolicitud = (DaoSolicitud) fabrica.generarDao();
            List<Estudio> estudios = dao.preguntasSimilares(
                    daoSolicitud.find(id, Solicitud.class)
            );
            if(estudios.size() > 0){
                for (Estudio estudio: estudios){
                    for(Pregunta pregunta: estudio.getPreguntas()){
                        if (pregunta.getActivo() == 1)
                        {
                            String tipo = pregunta.getTipo();
                            JsonObject objeto = null;
                            ResponsePregunta responsePregunta = (ResponsePregunta) fabricaPregunta.generarResponse();
                            DtoPregunta dtoPregunta = PreguntaMapper.mapEntityToDto( pregunta);
                            switch (tipo) {
                                case "abierta":
                                case "boolean":
                                    objeto = responsePregunta.generate( dtoPregunta);
                                    preguntaslist.add(objeto);
                                    break;

                                case "multiple":
                                case "simple":
                                    objeto = responsePregunta.generateWithOptions( dtoPregunta);
                                    preguntaslist.add(objeto);
                                    break;
                                case "rango":
                                    objeto = responsePregunta.generateWithRango( dtoPregunta);
                                    preguntaslist.add(objeto);
                                    break;
                            }//final switch
                        }
                    }
                }
                result = ResponseGeneral.Succes( preguntaslist);
            }
            else{
                result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un  al listar las preguntas recomendadas");
        }
    }

    /**
     * Name: getResult
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setId(long id) { this.id = id; }
}
