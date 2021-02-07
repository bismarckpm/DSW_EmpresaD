package mercadeoucab.comandos.Parroquia;

import mercadeoucab.accesodatos.DaoParroquia;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Parroquia;
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
public class  ComandoActualizarParroquia extends ComandoAbstracto implements ComandoBase {
    private Response result;
    private long id;
    private DtoParroquia dtoParroquia;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaParroquia = FabricaAbstracta.getFactory( Fabricas.PARROQUIA);
            DaoParroquia dao = (DaoParroquia) fabricaParroquia.generarDao();
            Parroquia parroquia = dao.find(id , Parroquia.class);
            parroquia.setModificado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            parroquia.setNombre( dtoParroquia.getNombre());
            parroquia.setValor_socio_economico( dtoParroquia.getValor_socio_economico());
            dao.update( parroquia );
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

    public void setDtoParroquia(DtoParroquia dtoParroquia) {
        this.dtoParroquia = dtoParroquia;
    }
}
