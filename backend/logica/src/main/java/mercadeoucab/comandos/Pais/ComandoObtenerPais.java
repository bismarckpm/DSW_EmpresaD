package mercadeoucab.comandos.Pais;

import mercadeoucab.accesodatos.DaoPais;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.PaisMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponsePais;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.Objects;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoObtenerPais implements ComandoBase {
    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaPais = FabricaAbstracta.getFactory( Fabricas.PAIS);
            DaoPais dao = (DaoPais)fabricaPais.generarDao();
            Pais resul = dao.find( id, Pais.class);
            if ( Objects.nonNull( resul) && resul.getActivo()==1){
                ResponsePais responsePais = (ResponsePais)fabricaPais.generarResponse();
                DtoPais dtoPais = PaisMapper.mapEntityToDto( resul);
                JsonObject pais = responsePais.generate( dtoPais);
                this.result = ResponseGeneral.Succes( pais);
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
