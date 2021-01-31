package mercadeoucab.comandos.tipo;

import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.TipoMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseTipo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoListarTipos implements ComandoBase {

    private Response result;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder tiposList = Json.createArrayBuilder();
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TIPO);
            DaoTipo dao = (DaoTipo) fabrica.generarDao();
            List<Tipo> tipos = dao.findAll(Tipo.class);
            ResponseTipo responseTipo = (ResponseTipo) fabrica.generarResponse();
            if ( tipos.size() > 0) {
                for (Tipo tipo : tipos) {
                    if (tipo.getActivo() == 1) {
                        DtoTipo dtoTipo = TipoMapper.mapEntityToDto(tipo);
                        JsonObject objeto = responseTipo.generate(dtoTipo);
                        tiposList.add(objeto);
                    }
                }
                result = ResponseGeneral.Succes( tiposList);
            }else {
                result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al listar los tipos");
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
