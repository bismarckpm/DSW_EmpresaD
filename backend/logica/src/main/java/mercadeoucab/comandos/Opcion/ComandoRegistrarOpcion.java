package mercadeoucab.comandos.Opcion;

import mercadeoucab.accesodatos.DaoOpcion;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.mappers.OpcionMapper;
import mercadeoucab.mappers.PreguntaMapper;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoRegistrarOpcion implements ComandoBase {

    private Response result;
    private DtoOpcion dtoOpcion;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            DaoOpcion dao = new DaoOpcion();
            Opcion opcion = OpcionMapper.mapDtotoEntity(dtoOpcion);
            opcion.setFk_pregunta(PreguntaMapper.mapDtoToEntity(dtoOpcion.get_Dtopregunta()));
            opcion.setActivo(1);
            opcion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Opcion resul = dao.insert( opcion);
            result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e) {
            result = ResponseGeneral.Failure("Ha ocurrido un error al registrar la opcion");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setDtoOpcion(DtoOpcion dtoOpcion) { this.dtoOpcion = dtoOpcion; }
}
