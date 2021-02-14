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
import mercadeoucab.responses.ResponseParroquia;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoConsultarParroquia extends ComandoAbstracto implements ComandoBase {
    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonObject parroquia;
            FabricaAbstracta fabricaParroquia = FabricaAbstracta.getFactory( Fabricas.PARROQUIA);
            DaoParroquia dao = (DaoParroquia) fabricaParroquia.generarDao();
            Parroquia resul = dao.find( id ,Parroquia.class);
            if ( resul.getActivo()!= 0 ){
                ResponseParroquia responseParroquia = (ResponseParroquia) fabricaParroquia.generarResponse();
                DtoParroquia dtoParroquia = ParroquiaMapper.mapEntityToDto( resul);
                parroquia = responseParroquia.generate( dtoParroquia);
                this.result = ResponseGeneral.Succes( parroquia);
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
