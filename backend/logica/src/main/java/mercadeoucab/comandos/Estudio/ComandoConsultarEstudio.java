package mercadeoucab.comandos.Estudio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.responses.ResponseEstudio;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ComandoConsultarEstudio implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonObject estudioJson;
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
            DaoEstudio dao = (DaoEstudio) fabrica.generarDao();
            Estudio estudio = dao.find(id, Estudio.class);
            if ( estudio.getActivo() == 1)
            {
                ResponseEstudio responseEstudio = (ResponseEstudio) fabrica.generarResponse();
                DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto( estudio );
                estudioJson = responseEstudio.generate( dtoEstudio );
                result = ResponseGeneral.Succes( estudioJson );
            }else
                {
                result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            System.out.println(e);
            result = ResponseGeneral.Failure("Ha ocurrido un error al consultar el estudio");
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
