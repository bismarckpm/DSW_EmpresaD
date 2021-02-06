package mercadeoucab.comandos.DatoEncuestado;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.DatoEncuestadoMapper;
import mercadeoucab.responses.ResponseDatoEncuestado;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoConsultarDatoEncuestado extends ComandoAbstracto implements ComandoBase {
    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaDatoEncuestado = FabricaAbstracta.getFactory( Fabricas.DATOENCUESTADO);
            DaoDatoEncuestado dao =  (DaoDatoEncuestado) fabricaDatoEncuestado.generarDao();
            DatoEncuestado resul = dao.find(id, DatoEncuestado.class);
            if ( resul.getActivo() !=0 ){
                ResponseDatoEncuestado responseDatoEncuestado = (ResponseDatoEncuestado) fabricaDatoEncuestado.generarResponse();
                DtoDatoEncuestado dtoDatoEncuestado = DatoEncuestadoMapper.mapEntitytoDto( resul);
                JsonObject objeto = responseDatoEncuestado.generate( dtoDatoEncuestado);
                this.result = ResponseGeneral.Succes( objeto);
            }
            else{
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
