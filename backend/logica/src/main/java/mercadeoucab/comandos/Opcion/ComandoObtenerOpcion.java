package mercadeoucab.comandos.Opcion;

import mercadeoucab.accesodatos.DaoOpcion;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.OpcionMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseOpcion;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.Objects;

public class ComandoObtenerOpcion implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.OPCION);
            DaoOpcion dao = (DaoOpcion) fabrica.generarDao();
            Opcion opcion = dao.find(id, Opcion.class);
            ResponseOpcion responseOpcion = (ResponseOpcion) fabrica.generarResponse();
            DtoOpcion dtoOpcion = OpcionMapper.mapEntitytoDto(opcion);
            JsonObject resultado = responseOpcion.generate(dtoOpcion);
            if (Objects.nonNull(resultado))
                result = ResponseGeneral.Succes(resultado);
            else
                result = ResponseGeneral.NoData();

        }
        catch (Exception e){
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al consultar la opcione");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setId(long id) { this.id = id; }
}
