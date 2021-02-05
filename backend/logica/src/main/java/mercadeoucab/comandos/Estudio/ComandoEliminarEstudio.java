package mercadeoucab.comandos.Estudio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoEliminarEstudio implements ComandoBase {

    private Response result;
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
            estudio.setActivo(0);
            estudio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            dao.update( estudio );
            result = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un error eliminar el estudio");
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
