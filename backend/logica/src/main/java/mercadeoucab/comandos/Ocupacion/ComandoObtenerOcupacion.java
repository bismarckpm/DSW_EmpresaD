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
import java.util.Objects;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoObtenerOcupacion implements ComandoBase {
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
            JsonObject ocupacion;
            Ocupacion resul = dao.find( id, Ocupacion.class);
            ResponseOcupacion responseOcupacion = (ResponseOcupacion) fabricaOcupacion.generarResponse();
            DtoOcupacion dtoOcupacion = OcupacionMapper.mapEntitytoDto( resul);
            ocupacion = responseOcupacion.generate( dtoOcupacion);
            if ( Objects.nonNull( dtoOcupacion)){
                this.result = ResponseGeneral.Succes( ocupacion);
            }else{
                this.result = ResponseGeneral.NoData();
            }
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
