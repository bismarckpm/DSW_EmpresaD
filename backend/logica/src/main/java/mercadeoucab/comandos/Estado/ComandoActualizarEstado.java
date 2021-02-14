package mercadeoucab.comandos.Estado;

import mercadeoucab.accesodatos.DaoEstado;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.entidades.Estado;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoActualizarEstado extends ComandoAbstracto implements ComandoBase {
    private Response result;
    private long id;
    private DtoEstado dtoEstado;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaEstado = FabricaAbstracta.getFactory( Fabricas.ESTADO);
            DaoEstado dao = (DaoEstado) fabricaEstado.generarDao();
            Estado estado = dao.find( id, Estado.class);
            estado.setModificado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            estado.setNombre( dtoEstado.getNombre());
            dao.update(estado);
            this.result = ResponseGeneral.SuccesMessage();
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("ERROR");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDtoEstado(DtoEstado dtoEstado) {
        this.dtoEstado = dtoEstado;
    }
}
