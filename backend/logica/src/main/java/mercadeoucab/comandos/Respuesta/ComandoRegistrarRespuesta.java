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

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoRegistrarRespuesta extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private DtoRespuesta dtoRespuesta;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.RESPUESTA);
            DaoRespuesta dao = (DaoRespuesta) fabrica.generarDao();
            Respuesta respuesta = RespuestaMapper.mapDtoToEntity(dtoRespuesta);
            respuesta.setActivo( 1 );
            respuesta.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Respuesta resul = dao.insert( respuesta );
            result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e) {
            result = ResponseGeneral.Failure("Ha ocurrido un error al registrar la respuesta");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setDtoRespuesta(DtoRespuesta dtoRespuesta) { this.dtoRespuesta = dtoRespuesta; }
}
