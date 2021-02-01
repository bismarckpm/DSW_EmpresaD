package mercadeoucab.comandos.Pais;

import mercadeoucab.accesodatos.DaoPais;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;
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
public class ComandoActualizarPais implements ComandoBase {
    private Response result;
    private long id;
    private DtoPais dtoPais;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaPais = FabricaAbstracta.getFactory( Fabricas.PAIS);
            DaoPais dao = (DaoPais)fabricaPais.generarDao();
            Pais pais = dao.find(id, Pais.class);
            pais.setNombre( dtoPais.getNombre());
            pais.setModificado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Pais resul = dao.update( pais );
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

    public void setDtoPais(DtoPais dtoPais) {
        this.dtoPais = dtoPais;
    }
}
