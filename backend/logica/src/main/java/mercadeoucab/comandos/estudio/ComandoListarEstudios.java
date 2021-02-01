package mercadeoucab.comandos.estudio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.responses.ResponseEstudio;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoListarEstudios implements ComandoBase {

    private Response result;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder estudiosList = Json.createArrayBuilder();
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
            DaoEstudio dao = (DaoEstudio) fabrica.generarDao();
            List<Estudio> estudios = dao.findAll(Estudio.class);
            if ( estudios.size() > 0) {
                for (Estudio estudio : estudios) {
                    if (estudio.getActivo() == 1) {
                        ResponseEstudio responseEstudio = (ResponseEstudio) fabrica.generarResponse();
                        DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto(estudio);
                        JsonObject agregar = responseEstudio.generate(dtoEstudio);
                        estudiosList.add(agregar);
                    }
                }
                result = ResponseGeneral.Succes(estudiosList);
            }else{
                result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un error al listar los estudios");
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
