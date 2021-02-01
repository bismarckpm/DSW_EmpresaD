package mercadeoucab.comandos.Estado;

import mercadeoucab.accesodatos.DaoEstado;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Pais;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.EstadoMapper;
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
public class ComandoAgregarEstado implements ComandoBase {
    private Response result;
    private DtoEstado dtoEstado;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaEstado = FabricaAbstracta.getFactory( Fabricas.ESTADO);
            FabricaAbstracta fabricaPais = FabricaAbstracta.getFactory( Fabricas.PAIS);
            DaoEstado dao = (DaoEstado) fabricaEstado.generarDao();
            Estado estado = EstadoMapper.mapdtotoEntity( this.dtoEstado);
            estado.setActivo( 1);
            estado.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Pais pais = (Pais) fabricaPais.generarEntidad();
            pais.set_id(
                    dtoEstado.getFk_pais().get_id()
            );
            estado.setFk_pais( pais );
            Estado resul = dao.insert( estado );
            this.result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("ERROR");
        }
    }

    public void setDtoEstado(DtoEstado dtoEstado) {
        this.dtoEstado = dtoEstado;
    }

    @Override
    public Response getResult() {
        return this.result;
    }
}
