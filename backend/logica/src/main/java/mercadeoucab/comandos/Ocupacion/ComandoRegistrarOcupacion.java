package mercadeoucab.comandos.Ocupacion;

import mercadeoucab.accesodatos.DaoOcupacion;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.OcupacionMapper;
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
public class ComandoRegistrarOcupacion implements ComandoBase {
    private Response result;
    private DtoOcupacion dtoOcupacion;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaOcupacion = FabricaAbstracta.getFactory( Fabricas.OCUPACION);
            DaoOcupacion dao = (DaoOcupacion)fabricaOcupacion.generarDao();
            Ocupacion ocupacion= OcupacionMapper.mapDtotoEntity( this.dtoOcupacion);
            ocupacion.setActivo(1);
            ocupacion.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Ocupacion resul = dao.insert( ocupacion);
            this.result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("ERROR");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setDtoOcupacion(DtoOcupacion dtoOcupacion) {
        this.dtoOcupacion = dtoOcupacion;
    }
}
