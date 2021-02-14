package mercadeoucab.comandos.DatoEncuestado;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoHijo;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.entidades.*;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.DatoEncuestadoMapper;
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
public class ComandoRegistrarDatoEncuestado extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private DtoDatoEncuestado dtoDatoEncuestado;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaDatoEncuestado = FabricaAbstracta.getFactory( Fabricas.DATOENCUESTADO);
            DaoDatoEncuestado dao = (DaoDatoEncuestado) fabricaDatoEncuestado.generarDao();
            DatoEncuestado datoEncuestado = DatoEncuestadoMapper.mapDtotoEntity(dtoDatoEncuestado);
            datoEncuestado.setActivo( 1);
            datoEncuestado.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            DatoEncuestado resul = dao.insert(datoEncuestado);
            this.result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("ERROR");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setDtoDatoEncuestado(DtoDatoEncuestado dtoDatoEncuestado) {
        this.dtoDatoEncuestado = dtoDatoEncuestado;
    }
}
