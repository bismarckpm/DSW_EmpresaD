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

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoListarParroquias extends ComandoAbstracto implements ComandoBase {
    private Response result;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder parroquias = Json.createArrayBuilder();
            FabricaAbstracta fabricaParroquia = FabricaAbstracta.getFactory( Fabricas.PARROQUIA);
            DaoParroquia dao = (DaoParroquia) fabricaParroquia.generarDao();
            List<Parroquia> parroquiasObtenidas = dao.findAll( Parroquia.class);
            ResponseParroquia responseParroquia = (ResponseParroquia) fabricaParroquia.generarResponse();
            if (Objects.nonNull(parroquiasObtenidas) && parroquiasObtenidas.size() > 0) {
                for (Parroquia parroquia : parroquiasObtenidas) {
                    if (parroquia.getActivo() != 0) {
                        DtoParroquia dtoParroquia = ParroquiaMapper.mapEntityToDto(parroquia);
                        JsonObject objeto = responseParroquia.generate(dtoParroquia);
                        parroquias.add(objeto);
                    }
                }
                this.result = ResponseGeneral.Succes( parroquias);
            }else {
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
}
