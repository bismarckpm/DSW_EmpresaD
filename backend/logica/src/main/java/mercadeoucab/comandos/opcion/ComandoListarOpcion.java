package mercadeoucab.comandos.opcion;

import mercadeoucab.accesodatos.DaoOpcion;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.OpcionMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseOpcion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoListarOpcion implements ComandoBase {

    private Response result;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder opcionList = Json.createArrayBuilder();
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.OPCION);
            DaoOpcion dao = (DaoOpcion) fabrica.generarDao();
            List<Opcion> opciones = dao.findAll(Opcion.class);
            if (opciones.size() > 0){
                for (Opcion opcion : opciones){
                    if(opcion.getActivo() == 1) {
                        ResponseOpcion responseOpcion = (ResponseOpcion) fabrica.generarResponse();
                        DtoOpcion dtoOpcion = OpcionMapper.mapEntitytoDto(opcion);
                        JsonObject objeto = responseOpcion.generate(dtoOpcion);
                        opcionList.add(objeto);
                    }
                }
                result = ResponseGeneral.Succes(opcionList);
            }
            else
                result = ResponseGeneral.NoData();

        }
        catch (Exception e){
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al listar las opciones");
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
