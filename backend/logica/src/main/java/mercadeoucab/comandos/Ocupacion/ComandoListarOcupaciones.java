package mercadeoucab.comandos.Ocupacion;

import mercadeoucab.accesodatos.DaoOcupacion;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.OcupacionMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseOcupacion;

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
public class ComandoListarOcupaciones extends ComandoAbstracto implements ComandoBase {
    private Response result;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder ocupacionesList = Json.createArrayBuilder();
            FabricaAbstracta fabricaOcupacion = FabricaAbstracta.getFactory( Fabricas.OCUPACION);
            DaoOcupacion dao = (DaoOcupacion)fabricaOcupacion.generarDao();
            List<Ocupacion> ocupaciones = dao.findAll(Ocupacion.class);
            if (Objects.nonNull(ocupaciones) && ocupaciones.size() > 0) {
                for (Ocupacion ocupacion : ocupaciones) {
                    if (ocupacion.getActivo() == 1) {
                        ResponseOcupacion responseOcupacion = (ResponseOcupacion)fabricaOcupacion.generarResponse();
                        DtoOcupacion dtoOcupacion = OcupacionMapper.mapEntitytoDto(ocupacion);
                        JsonObject objeto = responseOcupacion.generate(dtoOcupacion);
                        ocupacionesList.add(objeto);
                    }
                }
                this.result = ResponseGeneral.Succes( ocupacionesList);
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
}
