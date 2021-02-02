package mercadeoucab.comandos.Pregunta;

import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.PreguntaMapper;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoRegistrarPregunta implements ComandoBase {

    private Response result;
    private DtoPregunta dtoPregunta;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PREGUNTA);
            DaoPregunta dao = (DaoPregunta) fabrica.generarDao();
            Pregunta pregunta = PreguntaMapper.mapDtoToEntity(dtoPregunta);
            pregunta.setActivo(1);
            pregunta.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            if(pregunta.getOpciones().size() > 0){
                for (Opcion opcion : pregunta.getOpciones()){
                    opcion.setActivo(1);
                    opcion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
                }
            }
            Pregunta resul = dao.insert(pregunta);
            result = ResponseGeneral.SuccesCreate( resul.get_id());
        }
        catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un error al registrar la pregunta");
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
}
