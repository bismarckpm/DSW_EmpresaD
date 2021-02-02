package mercadeoucab.comandos.Tipo;

import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.TipoMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseTipo;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ComandoObtenerTipo implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TIPO);
            DaoTipo dao = (DaoTipo) fabrica.generarDao();
            Tipo resul = dao.find( id , Tipo.class);
            ResponseTipo responseTipo = (ResponseTipo) fabrica.generarResponse();
            DtoTipo dtoTipo = TipoMapper.mapEntityToDto( resul);
            if( resul.getActivo() == 1){
                JsonObject tipo = responseTipo.generate( dtoTipo);
                result = ResponseGeneral.Succes( tipo);
            }else{
                result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e) {
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al listar el tipo");
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
