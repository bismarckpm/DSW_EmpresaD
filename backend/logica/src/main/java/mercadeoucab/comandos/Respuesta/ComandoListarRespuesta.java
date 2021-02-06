package mercadeoucab.comandos.Respuesta;

import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.RespuestaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseRespuesta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoListarRespuesta extends ComandoAbstracto implements ComandoBase {

    private Response result;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder respuestasList = Json.createArrayBuilder();
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.RESPUESTA);
            DaoRespuesta dao = (DaoRespuesta) fabrica.generarDao();
            List<Respuesta> respuestas = dao.findAll(Respuesta.class);
            ResponseRespuesta responseRespuesta = (ResponseRespuesta) fabrica.generarResponse();
            for(Respuesta resul: respuestas){
                if( resul.getActivo() == 1){
                    DtoRespuesta dtoRespuesta = RespuestaMapper.mapEntityToDto( resul);
                    JsonObject objeto = responseRespuesta.generate( dtoRespuesta);
                    respuestasList.add(objeto);
                }
            }
            if (respuestas.isEmpty()){
                result = ResponseGeneral.NoData();
            }else{
                result = ResponseGeneral.Succes( respuestasList);
            }
        }
        catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un error al listar las respuestas");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }
}
