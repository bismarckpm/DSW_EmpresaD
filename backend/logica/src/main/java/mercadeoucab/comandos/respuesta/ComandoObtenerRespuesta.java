package mercadeoucab.comandos.respuesta;

import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.RespuestaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseRespuesta;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ComandoObtenerRespuesta implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            JsonObject respuesta;
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.RESPUESTA);
            DaoRespuesta dao = (DaoRespuesta) fabrica.generarDao();
            Respuesta resul = dao.find( id, Respuesta.class);
            ResponseRespuesta responseRespuesta = (ResponseRespuesta) fabrica.generarResponse();
            DtoRespuesta dtoRespuesta = RespuestaMapper.mapEntityToDto( resul);
            if( resul.getActivo() == 1) {
                respuesta = responseRespuesta.generate( dtoRespuesta);
                result = ResponseGeneral.Succes( respuesta);
            }else{
                result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e) {
            result = ResponseGeneral.Failure("Ha ocurrido un error al consultar la respuesta");
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
