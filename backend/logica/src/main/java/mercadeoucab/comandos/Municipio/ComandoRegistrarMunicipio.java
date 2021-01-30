package mercadeoucab.comandos.Municipio;

import mercadeoucab.accesodatos.DaoMunicipio;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.entidades.Estado;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.MunicipioMapper;
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
public class ComandoRegistrarMunicipio implements ComandoBase {
    private Response result;
    private DtoMunicipio dtoMunicipio;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaMunicipio = FabricaAbstracta.getFactory( Fabricas.MUNICIPIO);
            DaoMunicipio dao = (DaoMunicipio) fabricaMunicipio.generarDao();
            Municipio municipio = MunicipioMapper.mapDtotoEntity( this.dtoMunicipio);
            municipio.setActivo( 1);
            municipio.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Municipio resul = dao.insert(municipio);
            this.result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("ERROR");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setDtoMunicipio(DtoMunicipio dtoMunicipio) {
        this.dtoMunicipio = dtoMunicipio;
    }
}
