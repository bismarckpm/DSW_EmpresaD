package mercadeoucab.comandos.Ocupacion;

import mercadeoucab.accesodatos.DaoOcupacion;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.OcupacionMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseOcupacion;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoEliminarOcupacion implements ComandoBase {
    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaOcupacion = FabricaAbstracta.getFactory( Fabricas.OCUPACION);
            DaoOcupacion dao = (DaoOcupacion)fabricaOcupacion.generarDao();
            Ocupacion ocupacion = dao.find( id, Ocupacion.class);
            ocupacion.setActivo( 0);
            ocupacion.setModificado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Ocupacion resul = dao.update( ocupacion);
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
}
