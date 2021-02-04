package mercadeoucab.comandos.Tipo;

import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoActualizarTipo implements ComandoBase {
    private Response result;
    private long id;
    private DtoTipo dtoTipo;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TIPO);
            DaoTipo dao = (DaoTipo) fabrica.generarDao();
            Tipo tipo = dao.find( id, Tipo.class);
            tipo.setNombre( dtoTipo.getNombre());
            tipo.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            dao.update( tipo );
            result = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e) {
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al actualizar el tipo");
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

    public void setDtoTipo(DtoTipo dtoTipo) { this.dtoTipo = dtoTipo; }
}
