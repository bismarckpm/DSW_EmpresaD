package mercadeoucab.comandos.Estudio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoActualizarEstudio implements ComandoBase {

    private Response result;
    private DtoEstudio dtoEstudio;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
            DaoEstudio dao = (DaoEstudio) fabrica.generarDao();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setTipo(dtoEstudio.getTipo());
            estudio.setEncuestasEsperadas(dtoEstudio.getEncuestasEsperadas());
            estudio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            dao.update(estudio);
            result = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un error al actualizar el estudio");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setDtoEstudio(DtoEstudio dtoEstudio) { this.dtoEstudio = dtoEstudio; }

    public void setId(long id) { this.id = id; }
}
