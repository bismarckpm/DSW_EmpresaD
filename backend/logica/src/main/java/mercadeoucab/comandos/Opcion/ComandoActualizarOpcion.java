package mercadeoucab.comandos.Opcion;

import mercadeoucab.accesodatos.DaoOpcion;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoActualizarOpcion implements ComandoBase {

    private Response result;
    private long id;
    private DtoOpcion dtoOpcion;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.OPCION);
            DaoOpcion dao = (DaoOpcion) fabrica.generarDao();
            Opcion opcion = dao.find( id, Opcion.class);
            opcion.setNombre_opcion(dtoOpcion.getNombre_opcion());
            opcion.setModificado_el(new Date(Calendar
                    .getInstance()
                    .getTime()
                    .getTime()));
            dao.update( opcion);
            result = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            result = ResponseGeneral.Failure("Ha ocurrido un error al actualizar la opcion");
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

    public void setDtoOpcion(DtoOpcion dtoOpcion) { this.dtoOpcion = dtoOpcion; }
}