package mercadeoucab.comandos.Estado;

import mercadeoucab.accesodatos.DaoEstado;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.entidades.Estado;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.EstadoMapper;
import mercadeoucab.responses.ResponseEstado;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoConsultarEstado implements ComandoBase {
    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaEstado = FabricaAbstracta.getFactory( Fabricas.ESTADO);
            DaoEstado dao = (DaoEstado) fabricaEstado.generarDao();
            Estado resul = dao.find(id, Estado.class);
            ResponseEstado responseEstado = (ResponseEstado)fabricaEstado.generarResponse();
            if ( resul.getActivo()!= 0) {
                DtoEstado dtoEstado = EstadoMapper.mapentitytoDto( resul);
                JsonObject estado = responseEstado.generate( dtoEstado);
                this.result = ResponseGeneral.Succes( estado);
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
