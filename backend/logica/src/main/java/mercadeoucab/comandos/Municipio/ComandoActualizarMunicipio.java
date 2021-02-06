package mercadeoucab.comandos.Municipio;

import mercadeoucab.accesodatos.DaoMunicipio;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.entidades.Municipio;
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
public class ComandoActualizarMunicipio extends ComandoAbstracto implements ComandoBase {
    private Response result;
    private long id;
    private DtoMunicipio dtoMunicipio;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaMunicipio = FabricaAbstracta.getFactory( Fabricas.MUNICIPIO);
            DaoMunicipio dao = (DaoMunicipio) fabricaMunicipio.generarDao();
            Municipio municipio = dao.find( id, Municipio.class);
            municipio.setNombre( dtoMunicipio.getNombre());
            municipio.setModificado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            dao.update(municipio);
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

    public void setDtoMunicipio(DtoMunicipio dtoMunicipio) {
        this.dtoMunicipio = dtoMunicipio;
    }
}
