package mercadeoucab.comandos.DatoEncuestado;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.entidades.DatoEncuestado;
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
public class ComandoEliminarDatoEncuestado extends ComandoAbstracto implements ComandoBase {
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
            DatoEncuestado datoEncuestado = dao.find( id, DatoEncuestado.class);
            datoEncuestado.setActivo( 0);
            datoEncuestado.setModificado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            dao.update(datoEncuestado);
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
