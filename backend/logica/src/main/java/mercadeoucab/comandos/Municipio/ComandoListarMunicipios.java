package mercadeoucab.comandos.Municipio;

import mercadeoucab.accesodatos.DaoMunicipio;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.MunicipioMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseMunicipio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoListarMunicipios implements ComandoBase {
    private Response result;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder municipios = Json.createArrayBuilder();
            FabricaAbstracta fabricaMunicipio = FabricaAbstracta.getFactory( Fabricas.MUNICIPIO);
            DaoMunicipio dao = (DaoMunicipio) fabricaMunicipio.generarDao();
            List<Municipio> municipiosObtenidos = dao.findAll( Municipio.class);
            ResponseMunicipio responseMunicipio = (ResponseMunicipio) fabricaMunicipio.generarResponse();
            if ( !municipiosObtenidos.isEmpty()) {
                for (Municipio municipio : municipiosObtenidos) {
                    if (municipio.getActivo() != 0) {
                        DtoMunicipio dtoMunicipio = MunicipioMapper.mapEntitytoDto(municipio);
                        JsonObject objeto = responseMunicipio.generate(dtoMunicipio);
                        municipios.add(objeto);
                    }
                }
                this.result = ResponseGeneral.Succes( municipios);
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
