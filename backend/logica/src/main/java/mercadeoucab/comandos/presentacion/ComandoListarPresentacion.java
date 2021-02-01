package mercadeoucab.comandos.presentacion;

import mercadeoucab.accesodatos.DaoPresentacion;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.PresentacionMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponsePresentacion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoListarPresentacion implements ComandoBase {

    private Response result;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PRESENTACION);
            JsonArrayBuilder presentacionesList = Json.createArrayBuilder();
            DaoPresentacion dao = (DaoPresentacion) fabrica.generarDao();
            List<Presentacion> presentaciones = dao.findAll(Presentacion.class);
            ResponsePresentacion responsePresentacion = (ResponsePresentacion) fabrica.generarResponse();
            if ( presentaciones.size() > 0 ) {
                for (Presentacion presentacion : presentaciones) {
                    if (presentacion.getActivo() == 1) {
                        DtoPresentacion dtoPresentacion = PresentacionMapper.mapEntityToDto(presentacion);
                        JsonObject objeto = responsePresentacion.generate(dtoPresentacion);
                        presentacionesList.add(objeto);
                    }
                }
                result = ResponseGeneral.Succes( presentacionesList);
            }else{
                result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al listar las presentaciones");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }
}
