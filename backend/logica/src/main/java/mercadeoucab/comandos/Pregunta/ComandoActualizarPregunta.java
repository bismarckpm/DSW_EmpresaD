package mercadeoucab.comandos.Pregunta;

import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoActualizarPregunta extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private long id;
    private DtoPregunta dtoPregunta;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PREGUNTA);
            DaoPregunta dao = (DaoPregunta) fabrica.generarDao();
            Pregunta pregunta = dao.find(id, Pregunta.class);
            pregunta.setNombrePregunta(dtoPregunta.getNombre_pregunta());
            pregunta.setTipo(dtoPregunta.getTipo());
            pregunta.setRango(dtoPregunta.getRango());
            pregunta.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            dao.update( pregunta );
            result = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un error al actuaizar la pregunta");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setDtoPregunta(DtoPregunta dtoPregunta) { this.dtoPregunta = dtoPregunta; }

    public void setId(long id) { this.id = id; }
}
