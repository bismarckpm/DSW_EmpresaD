package mercadeoucab.comandos.Parroquia;

import mercadeoucab.accesodatos.DaoParroquia;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.ParroquiaMapper;
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
public class ComandoRegistrarParroquia extends ComandoAbstracto implements ComandoBase {
    private Response result;
    private DtoParroquia dtoParroquia;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaParroquia = FabricaAbstracta.getFactory( Fabricas.PARROQUIA);
            DaoParroquia dao = (DaoParroquia) fabricaParroquia.generarDao();
            Parroquia parroquia = ParroquiaMapper.mapDtoToEntity( this.dtoParroquia);
            parroquia.setActivo( 1);
            parroquia.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Parroquia resul = dao.insert( parroquia );
            this.result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("ERROR");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setDtoParroquia(DtoParroquia dtoParroquia) {
        this.dtoParroquia = dtoParroquia;
    }
}
