package mercadeoucab.comandos.estudio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoAgregarEstudio implements ComandoBase {

    private Response result;
    private DtoEstudio dtoEstudio;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try
        {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
            DaoEstudio dao = (DaoEstudio) fabrica.generarDao();
            Estudio estudio = EstudioMapper.mapDtotoEntity(dtoEstudio);
            estudio.setActivo(1);
            estudio.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Estudio resul = dao.insert(estudio);
            result = ResponseGeneral.SuccesCreate( resul.get_id());
        }
        catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un error al agregar el estudio");
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
}
