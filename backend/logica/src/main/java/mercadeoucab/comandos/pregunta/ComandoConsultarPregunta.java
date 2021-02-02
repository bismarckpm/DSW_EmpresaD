package mercadeoucab.comandos.pregunta;

import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.PreguntaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponsePregunta;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ComandoConsultarPregunta implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PREGUNTA);
            JsonObject pregunta;
            DaoPregunta dao = (DaoPregunta) fabrica.generarDao();
            Pregunta resul = dao.find(id, Pregunta.class);
            if ( resul.getActivo() != 0) {
                ResponsePregunta responsePregunta = (ResponsePregunta) fabrica.generarResponse();
                DtoPregunta dtoPregunta = PreguntaMapper.mapEntityToDto( resul);
                String tipo = resul.getTipo();
                switch (tipo) {
                    case "abierta":
                    case "boolean":
                        pregunta = responsePregunta.generate( dtoPregunta);
                        break;
                    case "multiple":
                    case "simple":
                        pregunta = responsePregunta.generateWithOptions( dtoPregunta);
                        break;
                    case "rango":
                        pregunta = responsePregunta.generateWithRango( dtoPregunta);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + tipo);
                }//final switch
                result = ResponseGeneral.Succes( pregunta);
            }
            else{
                result = ResponseGeneral.NoData();
            }
        }catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un error al consultar la pregunta");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setId(long id) { this.id = id; }
}
