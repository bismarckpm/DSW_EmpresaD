package mercadeoucab.comandos.respuesta;

import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoActualizarRespuesta implements ComandoBase {

    private Response result;
    private long id;
    private DtoRespuesta dtoRespuesta;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.RESPUESTA);
            DaoRespuesta dao = (DaoRespuesta) fabrica.generarDao();
            Respuesta respuesta = dao.find( id, Respuesta.class);

            respuesta.setRespuesta(dtoRespuesta.getRespuesta());
            respuesta.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            dao.update( respuesta );
            result = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            result = ResponseGeneral.Failure("Ha ocurrido un error al actualizar la respuesta");
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

    public void setDtoRespuesta(DtoRespuesta dtoRespuesta) { this.dtoRespuesta = dtoRespuesta; }
}
